package com.csye6225.fall2018.courseservice.datamodel;

public class Announcement {
	private long id;
	private String content;
	private String subject;
	
	public Announcement() {}
	
	public Announcement(long id,String content, String subject) {
		this.id = id;
		this.content = content;
		this.subject = subject;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
}
