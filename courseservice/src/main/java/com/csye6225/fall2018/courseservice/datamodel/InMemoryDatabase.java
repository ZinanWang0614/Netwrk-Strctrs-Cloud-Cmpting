package com.csye6225.fall2018.courseservice.datamodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class InMemoryDatabase {

	private static HashMap<Long, Professor> professorDB = new HashMap<> ();
	private static HashMap<Long, Student> studentDB = new HashMap<>();
	private static HashMap<Long,Lecture> lectureDB = new HashMap<>();
	private static HashMap<Long,Course> courseDB = new HashMap<>();

	public static HashMap<Long, Professor> getProfessorDB() {
		return professorDB;
	}
	
	public static HashMap<Long,Student> getStudentDB(){
		return studentDB;
	}
	
	public static HashMap<Long,Lecture> getLectureDB(){
		return lectureDB;
	}
	
	public static HashMap<Long,Course> getCourseDB(){
		return courseDB;
	}
}
