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
    Given url urlBase + '/employeeType?columns=EMPLOYEETYPEID'
    When method GET
    Then status 200
    And def authToken = response


  Scenario: get all employees and then get the one employee
    Given url urlBase + '/employeeType?columns=EMPLOYEETYPEID,EMPLOYEETYPENAME'
    When method get
    Then status 200
    And  match $..EMPLOYEETYPEID contains '#notnull'
    And  match $..EMPLOYEETYPENAME contains 'Manager'
    And  match $..EMPLOYEETYPEID contains 6380


  Scenario: Testing a POST endpoint with request body
    * def employeeType =

  """
  {
    "data": {
        "EMPLOYEETYPEID": "1002",
        "EMPLOYEETYPENAME": "Vinod",
        "EMPLOYEETYPEDESC": "Vinod"

    }
}
  """
    Given url urlBase + '/employeeType/'
    And request employeeType
    When method post
    Then status 200
    And match $..EMPLOYEETYPEID == '#present'
    * print 'postemployeeType-> ', employeeType



  Scenario: get all employees and then get the one employee
    Given url urlBase + '/employeeType?columns=EMPLOYEETYPEID,EMPLOYEETYPENAME,EMPLOYEETYPEDESC'
    When method get
    Then status 200
    And  match $..EMPLOYEETYPEID contains '#notnull'
    And  match $..EMPLOYEETYPENAME contains 'Vinod'
    And  match $..EMPLOYEETYPEDESC contains 'Vinod'



  Scenario: Testing a PUT endpoint with request body
    * def newPostBodyForPut =
     """
  {
    "filter" :{
		"EMPLOYEETYPEID" :6389
	},
    "data": {
        "EMPLOYEETYPENAME": "Carmen",
        "EMPLOYEETYPEDESC": "Carmen"
    }
}
  """

    Given url urlBase + '/employeeType/'
    And request newPostBodyForPut
    When method put
    Then status 200
    And print 'newPostBodyForPut-> ', newPostBodyForPut



  Scenario: Delete request
    * def deleteId =
      """
  {
   "filter":{
      "EMPLOYEETYPEID":	6387
     }
  }
    """
    Given url urlBase + '/employeeType/'
    And request deleteId
    When method DELETE
    Then status 200




  Scenario:employeeTypeAggregateQuery
    Given url urlBase + '/employeeTypeAggregate?columns=EMPLOYEETYPEID,EMPLOYEETYPENAME'
    When method GET
    Then status 200
    And def authToken = response
    And  match $..EMPLOYEETYPEID == '#notnull'
