package restassuredApiTests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.*;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;

import java.util.HashMap;


public class CreateToken {
	
	
	public static HashMap map=new HashMap();
	
	@BeforeClass
	public void postdata()
	{
		map.put("username",RestUtils.getUserName());
		map.put("password",RestUtils.getPassword());
		
		RestAssured.baseURI= RestUtils.getBaseURI();
		RestAssured.basePath="/auth" ;

	}
	Response response ;
	@Test
	public void testpost()
	{
		
		
		response = 
		given()
			.contentType("application/json")
			.body(map)
		
		.when()
			.post()
		
		.then()
			.statusCode(200)
			.and()
			.statusLine("HTTP/1.1 200 OK")
			.log().all() 
			.extract().response();
			
			
	}
	
	@Test
	public void testresponsevalidations()
	{
		AssertJUnit.assertTrue(response.getBody().asString().contains("token"));

	}
	
	

}
