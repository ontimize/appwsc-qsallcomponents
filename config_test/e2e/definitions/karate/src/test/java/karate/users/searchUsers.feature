Feature: sample karate test script for User

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
    Given url urlBase + '/searchUsers?columns=USER_,PASSWORD,NAME,SURNAME,EMAIL'
    When method GET
    Then status 200
    And def authToken = response
    And  match $..USER_ contains '#notnull'
    And  match $..PASSWORD contains '#notnull'
    And  match $..NAME  == '#present'

  Scenario: Testing a POST endpoint with request body
    * def searchUsers =
  """
  {
    "data": {
        "USER_": "searchUsersPrueba",
        "PASSWORD":"Prueba",
        "NAME":"name",
        "SURNAME":"SURNAME",
        "EMAIL": "searchUsersPrueba@hotmail.com"
    }
  }
  """
    Given url urlBase + '/user/'
    And request searchUsers
    When method post
    Then status 200
    And match $..USER_ == '#present'
    * print 'postsearchUsers-> ', searchUsers


  Scenario: Delete request
    * def deleteId =
      """
  {
   "filter" :{
		"USER_": "searchUsersPrueba"
	}
  }
    """
    Given url urlBase + '/searchUsers/'
    And request deleteId
    When method DELETE
    Then status 200