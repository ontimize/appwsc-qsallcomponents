Feature: sample karate test script for WebClientConfig

  Background:
    * def urlBase = 'http://localhost:8080/qsallcomponents-jee/config'
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



  Scenario: Basic Post
    Given url urlBase + '/remoteConfiguration?columns=USER_,APP_UUID,CONFIGURATION'
    When method GET
    Then status 200
    * print 'WebClientConfig-> ', response