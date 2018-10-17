package com.csye6225.fall2018.courseservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.courseservice.datamodel.Course;
import com.csye6225.fall2018.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice.datamodel.Lecture;
import com.csye6225.fall2018.courseservice.datamodel.Professor;
import com.csye6225.fall2018.courseservice.datamodel.Student;

public class CourseService {
	
	
	static HashMap<Long,Course> courseDB = InMemoryDatabase.getCourseDB();

	//GET all courses
	public List<Course> getAllCourses(){
		ArrayList<Course> list = new ArrayList<>();
		for(Course course: courseDB.values()) {
			list.add(course);
		}
		return list;
	}
	
	//GET course by Id
	public Course getCoursebyId(Long courseId) {
		return courseDB.get(courseId);
	}
	
	
}
