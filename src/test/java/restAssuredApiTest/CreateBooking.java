package restAssuredApiTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.ITestContext;
import org.hamcrest.Matchers;

import io.qameta.allure.Description;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
public class CreateBooking {
	
	 static int bookingid ;
	 
	 Response response;	// Response type variable 
	 
	 HashMap<String, Object> map=new HashMap<String, Object>();
	 
	@BeforeClass
	public void postdata(ITestContext context)
	{
		// Calling methods to generate random data for POST call 
		String firstname = RestUtils.getFirstName(); 
		 String lastname = RestUtils.getLastName();
		  int totalprice = RestUtils.getTotalPrice();
		  boolean depositpaid = RestUtils.getDepositPaid();
		  String additionalneeds = RestUtils.getAdditionalNeeds();
		  String checkin = RestUtils.getCheckInDate();
		  String checkout = RestUtils.getCheckOutDate();
		// Create HASHMAP for storing POST request body data 
		 
		 
			// Request Body for CreateBooking 
		map.put("firstname",firstname);
		map.put("lastname",lastname);
		map.put("totalprice",totalprice);
		map.put("depositpaid",depositpaid);
		map.put("additionalneeds",additionalneeds);
		// create nested HASHMAP to store nested JSON body 
		Map<String, String> bookingDates = new HashMap<String, String>();   
		 bookingDates.put("checkin",checkin);
		 bookingDates.put("checkout",checkout);
		 map.put("bookingdates", bookingDates);
		 
		 context.setAttribute("firstname", firstname);
		 context.setAttribute("lastname", lastname);
		 context.setAttribute("totalprice", totalprice);
		 context.setAttribute("depositpaid", depositpaid);
		 context.setAttribute("additionalneeds", additionalneeds);
		 context.setAttribute("checkin", checkin);
		 context.setAttribute("checkout", checkout);
		 
		 
		RestAssured.baseURI= RestUtils.getBaseURI();
		RestAssured.basePath="/booking" ;
	}
	@Test(priority=0)
	@Description("POST : Create Booking")
	public  void testpost(ITestContext context)
	{
		//System.out.print("inpostclass"+bookingid);
		
		Response response =
				RestAssured
					.given().filter(new AllureRestAssured())
							.contentType(ContentType.JSON)
							.accept("application/json")
							.body(map)	
							// .log().body()
						.when()
							.post()
						.then()
							// .log().body()
							.statusCode(200)
							.statusLine("HTTP/1.1 200 OK")
							// Verify Response fields are same as passed in request 
							.body("booking.firstname", Matchers.equalTo(context.getAttribute("firstname")))
							.body("booking.lastname", Matchers.equalTo(context.getAttribute("lastname")))
							.body("booking.totalprice", Matchers.equalTo(context.getAttribute("totalprice")))
							.body("booking.depositpaid", Matchers.equalTo(context.getAttribute("depositpaid")))
							.body("booking.bookingdates.checkout", Matchers.equalTo(context.getAttribute("checkout")))
							.body("booking.bookingdates.checkin", Matchers.equalTo(context.getAttribute("checkin")))
							.body("booking.additionalneeds", Matchers.equalTo(context.getAttribute("additionalneeds")))
						    .extract().response();
			AssertJUnit.assertTrue(response.getBody().asString().contains("bookingid"));  // Verify response has bookingid field present 
			bookingid = response.jsonPath().get("bookingid");  // Save bookingId to pass onto next calls 
			// System.out.print("inpostclassafterpostrequest"+bookingid);
			
			context.setAttribute("id",bookingid);
}
}
