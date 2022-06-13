package com.test.simpleCRUDEoperations;

import java.io.IOException;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.constants.Endpoints;
import com.test.helpers.UserServiceHelper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LoginToApi extends UserServiceHelper {
	
	@Test
	public void loginToApi() {
		Response res = LoginToApplication();
		res.then().statusCode(201);
		System.out.println("Sucessfully logged into the Application");
	}
		
		


}
