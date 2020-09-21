package restassuredpractise;

import org.testng.annotations.Test;

import files.MapPayload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParsing {

	@Test
	public void jsonParsing() {
		
		JsonPath js = new JsonPath(MapPayload.mockResponse());
		System.out.println(js.get("dashboard"));
		
		int count = js.getInt("courses.size()");
		int total = 0;
		for(int i=0; i<count; i++) {
			System.out.println(js.get("courses["+i+"].title"));
			
			total = total + js.getInt("courses["+i+"].price")*js.getInt("courses["+i+"].copies");
			System.out.println(total + "and" + js.getInt("dashboard.purchaseAmount"));
			
		}
	}
}
