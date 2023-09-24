package restassuredApiTests;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class HealthCheck {
	
	@Test(description = "GET HEALTH CHECK : PING")
	public void getping()
	{
		RestAssured 
			.given().filter(new AllureRestAssured())
				.baseUri(RestUtils.getBaseURI())
				.basePath("ping")
			.when()
				.get()
			.then()
				.statusCode(201)
				.statusLine("HTTP/1.1 201 Created");
	}
}
