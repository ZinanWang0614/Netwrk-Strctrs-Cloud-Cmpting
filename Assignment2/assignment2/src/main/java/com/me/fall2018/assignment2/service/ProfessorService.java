package com.me.fall2018.assignment2.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
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
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(professorId));
		
		DynamoDBQueryExpression<Professor> queryExpression = new DynamoDBQueryExpression<Professor>()
				.withIndexName("professorId")
				.withConsistentRead(false)
				.withKeyConditionExpression("professorId = :v1")
				.withExpressionAttributeValues(eav);
		List<Professor> result = mapper.query(Professor.class, queryExpression);
		if(result.size()==0) return null;
		return result.get(0);
	}
	
	// Add a Professor
	public Professor addProfessor(Professor prof) {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Professor> list = mapper.scan(Professor.class, scanExpression);
		prof.setProfessorId(prof.getFirstName()+String.valueOf(list.size()+1));
		mapper.save(prof);
		return prof;
	}
	
	//Delete a Professor
	public String deleteProfessor(String professorId) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(professorId));
		
		DynamoDBQueryExpression<Professor> queryExpression = new DynamoDBQueryExpression<Professor>()
				.withIndexName("professorId")
				.withConsistentRead(false)
				.withKeyConditionExpression("professorId = :v1")
				.withExpressionAttributeValues(eav);
		List<Professor> result = mapper.query(Professor.class, queryExpression);
		if(result.size()==0) return "Item does not exist!";
		mapper.delete(result.get(0));
		return "Delete Success";
	}
	
	//Update a Professor
	public Professor updateProfessor(String professorId,Professor prof) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(professorId));
		
		DynamoDBQueryExpression<Professor> queryExpression = new DynamoDBQueryExpression<Professor>()
				.withIndexName("professorId")
				.withConsistentRead(false)
				.withKeyConditionExpression("professorId = :v1")
				.withExpressionAttributeValues(eav);
		List<Professor> result = mapper.query(Professor.class, queryExpression);
		if(result.size()==0) return null;
		prof.setId(result.get(0).getId());
		mapper.save(prof);
		return prof;
	}
}
