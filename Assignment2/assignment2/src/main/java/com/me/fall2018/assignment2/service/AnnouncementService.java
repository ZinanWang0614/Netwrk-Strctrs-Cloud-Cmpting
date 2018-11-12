package com.me.fall2018.assignment2.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.me.fall2018.assignment2.datamodel.Announcement;
import com.me.fall2018.assignment2.datamodel.DynamoDbConnector;

public class AnnouncementService {

	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper;

	public AnnouncementService() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	//Get All Announcements
	public List<Announcement> getAllAnnouncements(){
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Announcement> list = mapper.scan(Announcement.class,scanExpression);
		return list;
	}
	
	// Get Announcements based on boardId
	public List<Announcement> getBoardAns(String boardId){
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(boardId));
		
		DynamoDBQueryExpression<Announcement> queryExpression = new DynamoDBQueryExpression<Announcement>()
				.withIndexName("boardId-announcementId-index")
				.withConsistentRead(false)
				.withKeyConditionExpression("boardId = :v1")
				.withExpressionAttributeValues(eav);
		
		List<Announcement> result = mapper.query(Announcement.class, queryExpression);
		return result;
	}
	
	// Get an Announcement
	public Announcement getAn(String boardId, String announcementId) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(boardId));
		eav.put(":v2",  new AttributeValue().withS(announcementId));
		
		DynamoDBQueryExpression<Announcement> queryExpression = new DynamoDBQueryExpression<Announcement>()
				.withIndexName("boardId-announcementId-index")
				.withConsistentRead(false)
				.withKeyConditionExpression("boardId = :v1 and announcementId = :v2")
				.withExpressionAttributeValues(eav);
		
		List<Announcement> result = mapper.query(Announcement.class, queryExpression);
		if(result.size() == 0) return null;
		return result.get(0);
	}
	
	//Add a announcement
	public Announcement addAnnouncement(String boardId,Announcement an) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(boardId));
		
		DynamoDBQueryExpression<Announcement> queryExpression = new DynamoDBQueryExpression<Announcement>()
				.withIndexName("boardId-announcementId-index")
				.withConsistentRead(false)
				.withKeyConditionExpression("boardId = :v1")
				.withExpressionAttributeValues(eav);
		
		List<Announcement> result = mapper.query(Announcement.class, queryExpression);
		
		an.setBoardId(boardId);
		an.setAnnouncementId(String.valueOf(result.size()+1));
		mapper.save(an);
		return an;
	}
	
	//Update an announcement
	public Announcement updateAn(String boardId,String announcementId, Announcement an) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(boardId));
		eav.put(":v2",  new AttributeValue().withS(announcementId));
		
		DynamoDBQueryExpression<Announcement> queryExpression = new DynamoDBQueryExpression<Announcement>()
				.withIndexName("boardId-announcementId-index")
				.withConsistentRead(false)
				.withKeyConditionExpression("boardId = :v1 and announcementId = :v2")
				.withExpressionAttributeValues(eav);
		
		List<Announcement> result = mapper.query(Announcement.class, queryExpression);
		if(result.size() == 0) return null;
		an.setId(result.get(0).getId());
		an.setBoardId(result.get(0).getBoardId());
		an.setAnnouncementId(result.get(0).getAnnouncementId());
		
		mapper.save(an);
		return an;
	}
	
	//Delete an announcement
	public String deleteAn(String boardId,String announcementId) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(boardId));
		eav.put(":v2",  new AttributeValue().withS(announcementId));
		
		DynamoDBQueryExpression<Announcement> queryExpression = new DynamoDBQueryExpression<Announcement>()
				.withIndexName("boardId-announcementId-index")
				.withConsistentRead(false)
				.withKeyConditionExpression("boardId = :v1 and announcementId = :v2")
				.withExpressionAttributeValues(eav);
		
		List<Announcement> result = mapper.query(Announcement.class, queryExpression);
		if(result.size() == 0) return "Item dose not exist!";
		mapper.delete(result.get(0));
		return "Delete success!";
	}
}
