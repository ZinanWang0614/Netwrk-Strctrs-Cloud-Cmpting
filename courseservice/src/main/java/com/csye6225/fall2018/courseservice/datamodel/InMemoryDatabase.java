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
		// init some data into DB
		
		// Student & add into studentDB
		List<String> enrolled = new ArrayList<>();
		Student ta = new Student(1,"zinan","none","msis",enrolled);
		studentDB.put(Long.valueOf(ta.getStudentId()),ta );
		
		// Professor & add into professorDB
		Professor prof = new Professor(1,"Ami","Amazon",new Date());
		professorDB.put(Long.valueOf(prof.getProfessorId()),prof);
		
		// lecture & add into lectures list 
		Lecture lecture = new Lecture(1,"noets","Not now","week 1");
		List<Lecture> lecs = new ArrayList<>();
		lecs.add(lecture);
		
		// add student into enrolled student list
		List<Student> sts = new ArrayList<>();
		sts.add(ta);
		
		// Announcement & add into board
		Announcement an = new Announcement(1,"This is our first assignment,welcome everyone","Welcome");
		List<Announcement> board = new ArrayList<>();
		board.add(an);
		
		//add to roster
		List<RosterName> roster = new ArrayList<>();
		RosterName name = new RosterName(ta.getStudentName());
		roster.add(name);
		
		// init course
		Course init = new Course(1,"Info5100",prof,ta,lecs,sts,board,roster);
		
		// add course into ta's enrolled course
		ta.getEnrolledCourses().add(init.getCourseName());
		
		//add course into courses list
		List<Course> courses = new ArrayList<>();
		courses.add(init);
		
		// init program & add into programDB
		Program prog = new Program(1,"MSIS",courses);
		programDB.put(Long.valueOf(prog.getProgramId()), prog);
		
		return programDB;
	}
}
