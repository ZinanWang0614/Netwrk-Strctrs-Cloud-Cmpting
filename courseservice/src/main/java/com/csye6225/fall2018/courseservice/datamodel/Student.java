package com.csye6225.fall2018.courseservice.datamodel;

import java.util.List;

public class Student {
	
	private String studentName;
	private long studentId;
	private String image;
	private String programName;
	private List<String> enrolledCourses;
	
	// No argument Constructor
	public Student() {}
	
	// Constructor
	public Student(long studentId, String studentName, String image, String programName,List<String> enrolledCourses) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.image = image;
		this.programName = programName;
		this.enrolledCourses = enrolledCourses;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public List<String> getEnrolledCourses() {
		return enrolledCourses;
	}

	public void setEnrolledCourses(List<String> enrolledCourses) {
		this.enrolledCourses = enrolledCourses;
	}

}
