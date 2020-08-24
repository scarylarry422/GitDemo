Feature: Validating Place API

@AddPlace
Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI 
Given Add place payload	with "<name>" "<language>" "<address>"
When user calls "addPlaceAPI" with "POST" http request 
Then the API call is success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_Id created maps to "<name>" using "getPlaceAPI"

Examples:
	| name   | language |      address       | 
	|AAhouse |English   | World cross center |
	#|BBhouse |French    | Sea cross center   |

@DeletePlace
Scenario: Verify if Delete Place functionality is working
Given DeletePlace Payload
When user calls "deletePlaceAPI" with "POST" http request 
Then the API call is success with status code 200
And "status" in response body is "OK"