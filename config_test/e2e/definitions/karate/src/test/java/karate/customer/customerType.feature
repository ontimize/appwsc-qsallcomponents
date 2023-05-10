Feature: sample karate test script for CustomerType

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


  Scenario:
    Given url urlBase + '/customerType?columns=CUSTOMERTYPEID,DESCRIPTION'
    * header Authorization = getAuth({username: 'demo', password: 'demouser'})
    When method GET
    Then status 200
    And def authToken = response
    And  match $..CUSTOMERTYPEID contains '#notnull'
    And  match $..DESCRIPTION contains '#notnull'


  Scenario: Testing a POST endpoint with request body
    * def customerType =

  """
  {
    "data": {
        "CUSTOMERTYPEID":"3",
        "DESCRIPTION": "CustomerType"
        }
  }
  """
    Given url urlBase + '/customerType/'
    And header Authorization = getAuth({username: 'demo', password: 'demouser'})
    And request customerType
    When method post
    Then status 200
    And match $..CUSTOMERTYPEID == '#present'


  Scenario:
    Given url urlBase + '/customerType?columns=CUSTOMERTYPEID,DESCRIPTION'
    * header Authorization = getAuth({username: 'demo', password: 'demouser'})
    When method GET
    Then status 200
    And def authToken = response
    And  match $..CUSTOMERTYPEID contains '#notnull'
    And  match $..DESCRIPTION contains '#notnull'


  Scenario: Testing a PUT endpoint with request body
    * def newPostBodyForPut =
     """
  {
    "filter" :{
		"CUSTOMERTYPEID":7
	},
    "data": {
        "DESCRIPTION": "CustomerTypePUT"
    }
}
  """
    Given url urlBase + '/customerType/'
    And header Authorization = getAuth({username: 'demo', password: 'demouser'})
    And request newPostBodyForPut
    When method put
    Then status 200

  Scenario: Delete request
    * def deleteId =
      """
  {
   "filter" :{
		"CUSTOMERTYPEID":7
	}
  }
    """
    Given url urlBase + '/customerType/'
    And header Authorization = getAuth({username: 'demo', password: 'demouser'})
    And request deleteId
    When method DELETE
    Then status 200
