package com.amazonaws.lambda.demo;

import java.util.Iterator;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Index;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler<Map<String,String>, Map<String,String>> {

    @Override
    public Map<String,String> handleRequest(Map<String,String> input, Context context) {
        
    	String courseId = input.get("courseId");
    	String department = input.get("department");
    	
    	AddRegistrarAndBoard(courseId,department);

        // TODO: implement your handler
        return null;
    }
    
    public void AddRegistrarAndBoard(String courseId,String department) {
    	
    	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
				.withRegion("us-west-2").build();
    	DynamoDB dynamoDB = new DynamoDB(client);

		
		Table courseTable = dynamoDB.getTable("course");
		Table regTable = dynamoDB.getTable("Registrar");
		Table boardTable = dynamoDB.getTable("board");
		
		Index index = courseTable.getIndex("courseId");
		
		ScanRequest request = new ScanRequest().withTableName("Registrar");
		ScanResult result = client.scan(request);
		
		Item reg = new Item().withPrimaryKey("RegistrationId",String.valueOf(result.getItems().size()+1))
				.withString("OfferingId", String.valueOf(result.getItems().size()+1))
				.withString("OfferingType", "Course")
				.withString("Department",department)
				.withNumber("PerUnitPrice", 1000);
		regTable.putItem(reg);
		
		ScanRequest request2 = new ScanRequest().withTableName("board");
		ScanResult result2 = client.scan(request);
		
		Item board = new Item().withString("boardId", String.valueOf(result2.getItems().size()+1))
				.withString("courseId",courseId)
				.withString("id",String.valueOf(result2.getItems().size()+1));
		boardTable.putItem(board);
		
		QuerySpec spec = new QuerySpec()
			    .withKeyConditionExpression("#d = :v_date")
			    .withNameMap(new NameMap()
			        .with("#d", "courseId"))
			    .withValueMap(new ValueMap()
			        .withString(":v_date",courseId));

			ItemCollection<QueryOutcome> items = index.query(spec);
			Iterator<Item> iter = items.iterator();
			String id = new String();
			while (iter.hasNext()) {
			    id = iter.next().getString("id");
			}
			
			UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("id", id)
	                .withUpdateExpression("set #na = :val1").withNameMap(new NameMap().with("#na", "boardId"))
	                .withValueMap(new ValueMap().withString(":val1", String.valueOf(result2.getItems().size()+1)));
			UpdateItemOutcome outcome = courseTable.updateItem(updateItemSpec);
				
    }

}
