package com.test.tests;

import java.util.ArrayList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.constants.Endpoints;
import com.test.helpers.UserServiceHelper;
import com.test.models.AddUserPojo;
import com.test.models.DeleteUserPogo;
import com.test.models.DeserializationPogo;
import com.test.models.DeserializeGetUserPogo;
import com.test.models.UpdateUserPojo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class ApiTestScriptsEndToEnd extends UserServiceHelper{

	static String token1;
	static String userId;
	static String id;

	@BeforeClass
	/*public static void init() {
		RestAssured.baseURI =getBaseUri();
	}*/
	
		@Test(priority=0)
		public void loginToApi() {
			Response res = LoginToApplication();
			res.then().statusCode(201);
			System.out.println("Sucessfully logged into the Application");
		/*	Response res=RestAssured.given().contentType(ContentType.JSON)
			.body("{\"username\":\"lintu.joseph06@ta.com\",\"password\":\"lintu.joseph06@123\"}")
		//	.body(matchesJsonSchemaInClasspath("LoginSchema.json"))
			.when()
			.post(Endpoints.LOGIN);

			//res.prettyPrint();
			
			 token1= res.jsonPath().get("[0].token");
			System.out.println("extracted token="+token1);
			//return token;*/
		}
		@Test(priority=2)
		public void getUsers() {
			DeserializeGetUserPogo[] obj;
			obj=getUserData();
			userId = obj[0].getUserid();
			id = obj[0].getId();
			System.out.println("Total no of Records "+obj.length);
			System.out.println("First User Accont no "+obj[0].getAccountno()+" Department no "
					+obj[0].getDepartmentno()+" Salary "+obj[0].getSalary()+" Pincode code "+obj[0].getPincode()+
					"  User id :"+userId+"  Id :"+id);
			//Storing UserId and Id
			
			
		}
		@Test(priority = 1,enabled = true)
		public void CreateUser() {
			Response res  = addUserData();
			res.then().statusCode(201);
			//getUsers();
			
					
		}
		
		@Test(priority=3,enabled=true)
		public void UpdateUser()
		{
			//getUsers();
			Response res  = updateUserData(userId,id);
			res.then().statusCode(200);
			getUsers();
		
		
			 
		}

		@Test(priority=4,enabled=true)
		public void DeleteUser()
		{
			//getUsers();
			Response res  = deleteUserData(userId,id);
			res.then().statusCode(200);
			
			getUsers();
		}


}
