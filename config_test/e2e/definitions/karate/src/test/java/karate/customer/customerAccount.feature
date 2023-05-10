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


  Scenario:
    Given url urlBase + '/customerAccount?columns=CUSTOMERACCOUNTID,CUSTOMERID,ACCOUNTID,ISOWNER'
    * header Authorization = getAuth({username: 'demo', password: 'demouser'})
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
        "CUSTOMERACCOUNTID":2,
        "CUSTOMERID": 10600,
        "ACCOUNTID":2,
        "ISOWNER":"true"
        }
  }
  """
    Given url urlBase + '/customerAccount/'
    And header Authorization = getAuth({username: 'demo', password: 'demouser'})
    And request customerAccount
    When method post
    Then status 200
    And match $..CUSTOMERACCOUNTID == '#present'


  Scenario:
    Given url urlBase + '/customerAccount?columns=CUSTOMERACCOUNTID,CUSTOMERID,ACCOUNTID,ISOWNER'
    * header Authorization = getAuth({username: 'demo', password: 'demouser'})
    When method GET
    Then status 200
    And def authToken = response
    And  match $..CUSTOMERACCOUNTID contains '#notnull'
    And  match $..CUSTOMERID contains '#notnull'