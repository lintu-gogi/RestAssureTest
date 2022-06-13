package com.test.helpers;

import java.util.ArrayList;

import org.testng.annotations.BeforeClass;

import com.test.constants.Endpoints;
import com.test.constants.SourcePath;
import com.test.models.AddUserPojo;
import com.test.models.DeleteUserPogo;
import com.test.models.DeserializationPogo;
import com.test.models.DeserializeGetUserPogo;
import com.test.models.LoginToApplicationPogo;
import com.test.models.UpdateUserPojo;
import com.test.utils.Utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
		
public class UserServiceHelper {
	static Response response;
	static String token;
	static String baseURIInVariable;
	static String userId;
	static String id;
	@BeforeClass
	public String init() {
		RestAssured.baseURI =SourcePath.BASE_URI;
		baseURIInVariable=RestAssured.baseURI;
		return baseURIInVariable;
	}
	public static String getBaseUri()
	{

		return baseURIInVariable;
	}
	public static Response LoginToApplication()
	{
		String username = Utils.username;
		String password = Utils.password;
		LoginToApplicationPogo ob= new LoginToApplicationPogo();
		ob.setUsername(username);
		ob.setPassword(password);
		response = RestAssured
				.given()
				.contentType("application/json")
				.body(ob)
				.when()
				.post(Endpoints.LOGIN);
		String token2=response.jsonPath().get("[0].token");
		System.out.println("Token= "+token2);
		return response;
		
	}
	public static String getToken()
	{
		response = LoginToApplication();
		token= response.jsonPath().get("[0].token");
		return token;
	}
	public static DeserializeGetUserPogo[] getUserData()
	{
		if(token==null)
		{
			token=getToken();
		}
		//String token1=getToken();
		Header header1=new Header("token",token );
		Response res=RestAssured.given().header(header1)
		.when()
		.get(Endpoints.GET_DATA);
		res.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("GetUsersSchema.json"));
		
		res.then().statusCode(200);
		
		/*String accNum=res.jsonPath().get("[0].accountno");
		userId= res.jsonPath().get("[0].userid");
		id= res.jsonPath().get("[0].id");
		String salary= res.jsonPath().get("[0].salary");
		System.out.println("first set account num="+accNum);
		System.out.println("first salary="+salary);
		System.out.println("Userid= "+userId);
		System.out.println("id= "+id);*/
		System.out.println("total num of records="+res.jsonPath().get("$.size()"));
		//Deserialization
		DeserializeGetUserPogo[] listOfRecords= res.as(DeserializeGetUserPogo[].class);
		//System.out.println("Total no of Records "+listOfRecords.length);
		//System.out.println("First User Accont no "+listOfRecords[0].getAccountno());
		return listOfRecords;
		
		/*
		
		//Get a specific record with specific where condition
		for(int i=0;i<listOfRecords.length;i++)
		{
			if(listOfRecords[i].getAccountno().equals("TA-2299889"))
			{
				System.out.println("Account number found");
			}
		}
		ArrayList object = res.jsonPath().get("findAll{it->it.accountno==\"TA-2299889\"}");
		System.out.println("Jason Object content with latest record "+object.get(0));
		*/
	}
	public static Response addUserData()
	{
		if(token==null)
		{
		token = getToken();
		}
		AddUserPojo obj=new AddUserPojo();
		obj.setAccountno("TA-9000999");
		obj.setDepartmentno("11");
		obj.setSalary("2000");
		obj.setPincode("123123");
		
		Header header1=new Header("token",token);
		Response res=RestAssured.given()
				.header(header1)
				.contentType(ContentType.JSON)
				.body(obj)// serialization
				.when()
				.post(Endpoints.ADD_DATA);
		String statusValue= res.jsonPath().get("status");
		System.out.println("Response status after create = "+statusValue);
		return response;
	}
	public static Response updateUserData(String userId,String id)
	{
		if(token==null)
		{
		token = getToken();
		}
		UpdateUserPojo obj=new UpdateUserPojo();
		obj.setAccountno("TA-0000999");
		obj.setDepartmentno("22");
		obj.setSalary("2000");
		obj.setPincode("123123");
		obj.setUserid(userId);
		obj.setId(id);
		
		Header header1=new Header("token",token);
		Response res=RestAssured.given()
				.header(header1)
				.contentType(ContentType.JSON)
				.body(obj)// serialization
				.when()
				.put(Endpoints.UPDATE_DATA);
		//Student stu=res.as(Student.class);
		DeserializationPogo obj2 = res.as(DeserializationPogo.class);
		
		
		//String value=res.jsonPath().get("status");
		System.out.println("Reponse status after update ="+obj2.getStatus());
		return res;
	}
	public static Response deleteUserData(String userId, String id)
	{
		if(token==null)
		{
			token= getToken();
		}
			DeleteUserPogo obj= new DeleteUserPogo();
			obj.setUserid(userId);
			obj.setId(id);
			Header header1= new Header("token",token);
			Response res= RestAssured.given()
					.header(header1)
					.contentType(ContentType.JSON)
					.body(obj)
					.when()
					.delete(Endpoints.DELETE_DATA);
			res.then().statusCode(200);
			String statusValue= res.jsonPath().get("status");
			System.out.println("Response status after delete"+statusValue);
			return res;
	}
}
