package com.neu.wham.model;

import java.util.List;

public class UserPreference {
	private String userId;
	private List<EventType> eventType;
	private List<EventTopic> eventTopic;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<EventType> getEventType() {
		return eventType;
	}
	public void setEventType(List<EventType> eventType) {
		this.eventType = eventType;
	}
	public List<EventTopic> getEventTopic() {
		return eventTopic;
	}
	public void setEventTopic(List<EventTopic> eventTopic) {
		this.eventTopic = eventTopic;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventTopic == null) ? 0 : eventTopic.hashCode());
		result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserPreference other = (UserPreference) obj;
		if (eventTopic == null) {
			if (other.eventTopic != null)
				return false;
		} else if (!eventTopic.equals(other.eventTopic))
			return false;
		if (eventType == null) {
			if (other.eventType != null)
				return false;
		} else if (!eventType.equals(other.eventType))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
	
	
}
