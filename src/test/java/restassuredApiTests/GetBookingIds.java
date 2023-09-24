package restassuredApiTests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetBookingIds {

	@Test 
	public void getAllBookings()
	{
	Response response = 
		RestAssured 
				.given()
					.accept("application/json")
					.baseUri(RestUtils.getBaseURI())
					.basePath("/booking")
				.when()
					.get()
				.then()
					.statusCode(200)
					.statusLine("HTTP/1.1 200 OK")
					.extract().response();
		AssertJUnit.assertTrue(response.getBody().asString().contains("bookingid"));
	}
}
