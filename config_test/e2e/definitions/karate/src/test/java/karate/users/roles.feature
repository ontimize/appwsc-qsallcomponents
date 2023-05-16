Feature: sample karate test script for Roles

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
    Given url urlBase + '/role?columns=ID_ROLENAME,ROLENAME,XMLCLIENTPERMISSION'
    When method GET
    Then status 200
    And def authToken = response
    And  match $..ID_ROLENAME contains '#notnull'
    And  match $..ROLENAME contains '#notnull'
    And  match $..XMLCLIENTPERMISSION  == '#present'


  Scenario: Print all role name
    Given url urlBase + '/role?columns=ID_ROLENAME,ROLENAME,XMLCLIENTPERMISSION'
    When method GET
    Then match responseStatus == 200
    * def fun = function(array){karate.log('Print all role name'); for (var i = 0; i < array.length; i++) karate.log(array[i].ROLENAME) }
    * call fun response.data


  Scenario: Testing a POST endpoint with request body
    * def role =
  """
  {
    "data": {
        "ID_ROLENAME": 2,
        "ROLENAME":"demoHoy",
        "XMLCLIENTPERMISSION":"prueba.xml"
    }
  }
  """
    Given url urlBase + '/role/'
    And request role
    When method post
    Then status 200
    And match $..ID_ROLENAME == '#present'
    * print 'postrole-> ', role


  Scenario: Testing a PUT endpoint with request body
    * def newPostBodyForPut =
     """
  {
    "filter" :{
		"ID_ROLENAME": 2
	},
    "data": {
        "ROLENAME":"prueba"
    }
}
  """
    Given url urlBase + '/role/'
    And request newPostBodyForPut
    When method put
    Then status 200


  Scenario: Delete request
    * def deleteId =
      """
  {
   "filter" :{
		"ID_ROLENAME": 2
	}
  }
    """
    Given url urlBase + '/role/'
    And request deleteId
    When method DELETE
    Then status 200