Feature: sample karate test script for Dummy

  Background:
    * def urlBase = 'http://localhost:8080/qsallcomponents-jee/dummy'
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
    Given url urlBase
    When method GET
    Then status 200
    * print 'dummy-> ', response


  Scenario: Basic Get for dummypermission
    Given url urlBase + '/dummypermission/'
    When method GET
    Then status 200
    * print 'dummypermission-> ', response