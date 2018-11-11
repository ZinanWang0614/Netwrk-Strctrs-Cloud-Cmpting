package com.me.fall2018.assignment2.service;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
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
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Student> list = mapper.scan(Student.class, scanExpression);
		for (Student student : list) {
			if (student.getStudentId().equals(studentId)) {
				return student;
			}
		}
		return null;
	}

	// Add a Student
	public Student addStudent(Student student) {
		mapper.save(student);
		return student;
	}

	// Update a Student
	public Student updateStudent(String studentId, Student student) {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Student> list = mapper.scan(Student.class, scanExpression);
		for (Student s : list) {
			if (s.getStudentId().equals(studentId)) {
				student.setId(s.getId());
				mapper.save(student);
				return student;
			}
		}
		return null;
	}

	// Delete a Student
	public void deleteStudent(String studentId) {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Student> list = mapper.scan(Student.class, scanExpression);
		for (Student s : list) {
			if (s.getStudentId().equals(studentId)) {
				mapper.delete(s);
			}
		}
	}

}
