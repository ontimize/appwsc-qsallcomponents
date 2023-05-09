Feature: sample karate test script for AccountType

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


  Scenario:
    Given url urlBase + '/account?columns=ACCOUNTTYPEID,ACCOUNTTYPENAME'
    * header Authorization = getAuth({username: 'demo', password: 'demouser'})
    When method GET
    Then status 200
    And def authToken = response
    And  match $..ACCOUNTTYPEID contains '#notnull'


  Scenario: Testing a POST endpoint with request body
    * def account =

  """
  {
    "data": {
        "ACCOUNTTYPEID":1,
        "ACCOUNTTYPENAME":"Savings",
        "OFFICEID" :1471
    }
}
  """
    Given url urlBase + '/account'
    And header Authorization = getAuth({username: 'demo', password: 'demouser'})
    And request account
    When method post
    Then status 200
    And match $..ACCOUNTTYPEID == '#present'
    * print 'postaccount-> ', account


  Scenario:
    Given url urlBase + '/account?columns=ACCOUNTTYPEID,ACCOUNTTYPENAME,OFFICEID'
    * header Authorization = getAuth({username: 'demo', password: 'demouser'})
    When method GET
    Then status 200
    And def authToken = response
    And  match $..ACCOUNTTYPEID contains '#notnull'
    And  match $..OFFICEID contains '1471'
    And  match $..ACCOUNTTYPEID contains '1'
    * print 'checkAddBranch-> ', response

  Scenario: Testing a PUT endpoint with request body
    * def newPostBodyForPut =
     """
  {
    "filter" :{
		"ACCOUNTTYPEID" :"1"
	},
    "data": {
        "ACCOUNTTYPENAME":"AccountTypeNAme",
        "OFFICEID" :1471
    }
}
  """
    Given url urlBase + '/account'
    And header Authorization = getAuth({username: 'demo', password: 'demouser'})
    And request newPostBodyForPut
    When method put
    Then status 200
    And print 'newPostBodyForPut-> ', newPostBodyForPut


