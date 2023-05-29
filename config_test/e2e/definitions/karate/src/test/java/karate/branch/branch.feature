Feature: sample karate test script for Branches

  Background:
    * def urlBase = 'http://localhost:8080/qsallcomponents-jee/branches'
    * def getAuth =
    """
    function(creds) {
        var temp = creds.username + ':' + creds.password;
        var Base64 = Java.type('java.util.Base64');
        var encoded = Base64.getEncoder().encodeToString(temp.toString().getBytes());
        return 'Basic ' + encoded;
    }
    """
    * header Authorization = getAuth({username: 'demo', password: 'demouser'})


  Scenario:
    Given url urlBase + '/branch?columns=OFFICEID,NAME,ADDRESS,PHONE,COUNTRY'
    When method GET
    Then status 200
    And def authToken = response
    And  match $..OFFICEID contains '#notnull'
    And  match $..NAME contains 'ImatiaBank Innovation'
    And  match $..OFFICEID contains '1471'


  Scenario: Testing a POST endpoint with request body
    * def branch =

  """
  {
    "data": {
        "OFFICEID": "1002",
        "NAME": "Branch1",
        "ADDRESS": "branch1dimatiacom",
        "PHONE": "63803806",
        "COUNTRY": "Spain"
    }
}
  """
    Given url urlBase + '/branch/'
    And request branch
    When method post
    Then status 200
    And match $..OFFICEID == '#present'
    * print 'postbranch-> ', branch


  Scenario:
    Given url urlBase + '/branch?columns=OFFICEID,NAME,ADDRESS,PHONE,COUNTRY'
    When method GET
    Then status 200
    And def authToken = response
    And  match $..OFFICEID contains '#notnull'
    And  match $..NAME contains '#string'
    * print 'checkAddBranch-> ', response




  Scenario: Testing a PUT endpoint with request body
    * def newPostBodyForPut =
     """
  {
    "filter" :{
		"OFFICEID" :1002
	},
    "data": {
        "NAME": "BranchCar",
        "ADDRESS": "branchCardimatiacom",
        "PHONE": "63803806",
        "COUNTRY": "Spain"
    }
}
  """
    Given url urlBase + '/branch/'
    And request newPostBodyForPut
    When method put
    Then status 200
    And print 'newPostBodyForPut-> ', newPostBodyForPut


  Scenario: Delete request
    * def deleteId =
      """
  {
   "filter" :{
		"OFFICEID" :1002
	}
  }
    """
    Given url urlBase + '/branch/'
    And request deleteId
    When method DELETE
    Then status 200


  Scenario: Testing a PAGINATION QUERY endpoint with request body
    * def branchPQ =

  """
      {
    "filter": {},
    "columns": [
        "OFFICEID",
        "NAME",
        "ADDRESS",
        "PHONE",
        "COUNTRY",
        ],
    "offset": 0,
    "pageSize": 10,
    "orderBy": []
}
  """

    Given url urlBase + '/branch/advancedsearch'
    And request branchPQ
    When method post
    Then status 200
    And match $..OFFICEID == '#present'
    * print 'postbranchPQ-> ', branchPQ
