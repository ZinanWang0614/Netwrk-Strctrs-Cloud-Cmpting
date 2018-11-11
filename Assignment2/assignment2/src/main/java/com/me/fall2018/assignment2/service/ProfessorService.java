package com.me.fall2018.assignment2.service;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.me.fall2018.assignment2.datamodel.DynamoDbConnector;
import com.me.fall2018.assignment2.datamodel.Professor;

public class ProfessorService {

	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper; 
	
	public ProfessorService() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	// Getting a list of all professor 
	public List<Professor> getAllProfessor(){
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Professor> list = mapper.scan(Professor.class, scanExpression);
		return list;
	}
	
	//Get Professor by Id
	public Professor getProfessorById(String professorId){
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Professor> list = mapper.scan(Professor.class, scanExpression);
		Professor p = new Professor();
		for(Professor prof: list) {
			if(prof.getProfessorId().equals(professorId)) {
				p= prof;
			}
		}
		
		return p;
	}
	
	// Add a Professor
	public Professor addProfessor(Professor prof) {
		mapper.save(prof);
		return prof;
	}
	
	//Delete a Professor
	public void deleteProfessor(String professorId) {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Professor> list = mapper.scan(Professor.class, scanExpression);
		for(Professor prof: list) {
			if(prof.getProfessorId().equals(professorId)) {
				mapper.delete(prof);
			}
		}
	}
	
	//Update a Professor
	public Professor updateProfessor(String professorId,Professor prof) {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Professor> list = mapper.scan(Professor.class, scanExpression);
		String id = new String();
		for(Professor p: list) {
			if(p.getProfessorId().equals(professorId)) {
				id = p.getId();
				prof.setId(id);
				mapper.save(prof);
				return prof;
			}
		}
		return null;
	}
}
