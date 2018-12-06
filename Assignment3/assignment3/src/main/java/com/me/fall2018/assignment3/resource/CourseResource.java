package com.me.fall2018.assignment3.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.me.fall2018.assignment3.datamodel.Course;
import com.me.fall2018.assignment3.service.CourseService;

@Path("courses")
public class CourseResource {
	
	CourseService courseService = new CourseService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getAllCourses(){
		return courseService.getAllCourses();
	}
	
	@GET
	@Path("{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course getCourse(@PathParam("courseId") String courseId) {
		return courseService.getCourse(courseId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Course addCourse(Course course) {
		return courseService.addCourse(course);
	}
	
	@PUT
	@Path("{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Course update(Course course, @PathParam("courseId") String courseId) {
		return courseService.update(course, courseId);
	}
	
	@DELETE
	@Path("{courseId}")
	public String delete(@PathParam("courseId") String courseId) {
		return courseService.deleteCourse(courseId);
	}
}
