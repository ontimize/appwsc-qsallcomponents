Feature: sample karate test script

  Background:
    * def urlBase = 'http://localhost:8080/qsallcomponents-jee/employees'
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


  Scenario:Basic GET
    Given url urlBase + '/employee?columns=EMPLOYEEID'
    When method GET
    Then status 200
    And def authToken = response


  Scenario: get all employees and then get the one employee
    Given url urlBase + '/employee?columns=EMPLOYEEID,EMPLOYEENAME,EMPLOYEEEMAIL'
    When method get
    Then status 200
    And  match $..EMPLOYEEID contains '#notnull'
    And  match $..EMPLOYEENAME contains 'Mark'
    And  match $..EMPLOYEEID contains 6892


  Scenario: Testing a POST endpoint with request body
    * def employee =

  """
  {
    "data": {
        "EMPLOYEEID": "1002",
        "EMPLOYEENAME": "Vinod",
        "EMPLOYEEEMAIL": "vinod@imatia.com",
        "EMPLOYEETYPEID": "6380",
        "EMPLOYEEPHOTO": "NULL"
    }
}
  """
    Given url urlBase + '/employee/'
    And request employee
    When method post
    Then status 200
    And match $..EMPLOYEEID == '#present'
    * print 'postemployee-> ', employee


  Scenario: check that the previous employee was added
    Given url urlBase + '/employee?columns=EMPLOYEEID,EMPLOYEENAME,EMPLOYEEEMAIL,EMPLOYEETYPEID,EMPLOYEEPHOTO'
    When method get
    Then status 200
    And  match $..EMPLOYEEID contains '#notnull'
    And  match $..EMPLOYEENAME contains 'Vinod'
    And  match $..EMPLOYEETYPEID contains 6380
    * print 'checkAddEmployee-> ', response


  Scenario: Testing a POST endpoint with request body
    * def employeeSecond =

  """
  {
    "data": {
        "EMPLOYEEID": "1002",
        "EMPLOYEENAME": "Sara",
        "EMPLOYEEEMAIL": "sara@imatia.com",
        "EMPLOYEETYPEID": "6381",
        "EMPLOYEEPHOTO": "NULL",
        "EMPLOYEEPHONE": "98754321"
    }
}
  """
    Given url urlBase + '/employee/'
    And request employeeSecond
    And remove employeeSecond.EMPLOYEEPHONE
    When method post
    Then status 200
    * print 'employeeSecond-> ', employeeSecond


  Scenario Outline: As a <description>, I want to get the corresponding response_code <status_code>
    Given url urlBase + '/employee?columns=EMPLOYEEID,EMPLOYEENAME,EMPLOYEEEMAIL,EMPLOYEETYPEID,EMPLOYEEPHOTO'
    And request { 'EMPLOYEEEMAIL': <employeeemail> , 'EMPLOYEENAME': <employeename> }
    When method POST
    * print 'employeePostExamples-> ', response
    Then response.status == <status_code>
    Examples:
      | employeeemail        | employeename | status_code | description  |
      | 'sara@imatia.com'    | 'Sara'       | 200         | valid user   |
      | 'eveholt@imatia.com' | null         | 400         | invalid user |


  Scenario: Testing a PUT endpoint with request body
    * def newPostBodyForPut =
     """
  {
    "filter" :{
		"EMPLOYEEID" :21039
	},
    "data": {
        "EMPLOYEENAME": "Carmen",
        "EMPLOYEEEMAIL": "carmen@imatia.com",
        "EMPLOYEETYPEID": "6380",
        "EMPLOYEEPHOTO": "NULL",
        "EMPLOYEEPHONE": "null"
    }
}
  """
    Given url urlBase + '/employee/'
    And request newPostBodyForPut
    When method put
    Then status 200
    And print 'newPostBodyForPut-> ', newPostBodyForPut




  Scenario: Delete request
    * def deleteId =
      """
  {
   "filter":{
      "EMPLOYEEID":	21036
     }
  }
    """
    Given url urlBase + '/employee/'
    And request deleteId
    When method DELETE
    Then status 200
