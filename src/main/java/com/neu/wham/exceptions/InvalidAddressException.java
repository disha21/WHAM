package com.neu.wham.exceptions;


/**
 * Exception thrown when the input address is invalid
 * @author Vijet Badigannnavar
 */
public class InvalidAddressException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5843688533964973513L;

	public InvalidAddressException(String message) {
		super(message);
	}
}
