package com.me.fall2018.assignment2.service;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
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
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Course> list = mapper.scan(Course.class, scanExpression);
		for(Course course:list) {
			if(course.getCourseId().equals(courseId)) {
				return course;
			}
		}
		return null;
	}
	
	//Add a Course
	public Course addCourse(Course course) {
		mapper.save(course);
		return course;
	}
	
	//Update
	public Course update(Course course,String courseId) {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Course> list = mapper.scan(Course.class, scanExpression);
		for(Course c:list) {
			if(c.getCourseId().equals(courseId)) {
				course.setId(c.getId());
				mapper.save(course);
				return course;
			}
		}
		return null;
	}
	
	//Delete a Course
	public void deleteCourse(String courseId) {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Course> list = mapper.scan(Course.class, scanExpression);
		for(Course c:list) {
			if(c.getCourseId().equals(courseId)) {
				mapper.delete(c);
			}
		}
	}
}
