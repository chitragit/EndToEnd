package restassuredpractise;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.AcademyCourses;
import pojo.webAutomation;

import static io.restassured.RestAssured.*;

public class OAuthPractise1 {
	
	@Test
	public void oAuthGoogle() {
		
		//
		String[] courseTitles = {"Selnium Webdriver Java", "Cypress", "Protractor"};
		
//Use selenium to get url
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\chitra_palanisamy\\eclipse-workspace\\PractiseProject\\src\\main\\java\\resources\\chromedriver.exe");
		
//		WebDriver driver = new ChromeDriver();
//		//driver.get("https://www.google.com");
//		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
//		
//		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("chitrabharathi0904@gmail.com");
//		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
//		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("123");
//		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
//		
//		String codeUrl = driver.getCurrentUrl(); //issue with google
	

		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F4AHoMTRRtJJk_aIsgkpkmyKOor9cO2RD4BVNslEjrx9kZEaqbmJg-MTfdvJU6SK3tqRt-2426L_MQzgGzXmJNJU&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=2&prompt=none#";
		String partialUrl = url.split("code=")[1];
		String  code = partialUrl.split("&scope=")[0];
		
		System.out.println(code);
		
		String accessTokenResponse =
		given()
		.urlEncodingEnabled(false)
		.queryParam("code", code)
		.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParam("grant_type", "authorization_code")
		.when().log().all().post("https://www.googleapis.com/oauth2/v4/token")
		.then().assertThat().statusCode(200).extract().response()
		.asString();
		
		System.out.println(accessTokenResponse);
		
		
		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken = js.get("access_token");
		
		System.out.println(accessToken);
		
		
		/*String response =
		given().queryParam("access_token", accessToken).
		when().log().all().get("https://rahulshettyacademy.com/getCourse.php").asString();
		*/
		
		AcademyCourses academyResponse = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON).
		when().get("https://rahulshettyacademy.com/getCourse.php").as(AcademyCourses.class);
		
		System.out.println(academyResponse.getCourses().getWebAutomation().get(0).getCourseTitle());

		System.out.println(academyResponse.getCourses().getWebAutomation().get(0).getPrice());
		
		//get all course title from web
		
		ArrayList<String> actualList = new ArrayList<String>();
		
		
		
		List<webAutomation> webAutomationList= academyResponse.getCourses().getWebAutomation();
		int totalCourses = academyResponse.getCourses().getWebAutomation().size();
		
		for(int i=0; i<totalCourses; i++) {
			
			System.out.println(webAutomationList.get(i).getCourseTitle());
			actualList.add(webAutomationList.get(i).getCourseTitle());
		
		}
		
	
		List<String> expectedList = Arrays.asList(courseTitles);
		
		Assert.assertEquals(actualList, expectedList);
		
		
	}

}
