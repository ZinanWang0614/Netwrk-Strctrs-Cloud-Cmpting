package com.me.fall2018.assignment3.datamodel;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;


public class SnsConnector {

	static AmazonSNS snsClient;
	
	public static void init() {
		ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
		credentialsProvider.getCredentials();
		
		snsClient = AmazonSNSClientBuilder.standard().withCredentials(credentialsProvider).withRegion("us-west-2").build();
		System.out.print("Sns client create");
	}
	
	public AmazonSNS getClient() {
		return snsClient;
	}
}
