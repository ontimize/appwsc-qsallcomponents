Feature: sample karate test script for FileManager

  Background:
    * def urlBase = 'http://localhost:8080/qsallcomponents-jee/filemanager/'
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



  Scenario: Basic POST
    * def filemanager =

  """
    {"filter":{},"columns": ["id", "name", "size", "creationDate", "directory"]}
  """

    Given url urlBase + 'queryFiles/38'
    And request filemanager
    When method POST
    Then status 200
    * print 'fileManager-> ', response



  Scenario: Basic Get
    Given url urlBase + 'getFiles/38
    When method GET
    Then status 200
    * print 'getFile-> ', response