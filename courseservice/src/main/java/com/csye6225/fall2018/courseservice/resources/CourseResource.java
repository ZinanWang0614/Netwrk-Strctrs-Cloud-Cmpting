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
	
	static HashMap<Long,Student> studentDB = InMemoryDatabase.getStudentDB();
	
	//Course resource
	//GET all course
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getAllCourses(){
		return courseService.getAllCourses();
	}
	
	//Get Course by Id
	@GET
	@Path("/{courseid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course getCoursebyId(@PathParam("courseid") Long courseId) {
		return courseService.getCoursebyId(courseId);
	}
	
	//POST method ***** To be continue
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Course addCourse(Course course) {
		return null;
	}
	
	
	// lecture subresource
	@Path("/{courseid}/lectures")
	public LectureResource test() {
		return new LectureResource();
	}
	
}
