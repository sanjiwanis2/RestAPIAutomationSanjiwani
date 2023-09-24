package restassuredApiTests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.HashMap;
import java.util.Map;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PartialUpdateBooking {
	
	 Response response  ;	// Response type variables to save response JSON 
	 static int bookingid  ;
	 static String token ;
	 
	 		// Calling methods to generate random data for POST call
		static String firstname = RestUtils.getFirstName(); 
		static String lastname = RestUtils.getLastName();
		static int totalprice = RestUtils.getTotalPrice();
		static boolean depositpaid = RestUtils.getDepositPaid();
		static String additionalneeds = RestUtils.getAdditionalNeeds();
		static String checkin = RestUtils.getCheckInDate();
		static String checkout = RestUtils.getCheckOutDate();
		
		// // Calling methods to generate random data for PATCH call
		String patchfirstname = RestUtils.getFirstName(); 
		 String patchlastname = RestUtils.getLastName();
		 int patchtotalprice = RestUtils.getTotalPrice();
		 boolean patchdepositpaid = RestUtils.getDepositPaid();
		 String patchadditionalneeds = RestUtils.getAdditionalNeeds();
		 String patchcheckin = RestUtils.getCheckInDate();
		 String patchcheckout = RestUtils.getCheckOutDate();
		
		
		// create HASHMAP for storing POST request body data 
		 public static HashMap<String, Object> map=new HashMap<String, Object>();
		 public static HashMap<String, String> tokenbody=new HashMap<String, String>();
		 public static HashMap<String, Object> patchbody =new HashMap<String, Object>();
		 
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
				 Map<String, String> bookingDates = new HashMap<String, String>();   // Nested hashmap 
			 bookingDates.put("checkin",checkin);
			 bookingDates.put("checkout",checkout);
			 map.put("bookingdates", bookingDates);
			 
			 // Request Body for CreteToken
			 tokenbody.put("username",RestUtils.getUserName());
			 tokenbody.put("password",RestUtils.getPassword());
			 
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
	
		// POST REQUEST : CREATE TOKEN 
		@Test(priority=0)  
		public void  Createtoken()
		{
			//System.out.println("\n In 0 priority\t ");
			response = 
			RestAssured 
				.given()
					.contentType("application/json")
					.body(tokenbody)
					.basePath("/auth")
				// .log().body()
				.when()
					.post()
				.then()
					//.log().body() 
					.extract().response();
		   token = response.jsonPath().get("token");	
		   	// System.out.println("token is " + token + "\n");
		}
		
		
		// POST REQUEST : CREATE BOOKING 
		@Test(priority=1)
		public void Createbooking()
		{
		//	System.out.println("In 1 priority\n");

			 response =
					RestAssured
						.given()
								.contentType(ContentType.JSON)
								.body(map)	
								.basePath("/booking")
								//.log().body()
							.when()
								.post()
								
							.then()
								// .log().body()
								.statusCode(200)
							    .extract()
								.response();
				bookingid = response.jsonPath().get("bookingid");	// Save Booking Id to pass on to PUT request 
				// System.out.println("Booking id is\n" +bookingid );
		}
			
		
		
		// PATCH REQUEST : UPDATE BOOKING - ALL FIELDS 
		@Test(priority=2)
		public void UpdateAllFields()
		{
			
			//	System.out.println("In 3 priority\n");
			String token1 = "token=" + token ;
			//System.out.println("token=" + token );
			
			response =
					RestAssured
						.given()
								.contentType(ContentType.JSON)
								.accept("application/json")
								.header("Cookie", token1)
								.basePath("/booking")
								.body(patchbody)	
								// .log().all()
							.when()
								.patch("/{id}", bookingid)
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
		
		@Test(priority=3)
		public void UpdateOnlyFirstName()
		{
			  updatefirstname = RestUtils.getFirstName();
			Map<String, String> authPayload = new HashMap<String, String>(); 
			authPayload.put("firstname",updatefirstname);
			
			String token1 = "token=" + token ;
			 response =
					RestAssured
						.given()
								.contentType(ContentType.JSON)
								.accept("application/json")
								.header("Cookie", token1)
								.basePath("/booking")
								.body(authPayload)
								//.log().all()
							.when()
								.patch("/{id}", bookingid)
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
		@Test(priority=3)
		public void UpdateOnlyLastName()
		{
			String updatelastname = RestUtils.getLastName();
			Map<String, String> authPayload = new HashMap<String, String>(); 
			authPayload.put("lastname",updatelastname);
			
			String token1 = "token=" + token ;
			 response =
					RestAssured
						.given()
								.contentType(ContentType.JSON)
								.accept("application/json")
								.header("Cookie", token1)
								.basePath("/booking")
								.body(authPayload)
								//.log().all()
							.when()
								.patch("/{id}", bookingid)
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
