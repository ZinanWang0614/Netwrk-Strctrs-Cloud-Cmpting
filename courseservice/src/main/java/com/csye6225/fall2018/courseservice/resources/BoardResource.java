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

import com.csye6225.fall2018.courseservice.datamodel.Announcement;
import com.csye6225.fall2018.courseservice.service.BoardService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BoardResource {

	BoardService boardService = new BoardService();
	
	@GET
	public List<Announcement> getAllAnnouncement(@PathParam("programid") Long programId,@PathParam("courseid") Long courseId){
		return boardService.getAllAnnouncement(programId, courseId);
	}
	
	@POST
	public List<Announcement> add(@PathParam("programid") Long programId,@PathParam("courseid") Long courseId, Announcement an){
		return boardService.addAnnouncement(programId, courseId, an);
	}
	
	@PUT
	@Path("/{anid}")
	public Announcement update(@PathParam("programid") Long programId,@PathParam("courseid") Long courseId,@PathParam("anid") Long anId,Announcement an) {
		return boardService.update(programId, courseId, anId, an);
	}
	
	@DELETE
	@Path("/{anid}")
	public Announcement delete(@PathParam("programid") Long programId,@PathParam("courseid") Long courseId,@PathParam("anid") Long anId) {
		return boardService.delete(programId, courseId, anId);
	}
}
