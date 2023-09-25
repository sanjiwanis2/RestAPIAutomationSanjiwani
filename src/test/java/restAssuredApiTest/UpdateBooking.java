package restAssuredApiTest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.ITestContext;
import java.util.HashMap;
import java.util.Map;
import org.hamcrest.Matchers;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateBooking {
	
	 Response response  ;	// Response type variables to save response JSON 
	 static String token ;
	 
		
		// // Calling methods to generate random data for PUT call
		String putfirstname = RestUtils.getFirstName(); 
		 String putlastname = RestUtils.getLastName();
		 int puttotalprice = RestUtils.getTotalPrice();
		 boolean putdepositpaid = RestUtils.getDepositPaid();
		 String putadditionalneeds = RestUtils.getAdditionalNeeds();
		 String putcheckin = RestUtils.getCheckInDate();
		 String putcheckout = RestUtils.getCheckOutDate();
		
		
		// create HASHMAP for storing POST request body data 
		 public static HashMap<String, Object> putbody =new HashMap<String, Object>();
		 
		@BeforeClass 
		public void postdata()
		{		
			 // Request Body for PUT call : Update Booking 
			 putbody.put("firstname",putfirstname);
			 putbody.put("lastname",putlastname);
			 putbody.put("totalprice",puttotalprice);
			 putbody.put("depositpaid",putdepositpaid);
			 putbody.put("additionalneeds",putadditionalneeds);
			 Map<String, String> bookingDates1 = new HashMap<String, String>();   // Nested HASHMAP 
				 bookingDates1.put("checkin",putcheckin);
				 bookingDates1.put("checkout",putcheckout);
				 putbody.put("bookingdates", bookingDates1);
			 
				
			 // Base_URI 
			RestAssured.baseURI= RestUtils.getBaseURI();
			
			}
		
		
		@Test(description = "UPDATE BOOKING")
		public void Updatebooking(ITestContext context)
		{
			String token1 = "token=" + context.getAttribute("token") ;
			//System.out.println("token=" + token );
			response =
					RestAssured
						.given().filter(new AllureRestAssured())
								.contentType(ContentType.JSON)
								.accept("application/json")
								.header("Cookie", token1)
								.basePath("/booking")
								.body(putbody)	
							// 	.log().all()
							.when()
								.put("/{id}", context.getAttribute("id"))
							.then()
								// .log().body()
								.statusCode(200)
								.statusLine("HTTP/1.1 200 OK")
								.body("firstname", Matchers.equalTo(putfirstname))
								.body("lastname", Matchers.equalTo(putlastname))
								.body("totalprice", Matchers.equalTo(puttotalprice))
								.body("depositpaid", Matchers.equalTo(putdepositpaid))
								.body("bookingdates.checkout", Matchers.equalTo(putcheckout))
								.body("bookingdates.checkin", Matchers.equalTo(putcheckin))
								.body("additionalneeds", Matchers.equalTo(putadditionalneeds))
							    .extract()
								.response();
		}
}
