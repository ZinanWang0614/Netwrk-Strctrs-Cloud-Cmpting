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

import com.me.fall2018.assignment3.service.AnnouncementService;
import com.me.fall2018.assignment3.datamodel.Announcement;

@Path("announcements")
public class AnnouncementResource {

	AnnouncementService anService = new AnnouncementService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Announcement> getAllAnnouncement(){
		return anService.getAllAnnouncements();
	}
	
	@GET
	@Path("{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Announcement> getBoardAns(@PathParam("boardId") String boardId){
		return anService.getBoardAns(boardId);
	}
	
	@GET
	@Path("{boardId}_{anId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Announcement getAn(@PathParam("boardId") String boardId, @PathParam("anId") String anId) {
		return anService.getAn(boardId, anId);
	}
	
	@POST
	@Path("{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Announcement addAnnouncement(@PathParam("boardId") String boardId, Announcement an) {
		return anService.addAnnouncement(boardId, an);
	}
	
	@PUT
	@Path("{boardId}_{anId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Announcement updateAn(@PathParam("boardId") String boardId, @PathParam("anId") String anId, Announcement an) {
		return anService.updateAn(boardId, anId, an);
	}
	
	@DELETE
	@Path("{boardId}_{anId}")
	public String deleteAn(@PathParam("boardId") String boardId, @PathParam("anId") String anId) {
		return anService.deleteAn(boardId, anId);
	}
}
