package resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayLoad(String name, String language, String address) {
		List<String> types = new ArrayList<>();
        String[] myTypes = {"shoe park","shop"};
        types = Arrays.asList(myTypes);
        AddPlace p = new AddPlace();
        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);

        p.setAccuracy(50);
        p.setAddress(address);
        p.setLanguage(language);
        p.setPhone_number("(+91) 983 893 3937");
        p.setName(name);
        p.setTypes(types);
        p.setWebsite("http://google.com");
        p.setLocation(l);
        
        return(p);
	}
	
	public String deletePlacePayload(String placeId) 
	{
		return "{\r\n\"place_id\": \""+placeId +"\"\r\n}";
	}
	

}
