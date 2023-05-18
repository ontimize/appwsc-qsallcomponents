Feature: sample karate test script for RolesForUser

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
    Given url urlBase + '/rolesForUser?columns=ID_USER_ROLE,ID_ROLENAME,USER_'
    When method GET
    Then status 200
    And def authToken = response
    And  match $..ID_USER_ROLE  == '#present'
    And  match $..ID_ROLENAME  == '#present'
    And  match $..USER_  == '#present'


  Scenario: Print all ID_ROLENAME for rolesForUser
    Given url urlBase + '/rolesForUser?columns=ID_USER_ROLE,ID_ROLENAME,USER_'
    When method GET
    Then status 200
    And def authToken = response
    * def fun = function(array){karate.log('Print all RolesForUser'); for (var i = 0; i < array.length; i++) karate.log(array[i].ID_ROLENAME) }
    * call fun response.data


  Scenario: Testing a PUT endpoint with request body
    * def RolesForuser =
  """
  {"filter" :{
		"ID_USER_ROLE": 4
	},
    "data": {
        "ID_ROLENAME":0,
        "USER_":"name",
        "ACTIVED":"S"
    }
  }
  """
    Given url urlBase + '/rolesForUser/'
    And request RolesForuser
    When method put
    Then status 200
    * print 'postRolesForuser-> ', RolesForuser