package restAssuredApiTest;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.ITestContext;
import org.hamcrest.Matchers;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class GetBookingsByName {

	@Test(description="GET REQUEST : GET BOOKINGS BY FIRSTNAME & LASTNAME")
	public void getBookingsForName(ITestContext context)
	{
		RestAssured 
				.given().filter(new AllureRestAssured())
					.accept("application/json")
					.baseUri(RestUtils.getBaseURI())
					.basePath("/booking")
					.param("firstname",context.getAttribute("firstname"))
					.param("lastname", context.getAttribute("lastname"))
					//.log().all()
				.when()
					.get()
				.then()
					.statusCode(200)
					.statusLine("HTTP/1.1 200 OK")
					.log().body()
					.body("bookingid[0]",Matchers.equalTo(context.getAttribute("id")))
					.extract().response();

}
	}
