package restAssuredApiTest;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.ITestContext;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class GetBookingsByCheckinCheckout {
	
	@Test(description="GET REQUEST : GET BOOKINGS BY FILTER CHECKIN & CHECKOUT  DATE")
	public void getBookingsForCheckinCheckout(ITestContext context)
	{		
			RestAssured 
				.given().filter(new AllureRestAssured())
					.accept("application/json")
					.baseUri(RestUtils.getBaseURI())
					.basePath("/booking")
					.param("checkin",context.getAttribute("checkin"))
					.param("checkout", context.getAttribute("checkout"))
					// .log().all()
				.when()
					.get()
				.then()
					.statusCode(200)
					.statusLine("HTTP/1.1 200 OK");
					// .log().body();
}
	
}
