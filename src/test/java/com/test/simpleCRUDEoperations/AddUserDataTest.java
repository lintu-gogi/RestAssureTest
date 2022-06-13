package com.test.simpleCRUDEoperations;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.JsonObject;
import com.test.constants.Endpoints;
import com.test.constants.SourcePath;
import com.test.helpers.UserServiceHelper;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class AddUserDataTest extends UserServiceHelper{
	@BeforeClass
	public void loginToApi() {
		Response res = LoginToApplication();
		res.then().statusCode(201);
		System.out.println("Sucessfully logged into the Application");
	}
	@Test
	public void CreateUser() {
		Response res  = addUserData();
		res.then().statusCode(201);
		//AssertJUnit.assertEquals(status, "success");
	}

}

