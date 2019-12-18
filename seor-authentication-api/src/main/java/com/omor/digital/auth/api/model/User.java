package com.omor.digital.auth.api.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="User")
public class User {
	
	private String name;
	private String email;
	private String password;
	private String phoneNo;
	private String address;
	
	
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


}
