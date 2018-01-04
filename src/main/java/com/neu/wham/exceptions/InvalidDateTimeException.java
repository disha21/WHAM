package com.neu.wham.exceptions;


/**
 * Exception thrown when the input start date time & end date time is invalid
 * @author Xi Wang
 */
public class InvalidDateTimeException extends Exception{
	private static final long serialVersionUID = 2L;
	
	public InvalidDateTimeException(String message) {
		super(message);
	}
}
