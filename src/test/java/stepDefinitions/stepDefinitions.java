package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefinitions extends Utils{
	
	RequestSpecification requestSpec;
	ResponseSpecification responseBuilder;
	Response response;
	TestDataBuild data = new TestDataBuild();
	JsonPath js;
	static String placeId;
	
	@Given("Add place payload	with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions

		requestSpec = given().spec(requestSpecification()).body(data.addPlacePayLoad(name,language,address));
		
        responseBuilder = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        //.then().spec(responseBuilder).assertThat().statusCode(200).extract().response();
        
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String pathParam, String action) {
	    // Write code here that turns the phrase above into concrete actions
		
		APIResources apiParam = APIResources.valueOf(pathParam);
		System.out.println(apiParam.getResource());
		
		if(action.equalsIgnoreCase("POST")){
			response = requestSpec.when().post(apiParam.getResource());
		}else if(action.equalsIgnoreCase("GET")) {
			response = requestSpec.when().get(apiParam.getResource());
		}
		       
	}
	
	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		
		assertEquals(200, response.getStatusCode());
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expectedValue) {
	    // Write code here that turns the phrase above into concrete actions
		
	   assertEquals(expectedValue, getJsonPath(response,key));
	}
	

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using_get_place_api(String name, String pathParam) throws IOException {
		
		placeId = getJsonPath(response,"place_id");
		requestSpec = given().spec(requestSpecification()).queryParam("place_id", placeId);
		user_calls_with_http_request(pathParam, "GET");
		
		assertEquals(name,getJsonPath(response,"name"));

	}
	

	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
		
		requestSpec = given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));
	}










}
