package com.neu.wham.services;

import com.neu.wham.exceptions.RegistrationFailedException;
import com.neu.wham.model.User;

public interface UserRegistrationService {
	public User registerUser(User user);
	public User validateUser(String emailId, String password);
}
