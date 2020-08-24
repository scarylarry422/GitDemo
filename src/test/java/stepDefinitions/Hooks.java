package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		stepDefinitions stepDef = new stepDefinitions();
		if(stepDefinitions.placeId == null) {
		stepDef.add_place_payload_with("Larry", "Japanese", "Nowhere");
		stepDef.user_calls_with_http_request("addPlaceAPI", "POST");
		stepDef.verify_place_id_created_maps_to_using_get_place_api("Larry", "getPlaceAPI");
		}
	}

}
