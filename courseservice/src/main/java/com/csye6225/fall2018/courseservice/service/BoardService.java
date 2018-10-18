package com.csye6225.fall2018.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.courseservice.datamodel.Announcement;
import com.csye6225.fall2018.courseservice.datamodel.Course;
import com.csye6225.fall2018.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice.datamodel.Program;

public class BoardService {

	static HashMap<Long,Program> programDB = InMemoryDatabase.getProgramDB();
	
	//Get all announcements
	public List<Announcement> getAllAnnouncement(Long programId,Long courseId){
		for(Course course: programDB.get(programId).getCourseList()) {
			if(courseId.equals(Long.valueOf(course.getCourseId()))) {
				return course.getBoard();
			}
		}	
		return null;
	}
	
	//Add an announcement
	public List<Announcement> addAnnouncement(Long programId, Long courseId,Announcement an){
		List<Announcement> board = new ArrayList<>();
		for(Course course: programDB.get(programId).getCourseList()) {
			if(courseId.equals(Long.valueOf(course.getCourseId()))) {
				an.setId(course.getBoard().size()+1);
				course.getBoard().add(an);
				board = course.getBoard();
			}
		}
		return board;
	}
	
	//Delete an announcement
	public Announcement delete(Long programId,Long courseId, Long anId) {
		Announcement an = new Announcement();
		for(Course course: programDB.get(programId).getCourseList()) {
			if(courseId.equals(Long.valueOf(course.getCourseId()))) {
				for(Announcement a: course.getBoard()) {
					if(Long.valueOf(a.getId()).equals(anId)) {
						an = a;
					}
				}
				course.getBoard().remove(an);
				return an;
			}
		}
		
		return null;
	}
	
	//Update an announcement
	public Announcement update(Long programId,Long courseId,Long anId,Announcement an) {
		Announcement old = new Announcement();
		for(Course course: programDB.get(programId).getCourseList()) {
			if(courseId.equals(Long.valueOf(course.getCourseId()))) {
				for(Announcement a: course.getBoard()) {
					if(Long.valueOf(a.getId()).equals(anId)) {
						old = a;
					}
				}
				an.setId(anId);
				course.getBoard().remove(old);
				course.getBoard().add(an);
				return old;
			}
		}
		
		return null;
	}
}
