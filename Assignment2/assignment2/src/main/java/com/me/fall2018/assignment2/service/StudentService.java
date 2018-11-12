package com.me.fall2018.assignment2.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import com.me.fall2018.assignment2.datamodel.DynamoDbConnector;
import com.me.fall2018.assignment2.datamodel.Student;

public class StudentService {

	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper;

	public StudentService() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
		
	}

	// Get all Students
	public List<Student> getAllStudent() {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Student> list = mapper.scan(Student.class, scanExpression);
		return list;
	}

	// Get a student by student id
	public Student getStudentById(String studentId) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(studentId));
		
		DynamoDBQueryExpression<Student> queryExpression = new DynamoDBQueryExpression<Student>()
				.withIndexName("studentId-index")
				.withConsistentRead(false)
				.withKeyConditionExpression("studentId = :v1")
				.withExpressionAttributeValues(eav);
		
		List<Student> result = mapper.query(Student.class, queryExpression);
		if(result.size() == 0) return null;
		return result.get(0);
	}

	// Add a Student
	// Generate student Id by firstname and lastname
	// If duplicate, add number to the end of id
	// zinan.wang & zinan.wang1
	public Student addStudent(Student student) {
		String studentId = student.getFirstName()+ "." + student.getLastName();
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(studentId));
		
		DynamoDBQueryExpression<Student> queryExpression = new DynamoDBQueryExpression<Student>()
				.withIndexName("studentId-index")
				.withConsistentRead(false)
				.withKeyConditionExpression("studentId = :v1")
				.withExpressionAttributeValues(eav);
		
		List<Student> result = mapper.query(Student.class, queryExpression);
		if(result.size() == 0) {
			student.setStudentId(studentId);
		}else {
			student.setStudentId(studentId+String.valueOf(result.size()+1));
		}
		mapper.save(student);
		return student;
	}

	// Update a Student
	public Student updateStudent(String studentId, Student student) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(studentId));
		
		DynamoDBQueryExpression<Student> queryExpression = new DynamoDBQueryExpression<Student>()
				.withIndexName("studentId-index")
				.withConsistentRead(false)
				.withKeyConditionExpression("studentId = :v1")
				.withExpressionAttributeValues(eav);
		
		List<Student> result = mapper.query(Student.class, queryExpression);
		if(result.size() == 0) return null;
		student.setId(result.get(0).getId());
		mapper.save(student);
		return student;
	}

	// Delete a Student
	public String deleteStudent(String studentId) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(studentId));
		
		DynamoDBQueryExpression<Student> queryExpression = new DynamoDBQueryExpression<Student>()
				.withIndexName("studentId-index")
				.withConsistentRead(false)
				.withKeyConditionExpression("studentId = :v1")
				.withExpressionAttributeValues(eav);
		
		List<Student> result = mapper.query(Student.class, queryExpression);
		if(result.size() == 0) return "Item not exist!";
		mapper.delete(result.get(0));
		return "Delete Success";
	}

}
