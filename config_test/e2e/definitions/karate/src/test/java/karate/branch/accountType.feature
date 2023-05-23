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




  Scenario:
    Given url urlBase + '/accountConcepts?columns=ACCOUNTID,ACCOUNTTYPEID,CONCEPT,OFFICEID'
    * header Authorization = getAuth({username: 'demo', password: 'demouser'})
    When method GET
    Then status 200
    And def authToken = response

    * print 'accountConcepts-> ', response


  Scenario:
    Given url urlBase + '/accountMovementTypes?columns=ACCOUNTID,ACCOUNTTYPEID,MOVEMENT,CONCEPT,OFFICEID'
    * header Authorization = getAuth({username: 'demo', password: 'demouser'})
    When method GET
    Then status 200
    And def authToken = response

    * print 'accountMovementTypes-> ', response



  Scenario:AccountTypeAggregate
    Given url urlBase + '/accountTypeAggregate?columns=ACCOUNTTYPEID,ACCOUNTTYPENAME'
    * header Authorization = getAuth({username: 'demo', password: 'demouser'})
    When method GET
    Then status 200
    And def authToken = response
    And  match $..ACCOUNTTYPEID contains '#notnull'