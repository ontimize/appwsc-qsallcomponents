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
    Given url urlBase + '/user?columns=USER_,PASSWORD,NAME,SURNAME,EMAIL'
    When method GET
    Then status 200
    And def authToken = response
    And  match $..USER_ contains '#notnull'
    And  match $..PASSWORD contains '#notnull'
    And  match $..NAME  == '#present'


  Scenario: get all users and then get the first user by id
    Given url urlBase + '/user?columns=USER_,PASSWORD,NAME,SURNAME,EMAIL'
    When method GET
    Then status 200

    * def first = response[0]

  Scenario: Get existent user and check match contains any
    Given url urlBase + '/user?columns=USER_,PASSWORD,NAME,SURNAME,EMAIL'
    When method get
    Then match responseStatus == 200
    And match $..USER_ contains '#string'

  Scenario: Print all users email
    Given url urlBase + '/user?columns=USER_,PASSWORD,NAME,SURNAME,EMAIL'
    When method GET
    Then match responseStatus == 200
    * def fun = function(array){karate.log('Print all users email'); for (var i = 0; i < array.length; i++) karate.log(array[i].EMAIL) }
    * call fun response.data


  Scenario:Get existent user with validate email
    Given url urlBase + '/user?columns=USER_,PASSWORD,NAME,SURNAME,EMAIL'
    When method GET
    Then match responseStatus == 200
    * def fun = function(array){karate.log('Get existent user with validate email'); for (var i = 0; i < array.length; i++) karate.log(eval(array[i].email == '#regex .+@imatia.com') )}
    * call fun response.data



  Scenario: Eval all users email
    Given url urlBase + '/user?columns=USER_,PASSWORD,NAME,SURNAME,EMAIL'
    When method GET
    Then match responseStatus == 200
    * def fun = function(array){karate.log('Eval all users email'); for (var i = 0; i < array.length; i++) karate.log(eval(array[i].email == '#regex ^[^@]+@[^@]+\.[a-zA-Z]{2,}$') )}
    * call fun response.data
    * def fun = function(array){karate.log('Eval all users email'); for (var i = 0; i < array.length; i++) karate.log(eval(array[i].email == '#regex ^[^@]+@[^@]+\.[a-zA-Z]{2,}$^[a-zA-Z0-9_\-\.~]{2,}@[a-zA-Z0-9_\-\.~]{2,}\.[a-zA-Z]{2,4}$') )}
    * call fun response.data

  Scenario: Get existent user with validate email with pattern

    Given url urlBase + '/user?columns=USER_,PASSWORD,NAME,SURNAME,EMAIL'
    When method GET
    Then match responseStatus == 200
    And match $..EMAIL contains  '#regex [A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}'


  Scenario: Testing a POST endpoint with request body
    * def user =
  """
  {
    "data": {
        "USER_": "demoHoyLunes",
        "PASSWORD":"demoHoy",
        "NAME":"name",
        "SURNAME":"SURNAME",
        "EMAIL": "prueba.hoy@hotmail.com"
    }
  }
  """
    Given url urlBase + '/user/'
    And request user
    When method post
    Then status 200
    And match $..USER_ == '#present'
    * print 'postuser-> ', user


  Scenario: Testing a PUT endpoint with request body
    * def newPostBodyForPut =
     """
  {
    "filter" :{
		"USER_": "demoHoy"
	},
    "data": {
        "SURNAME":"Surname"
    }
}
  """
    Given url urlBase + '/user/'
    And request newPostBodyForPut
    When method put
    Then status 200


  Scenario: Delete request
    * def deleteId =
      """
  {
   "filter" :{
		"USER_": "demoHoyLunes"
	}
  }
    """
    Given url urlBase + '/user/'
    And request deleteId
    When method DELETE
    Then status 200