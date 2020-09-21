package restassuredpractise;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.MapPayload;
import files.RawToJson;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;


public class JiraPractise1 {

	@Test(enabled=false)
	public void jiraCreateIssue() {
	RestAssured.baseURI="http://localhost:8080";
	
	SessionFilter session = new SessionFilter();
	
	//login
	given().filter(session).log().all().header("Content-Type","application/json").body("{\"username\":\"chitrapalanisamy09\",\"password\":\"P@ssw0rd\"}")
	.when().post("/rest/auth/1/session")
	.then().log().all().assertThat().statusCode(200);
	
	/*
	//add issue
	given().header("Content-Type","application/json").filter(session).body(MapPayload.addJiraIssue())
	.when().post("/rest/api/2/issue")
	.then().log().all().assertThat().statusCode(201);


	//add comment
	given().pathParam("issueId", "AUT-4").header("Content-Type","application/json").filter(session)
	.body("{\r\n" + 
			"  \"body\": \"comment from restapi...\",\r\n" + 
			"  \"visibility\": {\r\n" + 
			"    \"type\": \"role\",\r\n" + 
			"    \"value\": \"Administrators\"\r\n" + 
			"  }\r\n" + 
			"}")
	.when().post("/rest/api/2/issue/{issueId}/comment")
	.then().assertThat().statusCode(201);
	
	*/
		//add attachment
	
	given().pathParam("issueId", "AUT-4").header("Content-Type","multipart/form-data")
	.header("X-Atlassian-Token","no-check").filter(session)
	.multiPart("file", new File("C:\\Users\\chitra_palanisamy\\eclipse-workspace\\EndToEnd\\src\\main\\java\\resources\\picture.png"))
	.when().post("/rest/api/2/issue/{issueId}/attachments")
	.then().log().all().assertThat().statusCode(200);

	
	}

	@Test
	public void restrictResult() {
	
		RestAssured.baseURI="http://localhost:8080";
		
		SessionFilter session = new SessionFilter();
		
		//login
		given().filter(session).log().all().header("Content-Type","application/json").body("{\"username\":\"chitrapalanisamy09\",\"password\":\"P@ssw0rd\"}")
		.when().post("/rest/auth/1/session")
		.then().log().all().assertThat().statusCode(200);
		
		
		
		String expectedMsg = "rest api validation -- final!";
		//add comment
		String commentResponse = given().pathParam("issueId", "AUT-4").header("Content-Type","application/json").filter(session)
		.body("{\r\n" + 
				"  \"body\": \""+expectedMsg+"\",\r\n" + 
				"  \"visibility\": {\r\n" + 
				"    \"type\": \"role\",\r\n" + 
				"    \"value\": \"Administrators\"\r\n" + 
				"  }\r\n" + 
				"}")
		.when().post("/rest/api/2/issue/{issueId}/comment")
		.then().log().all().assertThat().statusCode(201).extract().response().asString();
		
		JsonPath js1 = RawToJson.parseJson(commentResponse);
		String addentCommentId = js1.get("id").toString();
		
		
	System.out.println(	"addentCommentId" + addentCommentId);
		
		//get issue -- with comments alone in json

		
		String comments = 
		given().filter(session).pathParam("issueId", "AUT-4").queryParam("fields", "comment")
		.when().get("/rest/api/2/issue/{issueId}")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(comments);
		
		JsonPath js = RawToJson.parseJson(comments);
		//System.out.println(js.get("fields.comment.comments[0].body"));
		
		int commentCount = js.getInt("fields.comment.comments.size()");
		for(int i=0; i<commentCount; i++) {
			System.out.println(js.getString("fields.comment.comments["+i+"].id"));
			String currentId = js.get("fields.comment.comments["+i+"].id").toString();
			if(currentId.equalsIgnoreCase(addentCommentId)) {
				System.out.println(js.getString("fields.comment.comments["+i+"].id"));
				System.out.println(js.getString("fields.comment.comments["+i+"].body"));
				Assert.assertEquals(js.getString("fields.comment.comments["+i+"].body"), expectedMsg);
				break;
			}
		}
		
		
	}
	
	
}
