package com.csye6225.fall2018.courseservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.courseservice.datamodel.Course;
import com.csye6225.fall2018.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice.datamodel.Lecture;
import com.csye6225.fall2018.courseservice.datamodel.Professor;
import com.csye6225.fall2018.courseservice.datamodel.Program;
import com.csye6225.fall2018.courseservice.datamodel.Student;

public class CourseService {

	static HashMap<Long,Program> programDB = InMemoryDatabase.getProgramDB();
	static HashMap<Long,Student> studentDB = InMemoryDatabase.getStudentDB();
	static HashMap<Long, Professor> professorDB = InMemoryDatabase.getProfessorDB();

	//GET all courses
	public List<Course> getAllCourses(Long programId){
		List<Course> list = programDB.get(programId).getCourseList();
		return list;
	}
	
	//add a course
	//To be continue
	public Course addCourse() {
		return null;
	}
	
	//GET course by Id
	public Course getCoursebyId(Long programId,Long courseId) {
		List<Course> list = programDB.get(programId).getCourseList();
		for(Course course:list) {
			if(courseId.equals(Long.valueOf(course.getCourseId()))) {
				return course;
			}
		}
		return null;
	}
	
	// Get all enrolled students by courseId
	public List<Student> getAllStudentsbyCourse(Long programId,Long courseId){
		List<Course> list = programDB.get(programId).getCourseList();
		List<Student> enrolled = new ArrayList<>();
		for(Course course:list) {
			if(courseId.equals(Long.valueOf(course.getCourseId()))) {
				enrolled = course.getEnrolledStu();
				return enrolled;
			}
		}
		return null;
	}
	
	//Add enrolled student
	public Student addEnrolledStudent(Long programId,Long courseId,Long studentId) {
		List<Course> list = programDB.get(programId).getCourseList();
		Student st = new Student();
		for(Course course:list) {
			if(courseId.equals(Long.valueOf(course.getCourseId()))) {
				studentDB.get(studentId).getEnrolledCourses().add(course.getCourseName());
				st = studentDB.get(studentId);
				course.getEnrolledStu().add(st);
			}
		}	
		
		return st;
	}
	
	//Delete enrolled student
	public Student deleteEnrolledStudent(Long programId,Long courseId,Long studentId) {
		List<Course> list = programDB.get(programId).getCourseList();
		Student details = studentDB.get(studentId);
		for(Course course:list) {
			if(courseId.equals(Long.valueOf(course.getCourseId()))) {
				course.getEnrolledStu().remove(details);
			}
		}
		
		return details;
		
	}
	
	//Get Professor
	public Professor getProfessor(Long programId,Long courseId) {
		List<Course> list = programDB.get(programId).getCourseList();
		for(Course course:list) {
			if(courseId.equals(Long.valueOf(course.getCourseId()))) {
				return course.getProfessor();
			}
		}
		return null;
	}
	
	//Add a Professor
	public Professor addProfessor(Long programId,Long courseId,Long profId) {
		Professor prof = professorDB.get(profId);
		List<Course> list = programDB.get(programId).getCourseList();
		for(Course course:list) {
			if(courseId.equals(Long.valueOf(course.getCourseId()))) {
				course.setProfessor(prof);
			}
		}
		return prof;
	}
	
	//delete professor
	public Professor deleteProfessor(Long programId,Long courseId,Long profId) {
		Professor prof = professorDB.get(profId);
		List<Course> list = programDB.get(programId).getCourseList();
		for(Course course:list) {
			if(courseId.equals(Long.valueOf(course.getCourseId()))) {
				course.setProfessor(new Professor());
			}
		}
		return prof;
	}
	
	//Get Ta
	public Student getTa(Long programId,Long courseId) {
		List<Course> list = programDB.get(programId).getCourseList();
		for(Course course:list) {
			if(courseId.equals(Long.valueOf(course.getCourseId()))) {
				return course.getTa();
			}
		}
		return null;
	}
	
	//Add a Ta
	public Student addTa(Long programId,Long courseId,Long studentId) {
		Student ta = studentDB.get(studentId);
		List<Course> list = programDB.get(programId).getCourseList();
		for(Course course:list) {
			if(courseId.equals(Long.valueOf(course.getCourseId()))) {
				course.setTa(ta);
			}
		}
		return ta;
	}
	
	//delete Ta
	public Student deleteTa(Long programId,Long courseId,Long studentId) {
		Student ta = studentDB.get(studentId);
		List<Course> list = programDB.get(programId).getCourseList();
		for(Course course:list) {
			if(courseId.equals(Long.valueOf(course.getCourseId()))) {
				course.setTa(new Student());
			}
		}
		return ta;
	}
}
