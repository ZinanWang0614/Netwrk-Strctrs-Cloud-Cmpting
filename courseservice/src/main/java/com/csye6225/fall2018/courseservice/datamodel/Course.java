package com.csye6225.fall2018.courseservice.datamodel;

import java.util.ArrayList;
import java.util.List;

public class Course {
	
	private String courseId;
	private Professor professor;
	private Student ta;
	private List<Lecture> lectures;
	private List<Student> enrolledStu;
	
	public Course() {
		professor = new Professor();
		ta = new Student();
		lectures = new ArrayList<>();
		enrolledStu = new ArrayList<>();
	}
	
	public Course(String courseId, Professor professor, Student ta, List<Lecture> lectures,List<Student> enrolledStu) {
		this.courseId = courseId;
		this.professor = professor;
		this.ta = ta;
		this.lectures = lectures;
		this.enrolledStu = enrolledStu;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
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
	
}
