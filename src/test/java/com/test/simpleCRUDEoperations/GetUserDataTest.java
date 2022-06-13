package com.test.simpleCRUDEoperations;


import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.constants.Endpoints;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class GetUserDataTest {
	
	@BeforeClass
	public static void init() {
		RestAssured.baseURI ="https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
	}
	
	public static String loginToTekarchApi() {
		Response response=RestAssured.
				given().contentType("application/json").
				body("{\"username\":\"lintu.joseph06@ta.com\",\"password\":\"lintu.joseph06@123\"}").
				when().log().all().
				post(Endpoints.LOGIN);
		String token=response.body().jsonPath().getString("[0].token");
		return token;
	}
	@Test(priority = 2,enabled = true)
	public static void getUserData() throws IOException {
		
		
		Header header = new Header("token",loginToTekarchApi());
		Response response = RestAssured.given().header(header)
				.when()
				.get("/getdata");
		//response.then().log().all();
		response.then().statusCode(200);
		response.body().prettyPrint();
		System.out.println("number of records="+response.jsonPath().get("$.size()"));
		String userId = response.jsonPath().get("[0].userid");
		String id=response.jsonPath().get("[0].id");
		System.out.println(response.jsonPath().get("[0].departmentno"));
		System.out.println("first entry userdata userid and id is="+userId+" and "+id);
	}

}