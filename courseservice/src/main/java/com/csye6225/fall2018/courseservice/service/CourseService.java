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
	static HashMap<Long,Student> studentDB = InMemoryDatabase.getStudentDB();
	static HashMap<Long, Professor> professorDB = InMemoryDatabase.getProfessorDB();

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
	
	// Get all enrolled students by courseId
	public List<Student> getAllStudentsbyCourse(Long courseId){
		List<Student> list = courseDB.get(courseId).getEnrolledStu();
		return list;
	}
	
	//Add enrolled student
	public Student addEnrolledStudent(Long courseId,Long studentId) {
		List<Student> list = courseDB.get(courseId).getEnrolledStu();
		Student st = studentDB.get(studentId);
		list.add(st);
		return st;
	}
	
	//Delete enrolled student
	public Student deleteEnrolledStudent(Long courseId,Long studentId) {
		List<Student> list = courseDB.get(courseId).getEnrolledStu();
		Student removeDetails = new Student();
		for(Student st: list) {
			if(studentId.equals(new Long(st.getStudentId()))) {
				removeDetails = st;
				list.remove(st);
				return removeDetails;
			}
		}
		return null;
	}
	
	//Get Professor
	public Professor getProfessor(Long courseId) {
		return courseDB.get(courseId).getProfessor();
	}
	
	//Add a Professor
	public Professor addProfessor(Long courseId,Long profId) {
		Professor prof = professorDB.get(profId);
		courseDB.get(courseId).setProfessor(prof);
		return prof;
	}
	
	//delete professor
	public Professor deleteProfessor(Long courseId,Long profId) {
		Professor prof = professorDB.get(profId);
		courseDB.get(courseId).setProfessor(new Professor());
		return prof;
	}
	
	//Get Ta
	public Student getTa(Long courseId) {
		return courseDB.get(courseId).getTa();
	}
	
	//Add a Ta
	public Student addTa(Long courseId,Long studentId) {
		Student ta = studentDB.get(studentId);
		courseDB.get(courseId).setTa(ta);
		return ta;
	}
	
	//delete Ta
	public Student deleteTa(Long courseId,Long studentId) {
		Student ta = studentDB.get(studentId);
		courseDB.get(courseId).setTa(new Student());
		return ta;
	}
}
