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

  Scenario: create a employee and then get it by id
    * def employee =

  """
  {
    "EMPLOYEEID": "1001",
    "EMPLOYEENAME": "Vinod",
    "EMPLOYEEEMAIL": "vinod@imatia.com",
	"EMPLOYEETYPEID": "6380",
	"EMPLOYEEPHOTO": "NULL"
  }
  """

  Scenario: get all employees and then get the first employee by id
    Given url 'http://localhost:8080/qsallcomponents-jee/employees/employee?columns=EMPLOYEEID,EMPLOYEENAME,EMPLOYEEEMAIL'
    * header Authorization = getAuth({username: 'demo', password: 'demouser'})
    When method get
    Then status 200
    And  match $..EMPLOYEEID contains '#notnull'
    And  match $..EMPLOYEENAME contains 'Mark'
    And  match $..EMPLOYEEID contains 6892

