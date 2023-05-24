Feature: sample karate test script for CustomerAccount

  Background:
    * def urlBase = 'http://localhost:8080/qsallcomponents-jee/customers'
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


  Scenario:Basic GET
    Given url urlBase + '/customerAccount?columns=CUSTOMERACCOUNTID,CUSTOMERID,ACCOUNTID,ISOWNER'
    When method GET
    Then status 200
    And def authToken = response
    And  match $..CUSTOMERACCOUNTID contains '#notnull'
    And  match $..CUSTOMERID contains '#notnull'


  Scenario: Testing a POST endpoint with request body
    * def customerAccount =

  """
  {
    "data": {
        "CUSTOMERID": 19274,
        "ACCOUNTID":7309,
        "ISOWNER":"true"
        }
  }
  """
    Given url urlBase + '/customerAccount'
    And request customerAccount
    When method post
    Then status 200
    And match $..CUSTOMERACCOUNTID == '#present'


  Scenario:GET to check the POST
    Given url urlBase + '/customerAccount?columns=CUSTOMERACCOUNTID,CUSTOMERID,ACCOUNTID,ISOWNER'
    When method GET
    Then status 200
    And def authToken = response
    And  match $..CUSTOMERACCOUNTID contains '#notnull'
    And  match $..CUSTOMERID contains '#notnull'


  Scenario: Testing a PUT endpoint with request body
    * def newPostBodyForPut =
     """
  {
    "filter" :{
		"CUSTOMERACCOUNTID":20665
	},
    "data": {
        "ISOWNER":"false"
    }
}
  """
    Given url urlBase + '/customerAccount/'
    And request newPostBodyForPut
    When method put
    Then status 200

  Scenario: Delete request
    * def deleteId =
      """
  {
   "filter" :{
        "CUSTOMERACCOUNTID":20668,
		"CUSTOMERACCOUNTID":20656
	}
  }
    """
    Given url urlBase + '/customerAccount/'
    And request deleteId
    When method DELETE
    Then status 200

