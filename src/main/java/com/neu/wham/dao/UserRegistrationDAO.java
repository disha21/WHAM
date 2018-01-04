package com.neu.wham.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import com.neu.wham.model.User;

public interface UserRegistrationDAO {
	public User createNewUser(User user) throws SQLException, NoSuchAlgorithmException;
	public User validateUser(String emailId, String password) throws SQLException, NoSuchAlgorithmException;
}
