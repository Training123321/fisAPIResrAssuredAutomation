package api.endpoints;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

public class BPIEndpoint {

   	//This will get the URLs from the property file in Resources folder, if there is a change in URL change only in property file
	static ResourceBundle getURL() {
		ResourceBundle routes= ResourceBundle.getBundle("routes"); 
		return routes;
	}
	
	public static Response getBPI() {
		
		String getURL=getURL().getString("getBpi_url");
		Response response= given().
				when().get(getURL);
		return response;
	}
}
