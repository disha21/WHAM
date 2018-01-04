package com.neu.wham.services;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.wham.dao.UserRegistrationDAO;
import com.neu.wham.exceptions.RegistrationFailedException;
import com.neu.wham.model.User;
@Service

public class UserRegistrationImpl implements UserRegistrationService{

	@Autowired
	private UserRegistrationDAO registrationDAO;
	
	@Override
	public User registerUser(User user){
		
		 try {
			return registrationDAO.createNewUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		 return null;
	}

	@Override
	public User validateUser(String emailId, String password){
		try {
			return registrationDAO.validateUser(emailId, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	}

}
