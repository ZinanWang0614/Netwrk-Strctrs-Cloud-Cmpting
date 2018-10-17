package com.csye6225.fall2018.courseservice.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice.datamodel.Course;
import com.csye6225.fall2018.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice.datamodel.Lecture;
import com.csye6225.fall2018.courseservice.datamodel.Professor;
import com.csye6225.fall2018.courseservice.datamodel.Student;
import com.csye6225.fall2018.courseservice.service.CourseService;
import com.csye6225.fall2018.courseservice.service.LectureService;
import com.csye6225.fall2018.courseservice.service.ProfessorsService;
import com.csye6225.fall2018.courseservice.service.StudentService;

@Path("classes")
public class CourseResource {

	CourseService courseService = new CourseService();
	LectureService lectureService = new LectureService();

	static HashMap<Long, Student> studentDB = InMemoryDatabase.getStudentDB();

	//************************************ Get Course  ********************************//
	// Course resource
	// GET all course
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getAllCourses() {
		return courseService.getAllCourses();
	}

	// Get Course by Id
	@GET
	@Path("/{courseid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course getCoursebyId(@PathParam("courseid") Long courseId) {
		return courseService.getCoursebyId(courseId);
	}
	//**********************************************************************************//

	
	

	//************************************ Enrolled Student********************************//
	// Get All enrolled students
	@GET
	@Path("/{courseid}/students")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getEnrolledStudents(@PathParam("courseid") Long courseId) {
		return courseService.getAllStudentsbyCourse(courseId);
	}

	// Post enrolled student
	@POST
	@Path("/{courseid}/students/{studentid}")
	public Student addenrolledStudent(@PathParam("courseid") Long courseId, @PathParam("studentid") Long studentId) {
		return courseService.addEnrolledStudent(courseId, studentId);
	}
	
	@DELETE
	@Path("/{courseid}/students/{studentid}")
	public Student deleteenrolledStudent(@PathParam("courseid") Long courseId, @PathParam("studentid") Long studentId) {
		return courseService.deleteEnrolledStudent(courseId, studentId);
	}
	//*************************************************************************************//

	
	
	
	
	//************************************ Lecture *********************************************//
	// lecture sub-resource
	@Path("/{courseid}/lectures")
	public LectureResource getLecResource() {
		return new LectureResource();
	}
	//******************************************************************************************//
	
	
	
	
	//*************************************** Post Course  **************************************//
	// POST method ***** To be continue
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Course addCourse(Course course) {
			return null;
		}
	//******************************************************************************************//
		

		
		
	//*************************************** Professor*********************************************//
	@GET
	@Path("/{courseid}/professor")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor getProfessor(@PathParam("courseid") Long courseId) {
		return courseService.getProfessor(courseId);
	}
	
	@POST
	@Path("/{courseid}/professor/{profid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor addProfessor(@PathParam("courseid") Long courseId,@PathParam("profid") Long profId) {
		return courseService.addProfessor(courseId, profId);
	}
	
	@DELETE
	@Path("/{courseid}/professor/{profid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor deleteProfessor(@PathParam("courseid") Long courseId,@PathParam("profid") Long profId) {
		return courseService.deleteProfessor(courseId, profId);
	}
	//***********************************************************************************************//
	
	
	
	//******************************************TA****************************************************//
	@GET
	@Path("/{courseid}/ta")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getTa(@PathParam("courseid") Long courseId) {
		return courseService.getTa(courseId);
	}
	
	@POST
	@Path("/{courseid}/ta/{studentid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student addTa(@PathParam("courseid") Long courseId,@PathParam("studentid") Long studentId) {
		return courseService.addTa(courseId, studentId);
	}
	
	@DELETE
	@Path("/{courseid}/ta/{studentid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student deleteTa(@PathParam("courseid") Long courseId,@PathParam("studentid") Long studentId) {
		return courseService.deleteTa(courseId, studentId);
	}
	//***********************************************************************************************//
	
	
}
