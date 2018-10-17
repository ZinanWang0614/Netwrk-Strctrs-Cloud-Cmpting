package com.csye6225.fall2018.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.courseservice.datamodel.Course;
import com.csye6225.fall2018.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice.datamodel.Lecture;

public class LectureService {
	
	static HashMap<Long,Course> courseDB = InMemoryDatabase.getCourseDB();
	
	//GET all lectures
	public List<Lecture> getAllLecture(Long courseId){
		List<Lecture> list = courseDB.get(courseId).getLectures();
		return list;	
	}
	
	//ADD a lecture
	public Lecture addLecture(Long courseId,Lecture lec) {
		List<Lecture> list = courseDB.get(courseId).getLectures();
		lec.setLectureId(list.size() +1);
		list.add(lec);
		return lec;
	}
	
	// update a lecture
	public Lecture updateLecture(Long courseId,Long lectureId,Lecture lec) {
		List<Lecture> list = courseDB.get(courseId).getLectures();
		for(Lecture lecture:list) {
			if(lectureId.equals(new Long(lecture.getLectureId()))){
				lec.setLectureId(lectureId);
				lecture = lec;
				return lecture;
			}
		}
		
		return null;
	}
	
	//get lecture by id
	public Lecture getLecturebyId(Long courseId,Long lectureId) {
		List<Lecture> list = courseDB.get(courseId).getLectures();
		for(Lecture lecture:list) {
			if(lectureId.equals(new Long(lecture.getLectureId()))){
				return lecture;
			}
		}
		return null;
	}
	
	//delete lecture
	public Lecture DeleteLecture(Long courseId,Long lectureId) {
		List<Lecture> list = courseDB.get(courseId).getLectures();
		Lecture lec = new Lecture();
		for(Lecture lecture:list) {
			if(lectureId.equals(new Long(lecture.getLectureId()))){
				lec = lecture;
				list.remove(lecture);
			}
		}
		return lec;
	}
}
