package restassuredApiTests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.*;
import org.hamcrest.Matchers;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;

import java.util.HashMap;
import java.util.Map;


public class CreateBooking {
	
	
	Response response;	
	
	
	String firstname = RestUtils.getFirstName(); 
	String lastname = RestUtils.getLastName();
	int totalprice = RestUtils.getTotalPrice();
	boolean depositpaid = RestUtils.getDepositPaid();
	String additionalneeds = RestUtils.getAdditionalNeeds();
	String checkin = RestUtils.getCheckInDate();
	String checkout = RestUtils.getCheckOutDate();


public static HashMap map=new HashMap();
	
	@BeforeClass
	public void postdata()
	{
		map.put("firstname",firstname);
		map.put("lastname",lastname);
		map.put("totalprice",totalprice);
		map.put("depositpaid",depositpaid);
		map.put("additionalneeds",additionalneeds);
		
		 Map<String, String> bookingDates = new HashMap();   // Nested hashmap 
		 bookingDates.put("checkin",checkin);
		 bookingDates.put("checkout",checkout);
		 map.put("bookingdates", bookingDates);
		
		RestAssured.baseURI= RestUtils.getBaseURI();
		RestAssured.basePath="/booking" ;
	}
	@Test 
	public void testpost()
	{
				
		Response response =
				RestAssured
						.given()
							.contentType(ContentType.JSON)
							.body(map)	
							.log().body()
						.when()
							.post()
						.then()
							.log().body()
							.statusCode(200)
							.body("booking.firstname", Matchers.equalTo(firstname))
							.body("booking.lastname", Matchers.equalTo(lastname))
							.body("booking.totalprice", Matchers.equalTo(totalprice))
							.body("booking.depositpaid", Matchers.equalTo(depositpaid))
							.body("booking.bookingdates.checkout", Matchers.equalTo(checkout))
							.body("booking.bookingdates.checkin", Matchers.equalTo(checkin))
							.body("booking.additionalneeds", Matchers.equalTo(additionalneeds))
						    .extract()
							.response();
		

		AssertJUnit.assertTrue(response.getBody().asString().contains("bookingid"));
		

	}
	
	
	
	

}
