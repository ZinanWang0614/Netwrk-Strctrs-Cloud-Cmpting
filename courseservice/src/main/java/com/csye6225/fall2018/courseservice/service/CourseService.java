package com.csye6225.fall2018.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.courseservice.datamodel.Course;
import com.csye6225.fall2018.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice.datamodel.Lecture;
import com.csye6225.fall2018.courseservice.datamodel.Professor;
import com.csye6225.fall2018.courseservice.datamodel.Student;

public class CourseService {
	static HashMap<Long,Course> courseDB = InMemoryDatabase.getCourseDB();
	static HashMap<Long, Professor> prof_Map = InMemoryDatabase.getProfessorDB();
	static HashMap<Long,Student> studentDB = InMemoryDatabase.getStudentDB();

	public List<Course> getAllCourse(){
		ArrayList<Course> list = new ArrayList<>();
		for(Course course: courseDB.values()) {
			list.add(course);
		}
		return list;
	}
	
	public void addNewCourse(String courseName, long proId, long studentId) {
		long  nextId = courseDB.size() +1;
		
		Professor prof = prof_Map.get(proId);
		Student ta = studentDB.get(studentId);
		List<Lecture> lectures = new ArrayList<>();
		List<Student> enrolledStu = new ArrayList<>();
		
		Course course = new Course(nextId,courseName,prof,ta,lectures,enrolledStu);
		courseDB.put(nextId, course);
	}
	
	public Course addNewCourse(Course course) {
		long nextId = courseDB.size() + 1;
		course.setCourseId(nextId);
		courseDB.put(nextId, course);
		return courseDB.get(nextId);
	}
	
}
