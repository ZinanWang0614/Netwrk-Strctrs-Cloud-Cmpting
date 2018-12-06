package com.me.fall2018.assignment3.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.me.fall2018.assignment3.datamodel.Board;
import com.me.fall2018.assignment3.datamodel.DynamoDbConnector;

public class BoardService {

	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper;
	
	public BoardService() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	// Get All boards
	public List<Board> getAllBoards(){
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Board> list = mapper.scan(Board.class, scanExpression);
		return list;
	}
	
	// Get a board by boardId
	public Board getBoard(String boardId) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(boardId));
		
		DynamoDBQueryExpression<Board> queryExpression = new DynamoDBQueryExpression<Board>()
				.withIndexName("boardId")
				.withConsistentRead(false)
				.withKeyConditionExpression("boardId = :v1")
				.withExpressionAttributeValues(eav);
		
		List<Board> result = mapper.query(Board.class, queryExpression);
		if(result.size() == 0) return null;
		return result.get(0);
	}
	
	// Add a board
	public Board addBoard(Board board) {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Board> list = mapper.scan(Board.class, scanExpression);
		board.setBoardId(String.valueOf(list.size()+1));
		mapper.save(board);
		return board;
	}
	
	//update a board
	public Board updateBoard(String boardId, Board board) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(boardId));
		
		DynamoDBQueryExpression<Board> queryExpression = new DynamoDBQueryExpression<Board>()
				.withIndexName("boardId")
				.withConsistentRead(false)
				.withKeyConditionExpression("boardId = :v1")
				.withExpressionAttributeValues(eav);
		
		List<Board> result = mapper.query(Board.class, queryExpression);
		if(result.size() == 0) return null;
		board.setId(result.get(0).getId());
		board.setBoardId(result.get(0).getBoardId());
		mapper.save(board);
		return board;
	}
	
	//delete a board
	public String deleteBoard(String boardId) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(boardId));
		
		DynamoDBQueryExpression<Board> queryExpression = new DynamoDBQueryExpression<Board>()
				.withIndexName("boardId")
				.withConsistentRead(false)
				.withKeyConditionExpression("boardId = :v1")
				.withExpressionAttributeValues(eav);
		
		List<Board> result = mapper.query(Board.class, queryExpression);
		if(result.size()==0) return "Item does not exist!";
		mapper.delete(result.get(0));
		return "Delete Success";
	}
}
