package com.csye6225.fall2018.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.courseservice.datamodel.Course;
import com.csye6225.fall2018.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice.datamodel.Lecture;
import com.csye6225.fall2018.courseservice.datamodel.Program;

public class LectureService {
	
	static HashMap<Long,Program> programDB = InMemoryDatabase.getProgramDB();
	
	//GET all lectures
	public List<Lecture> getAllLecture(Long programId,Long courseId){
		List<Course> list = programDB.get(programId).getCourseList();
		for(Course course:list) {
			if(courseId.equals(Long.valueOf(course.getCourseId()))) {
				return course.getLectures();
			}
		}
		return null;	
	}
	
	//ADD a lecture
	public Lecture addLecture(Long programId,Long courseId,Lecture lec) {
		for(Course course:programDB.get(programId).getCourseList()) {
			if(courseId.equals(Long.valueOf(course.getCourseId()))) {
				List<Lecture> list = course.getLectures();
				lec.setLectureId(list.size() +1);
				course.getLectures().add(lec);
			}
		}
		return lec;
	}
	
	// update a lecture
	public Lecture updateLecture(Long programId,Long courseId,Long lectureId,Lecture lec) {
		Lecture old = new Lecture();
		for(Course course:programDB.get(programId).getCourseList()) {
			if(courseId.equals(Long.valueOf(course.getCourseId()))) {
				for(Lecture lecture:course.getLectures()) {
					if(Long.valueOf(lecture.getLectureId()).equals(lectureId)){
						old = lecture;
					}
				}
				
				lec.setLectureId(lectureId);
				course.getLectures().remove(old);
				course.getLectures().add(lec);
				return old;
			}
		}	
		return null;
	}
	
	//get lecture by id
	public Lecture getLecturebyId(Long programId,Long courseId,Long lectureId) {
		for(Course course:programDB.get(programId).getCourseList()) {
			if(courseId.equals(Long.valueOf(course.getCourseId()))) {
				for(Lecture lecture:course.getLectures()) {
					if(lectureId.equals(new Long(lecture.getLectureId()))){
						return lecture;
					}
				}
			}
		}
		
		return null;
	}
	
	//delete lecture
	public Lecture DeleteLecture(Long programId,Long courseId,Long lectureId) {
		Lecture lec = new Lecture();
		for(Course course:programDB.get(programId).getCourseList()) {
			if(courseId.equals(Long.valueOf(course.getCourseId()))) {
				for(Lecture lecture:course.getLectures()) {
					if(lectureId.equals(new Long(lecture.getLectureId()))){
						lec = lecture;
						course.getLectures().remove(lecture);
						return lec;
					}
				}
			}
		}	
		return lec;
	}
}
