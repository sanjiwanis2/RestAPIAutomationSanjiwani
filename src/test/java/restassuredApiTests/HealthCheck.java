package restassuredApiTests;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class HealthCheck {
	
	@Test
	public void getping()
	{
		RestAssured 
			.given()
				.baseUri(RestUtils.getBaseURI())
				.basePath("ping")
			.when()
				.get()
			.then()
				.statusCode(201)
				.statusLine("HTTP/1.1 201 Created");
	}
}
