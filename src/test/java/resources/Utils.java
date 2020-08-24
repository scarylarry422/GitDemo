package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	static RequestSpecification requestBuilder;
	
	public RequestSpecification requestSpecification() throws IOException {
		
		if (requestBuilder == null) {
		
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		requestBuilder = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key","qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return requestBuilder;
		}
	
	return requestBuilder;
	}
	
	//Use this method to read properties file
	//It only works with files with the extension .properties
	public static String getGlobalValue(String key) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("/Users/lltam/eclipse-workspace/APIFramework/src/test/java/resources/global.properties");
		prop.load(fis); //method [load] is used for loading the fileInputStream into the Properties object 
		return prop.getProperty(key); //get any property with the provided key
	}
	
	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}
	

}
