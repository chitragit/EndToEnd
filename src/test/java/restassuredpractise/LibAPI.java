package restassuredpractise;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.MapPayload;
import files.RawToJson;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LibAPI {
	
	@Test(dataProvider = "getBookData")
	public void addBook(String isbn, String aisle) throws InterruptedException, IOException {
		RestAssured.baseURI = "http://216.10.245.166";
		
		
		
		//add book
		String addResponse =
				given().log().all().header("Content-Type","application/json")
				.body(MapPayload.addBookLibApi(isbn,aisle))
				.when().post("/Library/Addbook.php")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(addResponse);
		
		
		JsonPath js = RawToJson.parseJson(addResponse);
		System.out.println(js.getString("ID"));
	
		/*
		//get book
		String getBook = given().log().all().queryParam("ID", js.getString("ID") )
		.when().get("/Library/GetBook.php").then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(getBook);
		
		//delete
		String deleteBookresponse = given().header("Content-Type","application/json").body(MapPayload.deleteBook(js.getString("ID")))
		.when().post("/Library/DeleteBook.php").then().log().all().assertThat().statusCode(200).extract().response().asString();
	
		JsonPath js2 = RawToJson.parseJson(deleteBookresponse);
		System.out.println(js2.get("msg")); 
	
		*/
		
		
		//read book from jsonfile
		
		given().log().all().header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\chitra_palanisamy\\eclipse-workspace\\EndToEnd\\src\\main\\java\\resources\\addBook.json"))))
		.when().post("/Library/Addbook.php")
				.then().log().all().assertThat().statusCode(200);
			}
	
	@DataProvider
	public Object[][] getBookData() {
		Object[][] data = new Object[][]{{"isbn","aislenum"}};
		//data[0][0] = "isbn01";
		//data[0][1] = "aisle01";
//		data[1][0] = "isbn21";
//		data[1][1] = "aisle21";
//		data[2][0] = "isbn31";
//		data[2][1] = "aisle31";
				
		return data;
	}


}
