package com.csye6225.fall2018.courseservice.datamodel;

import java.util.List;

public class Program {
	
	private long programId;
	private String programName;
	private List<Course> courseList;
	
	public Program() {}
	
	public Program(long programId, String programName, List<Course> courseList) {
		this.programId = programId;
		this.programName = programName;
		this.courseList = courseList;
	}

	public long getProgramId() {
		return programId;
	}

	public void setProgramId(long programId) {
		this.programId = programId;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

}
