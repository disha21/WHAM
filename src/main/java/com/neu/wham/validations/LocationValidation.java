package com.neu.wham.validations;

import com.neu.wham.exceptions.LocationException;
import com.neu.wham.exceptions.LocationException.LocationExceptionType;

public class LocationValidation {
	public static String validateLatitude(String lat) throws LocationException {
		double dLat = parseCoordinate(lat); 
		
		if(dLat < -90 || dLat > 90)
			throw new LocationException(LocationExceptionType.LOCATION_OUT_OF_BOUNDS);
		
		return lat;
	}
	
	public static String validateLongitude(String lon) throws LocationException {
		double dLong = parseCoordinate(lon);
		
		if(dLong < -180 || dLong > 180)
			throw new LocationException(LocationExceptionType.LOCATION_OUT_OF_BOUNDS);
		
		return lon;
	}
	
	public static String validateRadius(String r) throws LocationException {
			
		if(!r.matches("^[0-9]*$") || (parseCoordinate(r) <= 0)){
			return "10";
		}
			
		return r;
	}
	
	private static double parseCoordinate(String val) throws LocationException {
		try {
			return Double.parseDouble(val);
		} catch(NumberFormatException e) {
			throw new LocationException(LocationExceptionType.LOCATION_FORMAT_EXCEPTION);
		} catch(NullPointerException e) {
			throw new LocationException(LocationExceptionType.LOCATION_INCOMPLETE);
		}
	}

}
