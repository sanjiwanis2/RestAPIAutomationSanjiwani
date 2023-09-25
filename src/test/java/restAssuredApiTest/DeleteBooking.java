package restAssuredApiTest;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.ITestContext;
import io.qameta.allure.Description;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteBooking {
	
	Response response  ;	// Response type variables to save response JSON 
	
	@Test(priority=0)	
	@Description("DELETE REQUEST : DELETE BOOKING")
	public void Deletebooking(ITestContext context )
	{	
		// System.out.println("entered delete booking");
		
		String username = (String) context.getAttribute("username");
		String password = (String) context.getAttribute("password");
		
		int bookingid = (int) context.getAttribute("id");
		 
		
				 RestAssured
					.given().filter(new AllureRestAssured())
						.auth().preemptive().basic(username,password )
							.contentType("application/json")
							 .basePath("/booking")
							// .log().all()
						.when()
							.delete("/{id}",bookingid )
						.then()
							//  .log().all()
							.statusCode(201)
							.statusLine("HTTP/1.1 201 Created");
						    
	}
	
	@Test(priority=1)	
	@Description("GET REQUEST : VERIFY IF THE BOOKING IS DELETED")
	public void Getdeletedbooking(ITestContext context)
	{
		int bookingid = (int) context.getAttribute("id");
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
