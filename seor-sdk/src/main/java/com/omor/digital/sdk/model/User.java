package com.omor.digital.sdk.model;

import java.io.IOException;
import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@DynamoDBTable(tableName="User")
public class User {
	
	private String name;
	private String email;
	private String password;
	private String phoneNo;
	private String address;
	private String creationTime;
	
	public User() {this.creationTime = new Date().toString();}
	
	public User(String name,String email,String password,String phoneNo,String address) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNo = phoneNo;
		this.creationTime = new Date().toString();
	}
	
	public static User createObjectFromJson(String jsonString) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(jsonString, User.class);
	}
	
	
	@DynamoDBAttribute(attributeName="name")  
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@DynamoDBHashKey(attributeName="email")  
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@DynamoDBAttribute(attributeName="password")  
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@DynamoDBAttribute(attributeName="phone_no")  
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	@DynamoDBAttribute(attributeName="address") 
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@DynamoDBAttribute(attributeName="creationTime")
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}


}