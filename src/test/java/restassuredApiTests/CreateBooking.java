package restassuredApiTests;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.hamcrest.Matchers;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
public class CreateBooking {
	
	 static int bookingid ;
	 
	 Response response;	// Response type variable 
	 
	// Calling methods to generate random data for POST call 
	static String firstname = RestUtils.getFirstName(); 
	static String lastname = RestUtils.getLastName();
	static int totalprice = RestUtils.getTotalPrice();
	static boolean depositpaid = RestUtils.getDepositPaid();
	static String additionalneeds = RestUtils.getAdditionalNeeds();
	static String checkin = RestUtils.getCheckInDate();
	static String checkout = RestUtils.getCheckOutDate();
	// Create HASHMAP for storing POST request body data 
	 public static HashMap<String, Object> map=new HashMap<String, Object>();
	 
		@BeforeClass
	public void postdata()
	{
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
		 
		RestAssured.baseURI= RestUtils.getBaseURI();
		RestAssured.basePath="/booking" ;
	}
	@Test(priority=0)
	public  void testpost()
	{
		//System.out.print("inpostclass"+bookingid);
		
		Response response =
				RestAssured
					.given()
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
							.body("booking.firstname", Matchers.equalTo(firstname))
							.body("booking.lastname", Matchers.equalTo(lastname))
							.body("booking.totalprice", Matchers.equalTo(totalprice))
							.body("booking.depositpaid", Matchers.equalTo(depositpaid))
							.body("booking.bookingdates.checkout", Matchers.equalTo(checkout))
							.body("booking.bookingdates.checkin", Matchers.equalTo(checkin))
							.body("booking.additionalneeds", Matchers.equalTo(additionalneeds))
						    .extract().response();
			AssertJUnit.assertTrue(response.getBody().asString().contains("bookingid"));  // Verify response has bookingid field present 
			bookingid = response.jsonPath().get("bookingid");  // Save bookingId to pass onto next calls 
			// System.out.print("inpostclassafterpostrequest"+bookingid);
}
}
