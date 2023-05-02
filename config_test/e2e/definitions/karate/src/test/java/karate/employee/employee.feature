Feature: sample karate test script

  Background:
    * url 'http://localhost:8080/qsallcomponents-jee'
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
    Given url 'http://localhost:8080/qsallcomponents-jee/employees/employee?columns=EMPLOYEEID'
    * header Authorization = getAuth({username: 'demo', password: 'demouser'})
    When method GET
    Then status 200
    And def authToken = response


  Scenario: get all employees and then get the one employee
    Given url 'http://localhost:8080/qsallcomponents-jee/employees/employee?columns=EMPLOYEEID,EMPLOYEENAME,EMPLOYEEEMAIL'
    * header Authorization = getAuth({username: 'demo', password: 'demouser'})
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
    Given url 'http://localhost:8080/qsallcomponents-jee/employees/employee/'
    And header Authorization = getAuth({username: 'demo', password: 'demouser'})
    And request employee
    When method post
    Then status 200
    * print response


  Scenario: check that the previous employee was added
    Given url 'http://localhost:8080/qsallcomponents-jee/employees/employee?columns=EMPLOYEEID,EMPLOYEENAME,EMPLOYEEEMAIL,EMPLOYEETYPEID,EMPLOYEEPHOTO'
    * header Authorization = getAuth({username: 'demo', password: 'demouser'})
    When method get
    Then status 200
    And  match $..EMPLOYEEID contains '#notnull'
    And  match $..EMPLOYEENAME contains 'Vinod'
    And  match $..EMPLOYEETYPEID contains 6380
    And  match $..EMPLOYEEID contains 21040






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
    Given url 'http://localhost:8080/qsallcomponents-jee/employees/employee/'
    And header Authorization = getAuth({username: 'demo', password: 'demouser'})
    And request employeeSecond
    And remove employeeSecond.EMPLOYEEPHONE
    When method post
    Then status 200
    * print response


  Scenario: check that the previous employee was added
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
    Given url 'http://localhost:8080/qsallcomponents-jee/employees/employee?columns=EMPLOYEEID,EMPLOYEENAME,EMPLOYEEEMAIL,EMPLOYEETYPEID,EMPLOYEEPHOTO'
    * header Authorization = getAuth({username: 'demo', password: 'demouser'})
    When method get
    Then status 200
    And match $.EMPLOYEEID == employeeSecond.EMPLOYEEID
    And match $.EMPLOYEENAME == employeeSecond.EMPLOYEENAME
    And match $.EMPLOYEEEMAIL == employeeSecond.EMPLOYEEEMAIL
    And match $.EMPLOYEETYPEID == employeeSecond.EMPLOYEETYPEID
