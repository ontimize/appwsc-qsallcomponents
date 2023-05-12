Feature: sample karate test script for Movement

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


  Scenario:
    Given url urlBase + '/user?columns=USER_,PASSWORD,NAME,SURNAME,EMAIL'
    * header Authorization = getAuth({username: 'demo', password: 'demouser'})
    When method GET
    Then status 200
    And def authToken = response
    And  match $..USER_ contains '#notnull'
    And  match $..PASSWORD contains '#notnull'
    And  match $..NAME  == '#present'


  Scenario: get all users and then get the first user by id
    Given url urlBase + '/user?columns=USER_,PASSWORD,NAME,SURNAME,EMAIL'
    * header Authorization = getAuth({username: 'demo', password: 'demouser'})
    When method GET
    Then status 200

    * def first = response[0]

  Scenario: Get existent user and check match contains any
    Given url urlBase + '/user?columns=USER_,PASSWORD,NAME,SURNAME,EMAIL'
    * header Authorization = getAuth({username: 'demo', password: 'demouser'})
    When method get
    Then match responseStatus == 200
    And match $..USER_ contains '#string'

  Scenario: Print all users email
    Given url urlBase + '/user?columns=USER_,PASSWORD,NAME,SURNAME,EMAIL'
    * header Authorization = getAuth({username: 'demo', password: 'demouser'})
    When method GET
    Then match responseStatus == 200
    * def fun = function(array){karate.log('Print all users email'); for (var i = 0; i < array.length; i++) karate.log(array[i].EMAIL) }
    * call fun response.data


  Scenario: Get existent user with validate email
    Given url urlBase + '/user?columns=USER_,PASSWORD,NAME,SURNAME,EMAIL'
    * header Authorization = getAuth({username: 'demo', password: 'demouser'})
    When method GET
    Then match responseStatus == 200
    And match $..EMAIL contains '#regex .*@imatia.*'

    * def fun = function(array){karate.log('Get existent user with validate email'); for (var i = 0; i < array.length; i++) karate.log(eval(array[i].email == '#regex .+@reqres.in') )}
    * call fun response.data

   # * match response.data[*] contains {id:'#number? _ > 0', email:'#regex .+@reqres.in', first_name:'#string', last_name:'#string', avatar: '#string'}

  Scenario: Eval all users email
    Given url urlBase + '/user?columns=USER_,PASSWORD,NAME,SURNAME,EMAIL'
    * header Authorization = getAuth({username: 'demo', password: 'demouser'})
    When method GET
    Then match responseStatus == 200
    * def fun = function(array){karate.log('Eval all users email'); for (var i = 0; i < array.length; i++) karate.log(eval(array[i].email == '#regex [A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}') )}
    * call fun response.data
