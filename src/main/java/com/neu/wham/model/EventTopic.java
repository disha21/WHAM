package com.neu.wham.model;

import java.util.List;

public class EventTopic {
	private int topicId;
	private String topicName;
	private List<EventType> subTopicsList;
	
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public List<EventType> getSubTopicsList() {
		return subTopicsList;
	}
	public void setSubTopicsList(List<EventType> subTopicsList) {
		this.subTopicsList = subTopicsList;
	}
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	
	
}
