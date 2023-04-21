Feature: sample karate test script

  Background:
    * url 'http://localhost:8080/qsallcomponents-jee'


  Scenario: get all employees and then get the first employee by id
    Given path 'employee'
    When method get
    Then status 200

    * def first = response[0]

    Given path 'employee', first.id
    When method get
    Then status 200

  Scenario: create a employee and then get it by id
    * def employee =

  """
  {
    "employeeID": "1001",
    "name": "Vinod",
    "email": "vinod@imatia.com",
	"officeID": "0001",
	"employeeTypeID": "6380",
	"employeePhoto": "NULL"
  }
  """

