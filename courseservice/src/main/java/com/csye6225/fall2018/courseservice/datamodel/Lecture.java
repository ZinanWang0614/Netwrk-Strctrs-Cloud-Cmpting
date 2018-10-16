package com.csye6225.fall2018.courseservice.datamodel;

public class Lecture {
	
	private long lectureId;
	private String lectureName;
	private String notes;
	private String associatedMaterials;
	
	public Lecture() {}
	
	public Lecture(long lectureId, String notes, String associatedMaterials,String lectureName) {
		this.lectureId = lectureId;
		this.notes = notes;
		this.associatedMaterials = associatedMaterials;
		this.lectureName = lectureName;
	}

	public long getLectureId() {
		return lectureId;
	}

	public void setLectureId(long lectureId) {
		this.lectureId = lectureId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getAssociatedMaterials() {
		return associatedMaterials;
	}

	public void setAssociatedMaterials(String associatedMaterials) {
		this.associatedMaterials = associatedMaterials;
	}

	public String getLectureName() {
		return lectureName;
	}

	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}
	
	
}
