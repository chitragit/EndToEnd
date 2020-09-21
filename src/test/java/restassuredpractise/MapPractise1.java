package restassuredpractise;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;

import files.MapPayload;
import files.RawToJson;

public class MapPractise1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Map<String,String> inputs = new HashMap<String, String>();
		
		inputs.put("key", "qaclick123");
		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response =
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(MapPayload.addPlace())
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)")
		.extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		String placeId = js.get("place_id");
		
		System.out.println(placeId);

		String newAddress = "My new Address!!";
		
		//Update Place
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}\r\n" + 
				"")
		.when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		

		//Get
		inputs.put("place_id", placeId);
		
		
		String getResponse = given().log().all().queryParams(inputs)
		.queryParam("place_id", placeId).when().get("/maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
	
		
		JsonPath js1 = RawToJson.parseJson(getResponse);
		String actualAddress = js1.get("address");
		
		Assert.assertEquals(actualAddress, newAddress);
		
	}

}
