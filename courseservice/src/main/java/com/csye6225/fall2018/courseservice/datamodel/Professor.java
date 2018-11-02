package com.csye6225.fall2018.courseservice.datamodel;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "professor")
public class Professor {
	private String firstName;
	private String department;
	private long professorId;
	private Date joiningDate;
	
	

	public Professor() {
		
	}
	
	public Professor(long professorId, String firstName, String department, Date joiningDate) {
		this.professorId = professorId;
		this.firstName = firstName;
		this.department = department;
		this.joiningDate = joiningDate;
	}
	
	@DynamoDBAttribute(attributeName = "firstName")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@DynamoDBAttribute(attributeName = "department")
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@DynamoDBHashKey(attributeName = "professorId")
	public long getProfessorId() {
		return professorId;
	}
	public void setProfessorId(long professorId) {
		this.professorId = professorId;
	}
	
	@DynamoDBAttribute(attributeName = "date")
	public Date getJoiningDate() {
		return joiningDate;
	}	
	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}
	
	//@DynamoDBIgnore
	@Override
	public String toString() {
		return "ProfId=" + getProfessorId() + ", firstName = " + getFirstName() +", department = " + getDepartment();
	}
}
