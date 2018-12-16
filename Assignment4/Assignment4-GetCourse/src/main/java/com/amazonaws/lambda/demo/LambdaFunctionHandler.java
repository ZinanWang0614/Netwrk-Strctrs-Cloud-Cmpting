package com.amazonaws.lambda.demo;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler<Map<String,AttributeValue>, Map<String,String>> {

	@Override
	public Map<String,String> handleRequest(Map<String,AttributeValue> input, Context context) {
	
		context.getLogger().log(input.get("id").getS());
		
		Map<String,String> result = new HashMap<>();
		
		if(!input.containsKey("boardId") && !input.containsKey("roster") && !input.containsKey("notificationTopic")) {
			result.put("courseId", input.get("courseId").getS());
			result.put("department", input.get("department").getS());
			return result;
		}else {
			result.put("courseId", "null");
			result.put("department", "null");
			return result;
		}
		
	}

	

}