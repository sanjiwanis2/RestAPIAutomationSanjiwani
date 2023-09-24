package restassuredApiTests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.HashMap;
import java.util.Map;
import org.hamcrest.Matchers;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateBooking {
	
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
		
		// // Calling methods to generate random data for PUT call
		String putfirstname = RestUtils.getFirstName(); 
		 String putlastname = RestUtils.getLastName();
		 int puttotalprice = RestUtils.getTotalPrice();
		 boolean putdepositpaid = RestUtils.getDepositPaid();
		 String putadditionalneeds = RestUtils.getAdditionalNeeds();
		 String putcheckin = RestUtils.getCheckInDate();
		 String putcheckout = RestUtils.getCheckOutDate();
		
		
		// create HASHMAP for storing POST request body data 
		 public static HashMap<String, Object> map=new HashMap<String, Object>();
		 public static HashMap<String, String> tokenbody=new HashMap<String, String>();
		 public static HashMap<String, Object> putbody =new HashMap<String, Object>();
		 
		@BeforeClass 
		public void postdata()
		{		
			// Request Body for CreateBooking 
			map.put("firstname",firstname);
			map.put("lastname",lastname);
			map.put("totalprice",totalprice);
			map.put("depositpaid",depositpaid);
			map.put("additionalneeds",additionalneeds);
			
					// Create nested HASHMAP to store nested JSON body 
				 Map<String, String> bookingDates = new HashMap<String, String>();   
			 bookingDates.put("checkin",checkin);
			 bookingDates.put("checkout",checkout);
			 map.put("bookingdates", bookingDates);
			 
			 // Request Body for CreteToken
			 
			 tokenbody.put("username",RestUtils.getUserName());
			 tokenbody.put("password",RestUtils.getPassword());
			 
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
		
		
		@Test(priority=0  , description = "CREATE TOKEN")
		// Generate AUTH Token 
		public void  Createtoken()
		{
			// System.out.println("\n In 0 priority\t ");
			response = 
					RestAssured 
			.given().filter(new AllureRestAssured())
				.contentType("application/json")
				.body(tokenbody)
				.basePath("/auth")
				// .log().body()
			.when()
				.post()
				
			.then()
				// .log().body() 
				.extract().response();
		   token = response.jsonPath().get("token");	
		
			 
			// System.out.println("token is " + token + "\n");
		}
		
		
		@Test(priority=1 , description = "CREATE BOOKING")
		public void Createbooking()
		{
		//	System.out.println("In 1 priority\n");

			 response =
					RestAssured
						.given().filter(new AllureRestAssured())
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
			
		
		
		@Test(priority=2 , description = "UPDATE BOOKING")
		public void Updatebooking()
		{
			
			//	System.out.println("In 3 priority\n");
			String token1 = "token=" + token ;
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
								.put("/{id}", bookingid)
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
