package restassuredApiTests;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.hamcrest.Matchers;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class GetBookingsByName extends CreateBooking{

	@Test(description="GET REQUEST : GET BOOKINGS BY FIRSTNAME & LASTNAME")
	public void getBookingsForName()
	{
		RestAssured 
				.given().filter(new AllureRestAssured())
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
