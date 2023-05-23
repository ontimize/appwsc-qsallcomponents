Feature: sample karate test script for Customer

  Background:
    * def urlBase = 'http://localhost:8080/qsallcomponents-jee/customers'
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


  Scenario:
    Given url urlBase + '/customer?columns=CUSTOMERID,CUSTOMERTYPEID,NAME,SURNAME,ADDRESS,PHONE,COUNTRY'
    When method GET
    Then status 200
    And def authToken = response
    And  match $..CUSTOMERID contains '#notnull'
    And  match $..NAME contains 'Daisy'
    And  match $..CUSTOMERID contains 7511


  Scenario: Testing a POST endpoint with request body
    * def customer =

  """
  {
    "data": {
        "CUSTOMERID": 1002,
        "CUSTOMERTYPEID":"3",
        "NAME": "Customer1",
        "SURNAME":"Surname1",
        "ADDRESS": "customer1dimatiacom",
        "PHONE": "63803806"
        }
  }
  """
    Given url urlBase + '/customer/'
    And request customer
    When method post
    Then status 200
    And match $..CUSTOMERID == '#present'
    * print 'postcustomer-> ', customer


  Scenario:
    Given url urlBase + '/customer?columns=CUSTOMERID,CUSTOMERTYPEID,NAME,SURNAME,ADDRESS,PHONE,COUNTRY'
    When method GET
    Then status 200
    And def authToken = response
    And  match $..CUSTOMERID contains '#notnull'
    And  match $..NAME contains 'Customer1'
    And  match $..ADDRESS contains 'customer1dimatiacom'
    * print 'checkAddBranch-> ', response




  Scenario: Testing a PUT endpoint with request body
    * def newPostBodyForPut =
     """
  {
    "filter" :{
		"CUSTOMERID": 1002
	},
    "data": {
        "NAME": "CustomerName",
        "ADDRESS": "customerNameImatiacom",
        "PHONE": "63803806",
        "COUNTRY": "Spain"
    }
}
  """
    Given url urlBase + '/customer/'
    And request newPostBodyForPut
    When method put
    Then status 200
    And print 'newPostBodyForPut-> ', newPostBodyForPut

  Scenario: Delete request
    * def deleteId =
      """
  {
   "filter" :{
		"CUSTOMERID": 1002
	}
  }
    """
    Given url urlBase + '/customer/'
    And request deleteId
    When method DELETE
    Then status 200



  Scenario: Testing a PAGINATION QUERY endpoint with request body
    * def customerPQ =

  """
      {
    "filter": {},
    "columns": [
        "CUSTOMERID",
        "CUSTOMERTYPEID",
        "NAME",
        "SURNAME",
        "ADDRESS",
        "PHONE",
        "EMAIL"
        ],
    "offset": 0,
    "pageSize": 10,
    "orderBy": []
}
  """

    Given url urlBase + '/customer/advancedsearch'
    And request customerPQ
    When method post
    Then status 200
    And match $..OFFICEID == '#present'
    * print 'postcustomerPQ-> ', customerPQ
