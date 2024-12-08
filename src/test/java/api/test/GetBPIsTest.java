package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.BPIEndpoint;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;



public class GetBPIsTest {
	
	@Test
	public void getBPIdata() {
		
		
		Response response = BPIEndpoint.getBPI();
		ArrayList<String> val = (ArrayList<String>)com.jayway.jsonpath.JsonPath.read(response.asString(), "$.bpi.*.code");
		System.out.println(val); //2
		
		response.then().log().all();
		Assert.assertTrue(val.size()==3);
		ArrayList<String> expectedList= new ArrayList<String>();
		expectedList.add("USD");expectedList.add("GBP");expectedList.add("EUR");
		Assert.assertEquals(val,expectedList);
		response.then().body("bpi.GBP.description", containsString("British Pound Sterling"));
		
	}

}
