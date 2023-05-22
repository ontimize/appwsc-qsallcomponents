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
    Given url urlBase + '/serverRole?columns=ID_SERVER_PERMISSION,PERMISSION_NAME'
    When method GET
    Then status 200
    And def authToken = response
    And  match $..ID_SERVER_PERMISSION contains '#notnull'
    And  match $..PERMISSION_NAME contains '#notnull'


  Scenario: Testing a PUT endpoint with request body
    * def serverRole =
  """
  {"filter" :{
		"ID_ROLE_SERVER_PERMISSION": 76
	},
    "data": {
        "ID_ROLE":4,
        "ID_SERVER_PERMISSION":76,
        "ACTIVED":"S"
    }
  }
  """
    Given url urlBase + '/serverRole/'
    And request serverRole
    When method put
    Then status 200
    * print 'postRolesForuser-> ', serverRole

  Scenario: Delete request
    * def deleteId =
      """
  {
   "filter" :{
        "ID_SERVER_PERMISSION":76,
        "ID_ROLE_SERVER_PERMISSION": 76

      }
  }
    """
    Given url urlBase + '/serverRole/'
    And request deleteId
    When method put
    Then status 200