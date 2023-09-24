package restassuredApiTests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteBooking {
	
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
		
		// create HASHMAP for storing POST request body data 
		 public static HashMap<String, Object> map=new HashMap<String, Object>();
		 public static HashMap<String, String> tokenbody=new HashMap<String, String>();
		 public static HashMap<?, ?> putbody =new HashMap<Object, Object>();
		 
	
	
	
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
		 
		 // Base_URI 
		RestAssured.baseURI= RestUtils.getBaseURI();
		
		}
	
	
	@Test(priority=0) 
	@Description("POST REQUEST : CREATE TOKEN ")
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
			//.log().body() 
			.extract().response();
	   token = response.jsonPath().get("token");	
	
		 
		// System.out.println("token is " + token + "\n");
	}

	@Test(priority=1)
	@Description("POST REQUEST : CREATE BOOKING  ")
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
	 
	@Test(priority=2)	
	@Description("DELETE REQUEST : DELETE BOOKING")
	public void Deletebooking()
	{
		String username = RestUtils.getUserName();
		String password = RestUtils.getPassword();
		
		// System.out.println("entered delete booking");
		
				 RestAssured
					.given().filter(new AllureRestAssured())
						.auth().preemptive().basic(username, password)
							.contentType("application/json")
							 .basePath("/booking")
							// .log().all()
						.when()
							.delete("/{id}", bookingid)
						.then()
							//  .log().all()
							.statusCode(201)
							.statusLine("HTTP/1.1 201 Created");
						    
	}
	
	@Test(priority=3)	
	@Description("GET REQUEST : VERIFY IF THE BOOKING IS DELETED")
	public void Getdeletedbooking()
	{
		response =
				RestAssured
					.given().filter(new AllureRestAssured())
							.basePath("/booking")
							//.log().all()
						.when()
							.get("/{id}", bookingid)
						.then()
							// .log().body()
							.statusCode(404)
						    .extract()
							.response();
	}
}
