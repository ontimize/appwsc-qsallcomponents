Feature: sample karate test script for MovementType

  Background:
    * def urlBase = 'http://localhost:8080/qsallcomponents-jee/movements'
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
    Given url urlBase + '/movementType?columns=MOVEMENTTYPEID,DESCRIPTION'
    When method GET
    Then status 200
    And def authToken = response
    And  match $..MOVEMENTTYPEID contains '#notnull'
    And  match $..DESCRIPTION  == '#present'


  Scenario: Testing a POST endpoint with request body
    * def movementType =

  """
  {
    "data": {
        "DESCRIPTION":"First movementKarate"
        }
  }
  """
    Given url urlBase + '/movementType'
    And request movementType
    When method post
    Then status 200
    And match $..MOVEMENTID == '#present'
    * def postmovementType = response

  Scenario:GET to check the POST
    Given url urlBase + '/movementType?columns=MOVEMENTTYPEID,DESCRIPTION'
    When method GET
    Then status 200
    And def authToken = response
    And  match $..MOVEMENTTYPEID contains '#notnull'
    And  match $..DESCRIPTION  == '#present'

  Scenario: Testing a PUT endpoint with request body
    * def newPostBodyForPut =
     """
  {
    "filter" :{
        "MOVEMENTTYPEID":8
	},
    "data": {
        "DESCRIPTION":"descriptionPut"
    }}
    """

    Given url urlBase + '/movementType/'
    And request newPostBodyForPut
    When method put
    Then status 200
    And print 'newPostBodyForPut-> ', newPostBodyForPut

  Scenario: Delete request
    * def deleteId =
      """
  {
   "filter" :{
        "MOVEMENTTYPEID":8,
        "MOVEMENTTYPEID":9,
        "MOVEMENTTYPEID":10,
        "MOVEMENTTYPEID":11
	}
  }
    """
    Given url urlBase + '/movementType/'
    And request deleteId
    When method DELETE
    Then status 200