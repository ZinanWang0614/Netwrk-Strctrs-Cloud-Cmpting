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

import com.me.fall2018.assignment3.datamodel.Student;
import com.me.fall2018.assignment3.service.StudentService;

@Path("students")
public class StudentResource {
	
	StudentService stuService = new StudentService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getAllStudent(){
		return stuService.getAllStudent();
	}
	
	@GET
	@Path("{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudentById(@PathParam("studentId") String studentId) {
		return stuService.getStudentById(studentId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student addStudent(Student student) {
		return stuService.addStudent(student);
	}
	
	@PUT
	@Path("{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student updateStudent(@PathParam("studentId") String studentId, Student student) {
		return stuService.updateStudent(studentId, student);
	}
	
	@DELETE
	@Path("{studentId}")
	public String deleteStudent(@PathParam("studentId") String studentId) {	
		return stuService.deleteStudent(studentId);
	}
}
