package restassuredpractise;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


import org.testng.Assert;

import files.MapPayload;
import files.RawToJson;

public class MapPractiseWithSpecBuilders {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
	
		RequestSpecification requestSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123")
		.setContentType(ContentType.JSON).build();
		
		ResponseSpecification responseSpec = new ResponseSpecBuilder().expectStatusCode(200)
		.expectContentType(ContentType.JSON).expectHeader("server", "Apache/2.4.18 (Ubuntu)").build();
		
		String response =
		given().spec(requestSpec).body(MapPayload.addPlace())
		.when().post("/maps/api/place/add/json")
		.then().spec(responseSpec).body("scope", equalTo("APP"))
		.extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		String placeId = js.get("place_id");
		
		System.out.println(placeId);

		String newAddress = "My new Address!!";
		
		//Update Place
		given().spec(requestSpec)
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}\r\n" + 
				"")
		.when().put("/maps/api/place/update/json")
		.then().log().all().spec(responseSpec).body("msg", equalTo("Address successfully updated"));
		

		
		
		String getResponse = 
				given().spec(requestSpec).queryParam("place_id", placeId)
				.when().get("/maps/api/place/get/json")
				.then().spec(responseSpec).extract().response().asString();
		
	
		
		JsonPath js1 = RawToJson.parseJson(getResponse);
		String actualAddress = js1.get("address");
		
		Assert.assertEquals(actualAddress, newAddress);
		
	}

}
