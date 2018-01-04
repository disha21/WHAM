package com.neu.wham.validations;

public class UserIdValidation {
	public static String validateUserId(String userId) {
		try {
			Integer.parseInt(userId);
			return userId;
		} catch(NumberFormatException e) {
			System.out.println("Warning: user id is not valid.  Returning null.");
			return null;
		}
	}
}
