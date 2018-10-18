package com.csye6225.fall2018.courseservice.datamodel;

import java.util.ArrayList;
import java.util.List;

public class Course {
	
	private long courseId;
	private String courseName;
	private Professor professor;
	private Student ta;
	private List<Lecture> lectures;
	private List<Student> enrolledStu;
	private List<Announcement> board;
	private List<RosterName> roster;
	
	//empty constructor
	public Course() {}
	
	// constructor
	public Course(long courseId,String courseName,Professor prof,Student ta, List<Lecture> lectures, 
			List<Student> enrolledStu, List<Announcement> board,List<RosterName> roster) {
		
		this.courseId = courseId;
		this.courseName = courseName;
		this.professor = prof;
		this.ta = ta;
		this.lectures = lectures;
		this.enrolledStu = enrolledStu;
		this.board = board;
		this.roster = roster;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Student getTa() {
		return ta;
	}

	public void setTa(Student ta) {
		this.ta = ta;
	}

	public List<Lecture> getLectures() {
		return lectures;
	}

	public void setLectures(List<Lecture> lectures) {
		this.lectures = lectures;
	}

	public List<Student> getEnrolledStu() {
		return enrolledStu;
	}

	public void setEnrolledStu(List<Student> enrolledStu) {
		this.enrolledStu = enrolledStu;
	}

	public List<Announcement> getBoard() {
		return board;
	}

	public void setBoard(List<Announcement> board) {
		this.board = board;
	}

	public List<RosterName> getRoster() {
		return roster;
	}

	public void setRoster(List<RosterName> roster) {
		this.roster = roster;
	}
	
}
