package com.csye6225.fall2018.courseservice.resources;

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

import com.csye6225.fall2018.courseservice.service.ProgramService;
import com.csye6225.fall2018.courseservice.datamodel.Program;

@Path("programs")
public class ProgramResource {
	
	ProgramService programService =  new ProgramService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Program> getAllProgram(){
		return programService.getAllProgram();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program addProgram(Program program){
		return programService.addProgram(program);
	}
	
	@DELETE
	@Path("/{programid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program deleteProgram(@PathParam("prgramid") Long programId) {
		return programService.deleteProgram(programId);
	}
	
	@PUT
	@Path("/{programid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program updateProgram(@PathParam("programid") Long programId,Program prog) {
		return programService.updateProgram(programId, prog);
	}

	@Path("/{programid}/classes")
	public CourseResource getCourseResource() {
		return new CourseResource();
	}
}
