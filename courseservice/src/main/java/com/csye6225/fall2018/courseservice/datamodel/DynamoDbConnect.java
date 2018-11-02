package com.csye6225.fall2018.courseservice.datamodel;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;

public class DynamoDbConnect {

	static AmazonDynamoDB dynamoDB;
	
	/*
	 * Init function to make the client availabe 
	 * 		setup the resouces
	 * 		resources include credentials 
	 * 		aws region 
	 * 		build the client
	 */
	public static void init() throws Exception {
		ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
		credentialsProvider.getCredentials();
		
		dynamoDB = AmazonDynamoDBClientBuilder
					.standard()
					.withCredentials(credentialsProvider)
					.withRegion("us-west-2")
					.build();			
	}
	
	public AmazonDynamoDB getClient() {
		return dynamoDB;
	}
}