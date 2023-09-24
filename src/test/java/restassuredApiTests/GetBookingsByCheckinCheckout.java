package restassuredApiTests;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class GetBookingsByCheckinCheckout extends CreateBooking{
	
	@Test(description="GET REQUEST : GET BOOKINGS BY FILTER CHECKIN & CHECKOUT  DATE")
	public void getBookingsForCheckinCheckout()
	{		
			RestAssured 
				.given().filter(new AllureRestAssured())
					.accept("application/json")
					.baseUri(RestUtils.getBaseURI())
					.basePath("/booking")
					.param("checkin",checkin)
					.param("checkout", checkout)
					// .log().all()
				.when()
					.get()
				.then()
					.statusCode(200)
					.statusLine("HTTP/1.1 200 OK");
					// .log().body();
}
	
}
