package restAssuredApiTest;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.ITestContext;
import org.hamcrest.Matchers;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class GetBooking  {

	@Test(description="GET : Get Booking")
	public  void testget(ITestContext context)
	{
		int bookingid = (int) context.getAttribute("id");
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
					//	Verify if Response data 
						.body("firstname", Matchers.equalTo(context.getAttribute("firstname")))
						.body("lastname", Matchers.equalTo(context.getAttribute("lastname")))
						.body("totalprice", Matchers.equalTo(context.getAttribute("totalprice")))
						.body("depositpaid", Matchers.equalTo(context.getAttribute("depositpaid")))
						.body("bookingdates.checkout", Matchers.equalTo(context.getAttribute("checkout")))
						.body("bookingdates.checkin", Matchers.equalTo(context.getAttribute("checkin")))
						.body("additionalneeds", Matchers.equalTo(context.getAttribute("additionalneeds")));
	}
}
