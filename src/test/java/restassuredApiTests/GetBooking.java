package restassuredApiTests;

import org.testng.annotations.Test;
import org.hamcrest.Matchers;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class GetBooking extends CreateBooking{

	@Test(description="GET : Get Booking")
	public  void testget()
	{
				RestAssured
					.given().filter(new AllureRestAssured())
						.accept("application/json")
						.baseUri(RestUtils.getBaseURI())
					.when()
						.get("/{id}",bookingid)
				.then()
						//.log().body()
						.statusCode(200)
						.statusLine("HTTP/1.1 200 OK")
						// Verify if Response data 
						.body("firstname", Matchers.equalTo(firstname))
						.body("lastname", Matchers.equalTo(lastname))
						.body("totalprice", Matchers.equalTo(totalprice))
						.body("depositpaid", Matchers.equalTo(depositpaid))
						.body("bookingdates.checkout", Matchers.equalTo(checkout))
						.body("bookingdates.checkin", Matchers.equalTo(checkin))
						.body("additionalneeds", Matchers.equalTo(additionalneeds));
	}
}
