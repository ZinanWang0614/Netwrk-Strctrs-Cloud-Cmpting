package com.amazonaws.lambda.demo;


import java.util.Map.Entry;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.StreamRecord;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent.DynamodbStreamRecord;
import com.amazonaws.services.stepfunctions.AWSStepFunctions;
import com.amazonaws.services.stepfunctions.AWSStepFunctionsClientBuilder;
import com.amazonaws.services.stepfunctions.model.StartExecutionRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LambdaFunctionHandler implements RequestHandler<DynamodbEvent, Integer> {

    @Override
    public Integer handleRequest(DynamodbEvent event, Context context) {
    	final AWSStepFunctions client = AWSStepFunctionsClientBuilder.defaultClient();
    	StartExecutionRequest request = new StartExecutionRequest();
    	
    	ObjectMapper mapper = new ObjectMapper();
 
    	request.setStateMachineArn("arn:aws:states:us-west-2:732704573169:stateMachine:Choicestate");
    	
    	StreamRecord sr = new StreamRecord();
    	
    	for (DynamodbStreamRecord record : event.getRecords()) {
			if (record.getEventName().equals("INSERT")) {
				sr = record.getDynamodb();
				context.getLogger().log(sr.getNewImage().toString());
				
				try {
					request.setInput(mapper.writeValueAsString(sr.getNewImage()));
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				client.startExecution(request);
				break;
			}
		}
    	
    	
    	
        return 0;
    }
}