package files;

public class MapPayload {

	public static String addPlace() {
		return "{\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": 50,\r\n" + 
				"  \"name\": \"Frontline house\",\r\n" + 
				"  \"phone_number\": \"(+91) 843 846 9817\",\r\n" + 
				"  \"address\": \"29, side layout, cohen 09\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"shoe park\",\r\n" + 
				"    \"shop\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \"http://google.com\",\r\n" + 
				"  \"language\": \"EN-US\"\r\n" + 
				"}\r\n" + 
				"";
	}
	
	
	public static String mockResponse() {
		return "{\r\n" + 
				"  \"dashboard\": {\r\n" + 
				"    \"purchaseAmount\": 910,\r\n" + 
				"    \"website\": \"rahulshettyacademy.com\"\r\n" + 
				"  },\r\n" + 
				"  \"courses\": [\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"Selenium Python\",\r\n" + 
				"      \"price\": 50,\r\n" + 
				"      \"copies\": 6\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"Cypress\",\r\n" + 
				"      \"price\": 40,\r\n" + 
				"      \"copies\": 4\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"RPA\",\r\n" + 
				"      \"price\": 45,\r\n" + 
				"      \"copies\": 10\r\n" + 
				"    }\r\n" + 
				"  ]\r\n" + 
				"}";
	}
	
	public static String addBookLibApi(String isbn, String aisle) {
		String appiumBook = "{\r\n" + 
				"\r\n" + 
				"\"name\":\"Appium Automation with Java\",\r\n" + 
				"\"isbn\":\""+isbn+"\",\r\n" + 
				"\"aisle\":\""+aisle+"\",\r\n" + 
				"\"author\":\"chitra\"\r\n" + 
				"}\r\n" + 
				"";
		return appiumBook;
		
	}
	
	public static String deleteBook(String id) {
		return "{\r\n" + 
				" \"ID\" : \""+id+"\"\r\n" + 
				"} \r\n" + 
				"";
	}
	
	public static String addJiraIssue() {
		return "{\r\n" + 
				"  \"fields\": {\r\n" + 
				"    \"project\": {\r\n" + 
				"      \"key\": \"AUT\"\r\n" + 
				"    },\r\n" + 
				"    \"summary\": \"Login attempt failed!\",\r\n" + 
				"    \"description\": \"Unable to login in the home page\",\r\n" + 
				"    \"issuetype\": {\r\n" + 
				"      \"name\": \"Bug\"\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}";
	}
}
