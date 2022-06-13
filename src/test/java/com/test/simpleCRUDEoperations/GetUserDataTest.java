package com.test.simpleCRUDEoperations;


import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.constants.Endpoints;
import com.test.helpers.UserServiceHelper;
import com.test.models.DeserializeGetUserPogo;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class GetUserDataTest extends UserServiceHelper{
	@BeforeClass
	public void loginToApi() {
		Response res = LoginToApplication();
		res.then().statusCode(201);
		System.out.println("Sucessfully logged into the Application");
	}
	
	@Test
	
	public void getUsers() {
		DeserializeGetUserPogo[] obj;
		obj=getUserData();
		System.out.println("Total no of Records "+obj.length);
		System.out.println("First User Accont no "+obj[0].getAccountno()+" Department no "
				+obj[0].getDepartmentno()+" Salary "+obj[0].getSalary()+" Pincode code "+obj[0].getPincode()+
				"  User id :"+obj[0].getUserid()+"  Id :"+obj[0].getId());
		//Storing UserId and Id
		
		
	}
}