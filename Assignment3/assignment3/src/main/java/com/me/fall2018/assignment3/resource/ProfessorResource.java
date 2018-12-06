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

import com.me.fall2018.assignment3.datamodel.Professor;
import com.me.fall2018.assignment3.service.ProfessorService;

@Path("professors")
public class ProfessorResource {
	
	ProfessorService profService = new ProfessorService();

	// Get All Professors
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> getAllProfessor(){
		return profService.getAllProfessor();
	}
	
	// Get A Professor by Id
	@GET
	@Path("/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor getProfessorById(@PathParam("professorId") String profId){
		return profService.getProfessorById(profId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Professor addProfessor(Professor prof) {
		return profService.addProfessor(prof);
	}
	
	@DELETE
	@Path("/{professorId}")
	public String deleteProfessor(@PathParam("professorId") String professorId) {
		return profService.deleteProfessor(professorId);
	}
	
	@PUT
	@Path("/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Professor updateProfessor(@PathParam("professorId") String professorId, Professor prof) {
		return profService.updateProfessor(professorId, prof);
	}
}
