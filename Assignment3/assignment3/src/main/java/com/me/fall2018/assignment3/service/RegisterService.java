package com.me.fall2018.assignment3.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;
import com.me.fall2018.assignment3.datamodel.Course;
import com.me.fall2018.assignment3.datamodel.DynamoDbConnector;
import com.me.fall2018.assignment3.datamodel.SnsConnector;
import com.me.fall2018.assignment3.datamodel.Student;

public class RegisterService {
	
	static DynamoDbConnector dynamoDb;
	static SnsConnector sns;
	AmazonSNS snsClient;
	DynamoDBMapper mapper;

	public RegisterService() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		
		sns = new SnsConnector();
		sns.init();
		
		snsClient = sns.getClient(); 
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	//Regsiter to a course
	public String register(String studentId, Course course) {
		
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(studentId));
		
		DynamoDBQueryExpression<Student> queryExpression = new DynamoDBQueryExpression<Student>()
				.withIndexName("studentId-index")
				.withConsistentRead(false)
				.withKeyConditionExpression("studentId = :v1")
				.withExpressionAttributeValues(eav);
		
		List<Student> result = mapper.query(Student.class, queryExpression);
		Student student = new Student();
		if(result.size()==0) return "No such student found.";
		else student = result.get(0);
		
		//find the course 
		Map<String, AttributeValue> eav1 = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(course.getCourseId()));
		
		DynamoDBQueryExpression<Course> queryExpression1 = new DynamoDBQueryExpression<Course>()
				.withIndexName("courseId")
				.withConsistentRead(false)
				.withKeyConditionExpression("courseId = :v1")
				.withExpressionAttributeValues(eav);
		
		List<Course> result1 = mapper.query(Course.class, queryExpression1);
		if(result.size() == 0) return "Course no found";
		
		
		//TODO: No more than 3 course & no such course
		if(student.getRegisterCourses().size() == 3) {
			return "You can only register 3 course";
		}else {
			Course c = result1.get(0);
			c.getRoster().add(studentId);
			mapper.save(c);
			student.getRegisterCourses().add(course.getCourseId());
			mapper.save(student);
		}
		
		String topicArn = course.getNotificationTopic();
		SubscribeRequest subscribe = new SubscribeRequest(topicArn, "email", student.getEmailId());
		SubscribeResult subscribeResult = snsClient.subscribe(subscribe);
		System.out.println("Subscribe result: " + subscribeResult);
		
		return null;
	}

}
