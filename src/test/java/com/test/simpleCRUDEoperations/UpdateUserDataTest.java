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

public class UpdateUserDataTest extends UserServiceHelper{
	
	String userId;
	String id;
	@BeforeClass
	public void loginToApi() {
		Response res = LoginToApplication();
		res.then().statusCode(201);
		System.out.println("Sucessfully logged into the Application");
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
	@Test(priority=1)
	public void CreateUser() {
		Response res  = addUserData();
		res.then().statusCode(201);
		//AssertJUnit.assertEquals(status, "success");
	}
	@Test(priority=3,enabled=true)
	public void UpdateUser()
	{
		//getUsers();
		Response res  = updateUserData(userId,id);
		res.then().statusCode(200);
		getUsers();
	}
	

}
