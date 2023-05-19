Feature: sample karate test script for ServerRole

  Background:
    * def urlBase = 'http://localhost:8080/qsallcomponents-jee/users'
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



  Scenario: Basic Get
    Given url urlBase + '/serverRole?columns=ID_ROLE_SERVER_PERMISSION,ID_ROLENAME,ID_SERVER_PERMISSION'
    When method GET
    Then status 200
    And def authToken = response
    And  match $..ID_ROLE_SERVER_PERMISSION contains '#notnull'
    And  match $..ID_ROLENAME contains '#notnull'
    And  match $..ID_SERVER_PERMISSION  == '#present'