package serialization;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;


import java.util.ArrayList;
import java.util.List;

public class GoogleMapApi {

	@Test
	public void addPlace() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		List<String> typesList = new ArrayList<String>();
		typesList.add("shoe");
		typesList.add("parkkk");
		typesList.add("new place");
		
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		GoogleJson1 gs = new GoogleJson1();
		gs.setLocation(l);
		
		gs.setAccuracy(50);
		gs.setAddress("some address");
		gs.setLanguage("English-US");
		gs.setName("Chitra");
		gs.setPhone_number("91 8438469817");
		gs.setTypes(typesList);
		
		String response =
		given().log().all().queryParam("key", "qaclick123").body(gs)
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(response);
	}
}
