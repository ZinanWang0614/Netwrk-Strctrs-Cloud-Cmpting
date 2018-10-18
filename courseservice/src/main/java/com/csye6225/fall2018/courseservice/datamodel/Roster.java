package com.csye6225.fall2018.courseservice.datamodel;

import java.util.List;

public class Roster {
	
	private List<String> name;
	
	public Roster() {}
	
	public Roster(List<String> name) {
		this.name = name;
	}

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

}
