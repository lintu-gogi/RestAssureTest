package com.test.simpleCRUDEoperations;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.JsonObject;
import com.test.constants.Endpoints;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class AddUserDataTest {
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
	public static void addUserData() throws IOException {
		// convert json to string online https://tools.knowledgewalls.com/json-to-string
		Header header = new Header("token", loginToTekarchApi());
		Response response = RestAssured.given().contentType("application/json").header(header)
				.body("{\"accountno\":\"DA-eclipse\",\"departmentno\":\"1\",\"salary\":\"1000\",\"pincode\":\"342435\"}")
				//.when().log().all()
				.post("/addData");
		//response.then().log().all();
		response.then().statusCode(201);
		String status = response.jsonPath().get("status");
		
		System.out.println("STATUS ="+status);
		//AssertJUnit.assertEquals(status, "success");
	}

}

