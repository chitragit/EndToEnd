package files;

import io.restassured.path.json.JsonPath;

public class RawToJson {
	
	public static JsonPath parseJson(String response) {
		JsonPath js = new JsonPath(response);
		return js;
	}

}
