package com.test.simpleCRUDEoperations;

import java.io.IOException;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.constants.Endpoints;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LoginToApi {
	@BeforeClass
	public static void init() {
		RestAssured.baseURI ="https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
	}
	
	@Test
	public static void loginToTekarchApi() throws IOException {
		
		
		Response response=RestAssured.
			given().contentType("application/json").
			body("{\"username\":\"lintu.joseph06@ta.com\",\"password\":\"lintu.joseph06@123\"}").
			when().log().all().
			
			post(Endpoints.LOGIN);
		
		
		response.then().log().all();
		response.then().statusCode(HttpStatus.SC_CREATED);//201 is the code //you can also enter code 
		//response.then().extract().body().asString();
		response.body().prettyPrint();
		String token=response.body().jsonPath().getString("[0].token");//$.token, $.[0].token  not working
		String userId=response.body().jsonPath().getString("[0].userid");
		System.out.println("TOKEN ="+token);
		System.out.println("USER ID ="+userId);
		//return token;
		
		}


}
