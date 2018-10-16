package com.csye6225.fall2018.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice.datamodel.Lecture;

public class LectureService {
	
static HashMap<Long,Lecture> lectureDB = InMemoryDatabase.getLectureDB();
	
	public List<Lecture> getAllStudents() {	
		//Getting the list
		ArrayList<Lecture> list = new ArrayList<>();
		for (Lecture prof : lectureDB.values()) {
			list.add(prof);
		}
		return list ;
	}
	
	// add student 
	public void addStudent (String notes,String associatedMaterials,String lectureName) {
		long nextAvailableId = lectureDB.size() + 1;
		
		Lecture student = new Lecture(nextAvailableId,notes,associatedMaterials,lectureName);
		lectureDB.put(nextAvailableId, student);
	}
	
	// add student
	public Lecture addStudent(Lecture lecture) {
		long nextAvailableId = lectureDB.size() + 1;
		lecture.setLectureId(nextAvailableId);
		lectureDB.put(nextAvailableId, lecture);
		return lectureDB.get(nextAvailableId);
	}
	
	public Lecture deleteStudent(long lectureId) {
		Lecture deletedDetails = lectureDB.get(lectureId);
		lectureDB.remove(lectureId);
		return deletedDetails;
	}
	
	public Lecture updateStudentInfo(Long studentId, Lecture student) {
		
		Lecture oldStudent = lectureDB.get(studentId);
		studentId = oldStudent.getLectureId();
		student.setLectureId(studentId);
		lectureDB.put(studentId, student);
		return student;
	}

}
