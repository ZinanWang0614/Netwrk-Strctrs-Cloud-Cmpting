package com.amazonaws.lambda.demo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Index;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.StreamRecord;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent.DynamodbStreamRecord;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

public class LambdaFunctionHandler implements RequestHandler<DynamodbEvent, Integer> {

	@Override
	public Integer handleRequest(DynamodbEvent event, Context context) {

		context.getLogger().log("Received event: " + event);

		StreamRecord sr = new StreamRecord();
		String message = "";
		String courseId = "";
		String topicArn = "";

		for (DynamodbStreamRecord record : event.getRecords()) {
			context.getLogger().log(record.getEventName());
			if (record.getEventName().equals("INSERT")) {
				sr = record.getDynamodb();
				for (Entry<String, AttributeValue> entry : sr.getNewImage().entrySet()) {
					if (entry.getKey().equals("announcementText")) {
						message = entry.getValue().getS();
					}
					if(entry.getKey().equals("courseId")) {
						courseId = entry.getValue().getS();
					}
				}
				
				break;
			}
		}
		
		context.getLogger().log(courseId);
		topicArn = getArn(courseId);
		context.getLogger().log(topicArn);
		
		if(!message.equals("")&& !courseId.equals("")) {
			// create sns client
			AmazonSNSClient snsClient = new AmazonSNSClient();
			snsClient.setRegion(Region.getRegion(Regions.US_WEST_2));
			
			//String topicArn = "arn:aws:sns:us-west-2:732704573169:CSYE6200";

			PublishResult result = snsClient.publish(new PublishRequest().withTopicArn(topicArn).withMessage(message));
			context.getLogger().log(result.toString());
		}
		

		return null;
	}
	
	public String getArn(String courseId) {
		
		String topicArn = new String();
		//dynamoDb

		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
				.withRegion("us-west-2").build();
		DynamoDB dynamoDB = new DynamoDB(client);

		Table table = dynamoDB.getTable("course");
		Index index = table.getIndex("courseId");

		QuerySpec spec = new QuerySpec()
		    .withKeyConditionExpression("#d = :v_date")
		    .withNameMap(new NameMap()
		        .with("#d", "courseId"))
		    .withValueMap(new ValueMap()
		        .withString(":v_date",courseId));

		ItemCollection<QueryOutcome> items = index.query(spec);
		Iterator<Item> iter = items.iterator(); 
		while (iter.hasNext()) {
		    topicArn = iter.next().getString("notificationTopic");
		}
		return topicArn;
	}
}