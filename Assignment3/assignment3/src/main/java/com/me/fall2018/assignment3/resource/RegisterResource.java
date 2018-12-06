package com.me.fall2018.assignment3.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.me.fall2018.assignment3.datamodel.Course;
import com.me.fall2018.assignment3.service.RegisterService;

@Path("student")
public class RegisterResource {
	
	RegisterService regService = new RegisterService();
	
	@POST
	@Path("{studentId}/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String register(@PathParam("studentId") String studentId, Course course) {
		return regService.register(studentId, course);
	}

}
