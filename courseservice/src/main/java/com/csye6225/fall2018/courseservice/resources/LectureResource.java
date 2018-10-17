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

import com.csye6225.fall2018.courseservice.datamodel.Lecture;
import com.csye6225.fall2018.courseservice.service.LectureService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LectureResource {
	
	
	LectureService lecService = new LectureService();
	
	@GET
	public List<Lecture> getAllLectures(@PathParam("programid") Long programId,@PathParam("courseid") Long courseId){		
		return lecService.getAllLecture(programId,courseId);
	}
	
	@GET
	@Path("/{lectureid}")
	public Lecture getLecturebyId(@PathParam("programid") Long programId,@PathParam("courseid") Long courseId,@PathParam("lectureid") Long lectureId) {
		return lecService.getLecturebyId(programId,courseId, lectureId);
	}
	
	@POST
	public Lecture addLecture(@PathParam("programid") Long programId,@PathParam("courseid") Long courseId,Lecture lec) {
		return lecService.addLecture(programId,courseId, lec);
	}
	
	@PUT
	@Path("/{lectureid}")
	public Lecture updateLecture(@PathParam("programid") Long programId,@PathParam("courseid") Long courseId,@PathParam("lectureid") Long lectureId,Lecture lecture) {
		return lecService.updateLecture(programId,courseId, lectureId, lecture);
	}
	
	@DELETE
	@Path("/{lectureid}")
	public Lecture deleteLecture(@PathParam("programid") Long programId,@PathParam("courseid") Long courseId,@PathParam("lectureid") Long lectureId) {
		return lecService.DeleteLecture(programId,courseId, lectureId);
	}
}
