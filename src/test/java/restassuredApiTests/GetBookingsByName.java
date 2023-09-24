package restassuredApiTests;

import org.testng.annotations.Test;
import org.hamcrest.Matchers;
import io.restassured.RestAssured;

public class GetBookingsByName extends CreateBooking{

	@Test
	public void getBookingsForName()
	{
		// GET REQUEST : GET BOOKINGS BY FIRSTNAME & LASTNAME
		RestAssured 
				.given()
					.accept("application/json")
					.baseUri(RestUtils.getBaseURI())
					.basePath("/booking")
					.param("firstname",firstname)
					.param("lastname", lastname)
					//.log().all()
				.when()
					.get()
				.then()
					.statusCode(200)
					.statusLine("HTTP/1.1 200 OK")
					.log().body()
					.body("bookingid[0]",Matchers.equalTo(bookingid))
					.extract().response();
		
	}
}
