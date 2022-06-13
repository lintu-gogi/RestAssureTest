package com.test.simpleCRUDEoperations;


import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.constants.Endpoints;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class UpdateUserDataTest {
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
		System.out.println("token generated is="+token);
		return token;
	}
	
	@Test(enabled = true)
	public static void TC_005_updateUserData() throws IOException {
				
		Header header = new Header("token",loginToTekarchApi());
		Response response = RestAssured.given().contentType("application/json").header(header)
				.body("{\"accountno\":\"TA-1234477\",\"departmentno\":\"7\",\"salary\":\"1700\",\"pincode\":\"111111\",\"userid\":"
						+ "\"o4FDbZMJElX44kOM78mO\",\"id\":\"WFfm4YPKxbjUdQNmjqKX\"}")
				.when()
				.put("/updateData");

		response.then().statusCode(200);
		String status = response.jsonPath().get("status");
		System.out.println("UPDATE STATUS="+status);
		//AssertJUnit.assertEquals(status, "success");
	}


}
