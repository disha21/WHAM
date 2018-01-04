package com.neu.wham.model;

public class SelectedPreference {
	private int eventId;
	private int eventCategory;
	
	
	public SelectedPreference() {
		// TODO Auto-generated constructor stub
	}
	public SelectedPreference(int eventId, int eventCategory) {
		super();
		this.eventId = eventId;
		this.eventCategory = eventCategory;
	}
	
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public int getEventCategory() {
		return eventCategory;
	}
	public void setEventCategory(int eventCategory) {
		this.eventCategory = eventCategory;
	}
	
	
	
}
