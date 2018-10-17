package com.csye6225.fall2018.courseservice.datamodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class InMemoryDatabase {

	private static HashMap<Long, Professor> professorDB = new HashMap<> ();
	private static HashMap<Long, Student> studentDB = new HashMap<>();
	private static HashMap<Long,Program> programDB = new HashMap<>();

	public static HashMap<Long, Professor> getProfessorDB() {
		return professorDB;
	}
	
	public static HashMap<Long,Student> getStudentDB(){
		return studentDB;
	}
	
	
	
	public static HashMap<Long,Program> getProgramDB(){
		Student ta = new Student(100,"zinan","none","msis");
		studentDB.put(Long.valueOf(ta.getStudentId()),ta );
		Professor prof = new Professor(100,"Ami","Amazon",new Date());
		professorDB.put(Long.valueOf(prof.getProfessorId()),prof);
		Lecture lecture = new Lecture(100,"noets","Not now","week 1");
		List<Lecture> lecs = new ArrayList<>();
		lecs.add(lecture);
		List<Student> sts = new ArrayList<>();
		sts.add(ta);
		Course init = new Course(100,"Info5100",prof,ta,lecs,sts);
		List<Course> courses = new ArrayList<>();
		courses.add(init);
		Program prog = new Program(100,"MSIS",courses);
		programDB.put(Long.valueOf(prog.getProgramId()), prog);
		return programDB;
	}
}
