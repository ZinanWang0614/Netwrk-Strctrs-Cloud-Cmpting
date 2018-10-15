package com.csye6225.fall2018.courseservice.datamodel;

public class Lecture {
	
	private String lectureId;
	private String notes;
	private String associatedMaterials;
	
	public Lecture() {}
	
	public Lecture(String lectureId, String notes, String associatedMaterials) {
		this.lectureId = lectureId;
		this.notes = notes;
		this.associatedMaterials = associatedMaterials;
	}

	public String getLectureId() {
		return lectureId;
	}

	public void setLectureId(String lectureId) {
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
	
}
