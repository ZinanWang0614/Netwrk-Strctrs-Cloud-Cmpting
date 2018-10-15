package com.csye6225.fall2018.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice.datamodel.Student;

public class StudentService {
	
	static HashMap<Long,Student> studentDB = InMemoryDatabase.getStudentDB();
	
	public List<Student> getAllStudents() {	
		//Getting the list
		ArrayList<Student> list = new ArrayList<>();
		for (Student prof : studentDB.values()) {
			list.add(prof);
		}
		return list ;
	}
	
	// add student 
	public void addStudent(String studentName, String image, String programName) {
		long nextAvailableId = studentDB.size() + 1;
		
		Student student = new Student(nextAvailableId,studentName,image,programName);
		studentDB.put(nextAvailableId, student);
	}
	
	// add student
	public Student addStudent(Student student) {
		long nextAvailableId = studentDB.size() + 1;
		student.setStudentId(nextAvailableId);
		studentDB.put(nextAvailableId, student);
		return studentDB.get(nextAvailableId);
	}
	
	public Student deleteStudent(long studentId) {
		Student deletedDetails = studentDB.get(studentId);
		studentDB.remove(studentId);
		return deletedDetails;
	}
	
	public Student updateStudentInfo(Long studentId, Student student) {
		
		Student oldStudent = studentDB.get(studentId);
		studentId = oldStudent.getStudentId();
		student.setStudentId(studentId);
		studentDB.put(studentId, student);
		return student;
	}

}
