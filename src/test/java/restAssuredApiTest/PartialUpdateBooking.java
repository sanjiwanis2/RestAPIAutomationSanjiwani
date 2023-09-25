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

public class PartialUpdateBooking {
	
	 Response response  ;	// Response type variables to save response JSON 
	 static String token ;
	 
		
		// // Calling methods to generate random data for PATCH call
		String patchfirstname = RestUtils.getFirstName(); 
		 String patchlastname = RestUtils.getLastName();
		 int patchtotalprice = RestUtils.getTotalPrice();
		 boolean patchdepositpaid = RestUtils.getDepositPaid();
		 String patchadditionalneeds = RestUtils.getAdditionalNeeds();
		 String patchcheckin = RestUtils.getCheckInDate();
		 String patchcheckout = RestUtils.getCheckOutDate();
		
		
		// create HASHMAP for storing POST request body data 
		 public static HashMap<String, Object> patchbody =new HashMap<String, Object>();
		 
		@BeforeClass 
		public void postdata()
		{		
			 // Request Body for PATCH call : Update Booking 
			 patchbody.put("firstname",patchfirstname);
			 patchbody.put("lastname",patchlastname);
			 patchbody.put("totalprice",patchtotalprice);
			 patchbody.put("depositpaid",patchdepositpaid);
			 patchbody.put("additionalneeds",patchadditionalneeds);
			 Map<String, String> bookingDates1 = new HashMap<String, String>();   // Nested hashmap 
				 bookingDates1.put("checkin",patchcheckin);
				 bookingDates1.put("checkout",patchcheckout);
				 patchbody.put("bookingdates", bookingDates1);
			 
				// Base_URI 	
			 RestAssured.baseURI= RestUtils.getBaseURI();
			
			}
		
		// PATCH REQUEST : UPDATE BOOKING - ALL FIELDS 
		@Test(priority=0 , description = "Update Booking All Fileds")
		public void UpdateAllFields(ITestContext context )
		{
			
			
			//	System.out.println("In 3 priority\n");
			String token1 = "token=" + context.getAttribute("token") ;
			//System.out.println("token=" + token );
			
			response =
					RestAssured
						.given().filter(new AllureRestAssured())
								.contentType(ContentType.JSON)
								.accept("application/json")
								.header("Cookie", token1)
								.basePath("/booking")
								.body(patchbody)	
								// .log().all()
							.when()
								.patch("/{id}", context.getAttribute("id"))
							.then()
								//.log().body()
								.statusCode(200)
								.statusLine("HTTP/1.1 200 OK")
								.body("firstname", Matchers.equalTo(patchfirstname))
								.body("lastname", Matchers.equalTo(patchlastname))
								.body("totalprice", Matchers.equalTo(patchtotalprice))
								.body("depositpaid", Matchers.equalTo(patchdepositpaid))
								.body("bookingdates.checkout", Matchers.equalTo(patchcheckout))
								.body("bookingdates.checkin", Matchers.equalTo(patchcheckin))
								.body("additionalneeds", Matchers.equalTo(patchadditionalneeds))
							    .extract()
								.response();
		}
		
		// PATCH REQUEST : UPDATE BOOKING - ONLY FIRSTNAME
		
		public String updatefirstname;
		
		@Test(priority=3 , description = "UPDATE BOOKING - ONLY FIRSTNAME")
		public void UpdateOnlyFirstName(ITestContext context)
		{
			  updatefirstname = RestUtils.getFirstName();
			Map<String, String> authPayload = new HashMap<String, String>(); 
			authPayload.put("firstname",updatefirstname);
			
			String token1 = "token=" + context.getAttribute("token") ;
			 response =
					RestAssured
						.given().filter(new AllureRestAssured())
								.contentType(ContentType.JSON)
								.accept("application/json")
								.header("Cookie", token1)
								.basePath("/booking")
								.body(authPayload)
								//.log().all()
							.when()
								.patch("/{id}", context.getAttribute("id"))
							.then()
								//.log().body()
								.statusCode(200)
								.statusLine("HTTP/1.1 200 OK")
								// Verify Only FirstName is updated 
								.body("firstname", Matchers.equalTo(updatefirstname)) 
								// Other all fields value should not be changed 
								.body("lastname", Matchers.equalTo(patchlastname)) 
								.body("totalprice", Matchers.equalTo(patchtotalprice))
								.body("depositpaid", Matchers.equalTo(patchdepositpaid))
								.body("bookingdates.checkout", Matchers.equalTo(patchcheckout))
								.body("bookingdates.checkin", Matchers.equalTo(patchcheckin))
								.body("additionalneeds", Matchers.equalTo(patchadditionalneeds))
							    .extract().response();
		
		}
		
		// PATCH REQUEST : UPDATE BOOKING - ONLY LASTNAME
		@Test(priority=3 , description = "UPDATE BOOKING - ONLY LASTNAME")
		public void UpdateOnlyLastName(ITestContext context)
		{
			String updatelastname = RestUtils.getLastName();
			Map<String, String> authPayload = new HashMap<String, String>(); 
			authPayload.put("lastname",updatelastname);
			
			String token1 = "token=" + context.getAttribute("token") ;
			 response =
					RestAssured
						.given().filter(new AllureRestAssured())
								.contentType(ContentType.JSON)
								.accept("application/json")
								.header("Cookie", token1)
								.basePath("/booking")
								.body(authPayload)
								//.log().all()
							.when()
								.patch("/{id}", context.getAttribute("id"))
							.then()
								//.log().body()
								.statusCode(200)
								.statusLine("HTTP/1.1 200 OK")
								// Verify Only LastName is updated 
								.body("lastname", Matchers.equalTo(updatelastname))
								// Other all fields value should not change
								.body("firstname", Matchers.equalTo(updatefirstname)) 
								.body("totalprice", Matchers.equalTo(patchtotalprice))
								.body("depositpaid", Matchers.equalTo(patchdepositpaid))
								.body("bookingdates.checkout", Matchers.equalTo(patchcheckout))
								.body("bookingdates.checkin", Matchers.equalTo(patchcheckin))
								.body("additionalneeds", Matchers.equalTo(patchadditionalneeds))
							    .extract().response();
		
		}
}
