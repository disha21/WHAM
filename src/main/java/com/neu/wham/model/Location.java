package com.neu.wham.model;

public class Location {
	
	private String location;
	private String address;
	
	public Location(String location, String address) {
		this.location = location;
		this.address = address;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
