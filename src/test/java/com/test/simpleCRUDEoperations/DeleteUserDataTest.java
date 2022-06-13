package com.test.simpleCRUDEoperations;


import java.io.IOException;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.constants.Endpoints;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class DeleteUserDataTest {
	
	@BeforeClass
	public static void init() {
		RestAssured.baseURI ="https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
	}
	
	public static String loginToTekarchApi() {
		Response response=RestAssured.
				given().contentType("application/json").
				body("{\"username\":\"divyashree@ta.com\",\"password\":\"divya@123\"}").
				when().log().all().
				post(Endpoints.LOGIN);
		String token=response.body().jsonPath().getString("[0].token");
		return token;
	}
	@Test
	public static void TC_004_deleteUserData() throws IOException {
		// search for perticular record using account number then extract id and userid and send it as body
		String userId=null;
		String id=null;
		Header header = new Header("token",loginToTekarchApi());
		Response response = RestAssured.given().contentType("application/json").header(header)
				.body("{\"id\":\"eiMoSxJoGteLSfI4rDEE\",\"userid\":\"zhFI4oQlHjP2cn3mW3GP\"}")
				.when()
				.delete("/deleteData");

		response.then().statusCode(200);
		String status = response.jsonPath().get("status");
		AssertJUnit.assertEquals(status, "success");
		System.out.println("STATUS ="+status);
		

	} 


}