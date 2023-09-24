package restassuredApiTests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;


public class CreateToken {
	
	static String token ; 
	static Response response ;
	public static HashMap<String, String> map=new HashMap<String, String>();
	
	@BeforeClass
	public void postdata()
	{
		map.put("username",RestUtils.getUserName());
		map.put("password",RestUtils.getPassword());
		
		RestAssured.baseURI= RestUtils.getBaseURI();
		RestAssured.basePath="/auth" ;
	}
	
	@Test(description = "POST : Create Token")
	public void  testpost()
	{
	response = 
			RestAssured 
			.given().filter(new AllureRestAssured())
				.contentType("application/json")
				.body(map)
				//.log().all()
			.when()
				.post()
			.then()
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				//.log().all() 
				.extract().response();
			AssertJUnit.assertTrue(response.getBody().asString().contains("token")); // Verify Response has token field 
	token  = response.jsonPath().get("token"); //Storing token to pass onto other methods
		 
	}
	
}
