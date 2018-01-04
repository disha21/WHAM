package com.neu.wham.exceptions;

public class LocationException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message;
	public enum LocationExceptionType {
		LOCATION_FORMAT_EXCEPTION,
		LOCATION_OUT_OF_BOUNDS,
		LOCATION_INCOMPLETE
	}
	
	public LocationException(LocationExceptionType type) {
		switch(type) {
		
		case LOCATION_FORMAT_EXCEPTION:
			message = "Coordinates are not valid. Please ensure latitude, longitude, and radius are numbers.";
			break;
		
		case LOCATION_OUT_OF_BOUNDS:
			message = "Coordinates are not valid. Please ensure latitude is between -90 and 90 and longitude is between -180 and 180.";
			break;
		
		case LOCATION_INCOMPLETE:
			message = "Coordinates are not valid. Please specify one latitude, one longitude, and one radius.";
			break;
			
		default:
			message = "Coordinates not valid.";
			break;
		}
	}
	
	public String getMessage() {
		return message;
	}

}
