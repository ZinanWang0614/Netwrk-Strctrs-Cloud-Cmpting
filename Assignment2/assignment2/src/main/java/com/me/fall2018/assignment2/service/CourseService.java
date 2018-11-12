package com.me.fall2018.assignment2.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.me.fall2018.assignment2.datamodel.Course;
import com.me.fall2018.assignment2.datamodel.DynamoDbConnector;

public class CourseService {

	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper; 
	
	public CourseService() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	// Get all the courses
	public List<Course> getAllCourses(){
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Course> list = mapper.scan(Course.class, scanExpression);
		return list;
	}
	
	// Get course by CourseId
	public Course getCourse(String courseId) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(courseId));
		
		DynamoDBQueryExpression<Course> queryExpression = new DynamoDBQueryExpression<Course>()
				.withIndexName("courseId")
				.withConsistentRead(false)
				.withKeyConditionExpression("courseId = :v1")
				.withExpressionAttributeValues(eav);
		
		List<Course> result = mapper.query(Course.class, queryExpression);
		if(result.size() == 0) return null;
		return result.get(0);
	}
	
	//Add a Course
	public Course addCourse(Course course) {
		mapper.save(course);
		return course;
	}
	
	//Update
	public Course update(Course course,String courseId) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(courseId));
		
		DynamoDBQueryExpression<Course> queryExpression = new DynamoDBQueryExpression<Course>()
				.withIndexName("courseId")
				.withConsistentRead(false)
				.withKeyConditionExpression("courseId = :v1")
				.withExpressionAttributeValues(eav);
		
		List<Course> result = mapper.query(Course.class, queryExpression);
		if(result.size() == 0) return null;
		course.setId(result.get(0).getId());
		mapper.save(course);
		return course;
	}
	
	//Delete a Course
	public void deleteCourse(String courseId) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(courseId));
		
		DynamoDBQueryExpression<Course> queryExpression = new DynamoDBQueryExpression<Course>()
				.withIndexName("courseId")
				.withConsistentRead(false)
				.withKeyConditionExpression("courseId = :v1")
				.withExpressionAttributeValues(eav);
		
		List<Course> result = mapper.query(Course.class, queryExpression);
		if(result.size() == 0) return;
		mapper.delete(result.get(0));
	}
}
