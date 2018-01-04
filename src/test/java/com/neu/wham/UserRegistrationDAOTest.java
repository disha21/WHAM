package com.neu.wham;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

import com.neu.wham.dao.UserRegistrationDAOImpl;
import com.neu.wham.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("test-context.xml")
public class UserRegistrationDAOTest {
	// JDBC driver name and database URL
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private final String DB_URL = "jdbc:mysql://ec2-52-87-159-69.compute-1.amazonaws.com:3306/whamDB";

	//  Database credentials
	private final String USER = "wham";
	private final String PASS = "wham@123";
	
	UserRegistrationDAOImpl userRegistrationDAOImpl = new UserRegistrationDAOImpl();
	
	
	
	@Test
	public void testPasswordNeg() {
		User user = new User();
		user.setFirstName("test");
		user.setMiddleName("ma");
		user.setLastName("jason");
		user.setEmailId("jason@husky.neu.edu");
		user.setPhoneNo("1112221234");
		user.setPassword("abc");
		try {
			userRegistrationDAOImpl.createNewUser(user);
			Connection conn = null;
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			String sql_statement = "select * from USER where emailId = ?;";

			PreparedStatement ppdStmt = conn.prepareStatement(sql_statement);
			ppdStmt.setString(1, user.getEmailId());
			
			ResultSet rs = ppdStmt.executeQuery();
			User u = new User();
			if (rs.next()) {
				
				u.setUserId(rs.getInt("user_id"));
				u.setFirstName(rs.getString("first_name"));
				u.setMiddleName(rs.getString("middle_name"));
				u.setLastName(rs.getString("last_name"));
				u.setPhoneNo(rs.getString("phone_no"));
				u.setEmailId(rs.getString("emailId"));
				u.setPassword(rs.getString("password"));
			}
			Assert.isTrue(!u.getPassword().equals("abc"));
			
		} catch (NoSuchAlgorithmException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEmailPos() {
		User user = new User();
		user.setFirstName("test");
		user.setMiddleName("ma");
		user.setLastName("Green");
		user.setEmailId("Green@husky.neu.edu");
		user.setPhoneNo("1112221234");
		user.setPassword("abc");
		try {
			userRegistrationDAOImpl.createNewUser(user);
			Connection conn = null;
			
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			String sql_statement = "select * from USER where emailId = ?;";

			PreparedStatement ppdStmt = conn.prepareStatement(sql_statement);
			ppdStmt.setString(1, user.getEmailId());
			
			ResultSet rs = ppdStmt.executeQuery();
			User u = new User();
			if (rs.next()) {
				
				u.setUserId(rs.getInt("user_id"));
				u.setFirstName(rs.getString("first_name"));
				u.setMiddleName(rs.getString("middle_name"));
				u.setLastName(rs.getString("last_name"));
				u.setPhoneNo(rs.getString("phone_no"));
				u.setEmailId(rs.getString("emailId"));
				u.setPassword(rs.getString("password"));
			}
			
			assertEquals(user.getEmailId(), u.getEmailId());
		} catch (NoSuchAlgorithmException | SQLException e) {
			e.printStackTrace();
		}
	}
	

	
	@Test
	public void testPasswordNull() throws SQLException {
		User user = new User();
		user.setFirstName("test");
		user.setMiddleName("ma");
		user.setLastName("red");
		user.setEmailId("red@husky.neu.edu");
		user.setPhoneNo("1112221234");
		user.setPassword(null);
		try {
			userRegistrationDAOImpl.createNewUser(user);
			Connection conn = null;
			
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			String sql_statement = "select * from USER where emailId = ?;";

			PreparedStatement ppdStmt = conn.prepareStatement(sql_statement);
			ppdStmt.setString(1, user.getEmailId());
			
			ResultSet rs = ppdStmt.executeQuery();
			User u = new User();
			if (rs.next()) {
				
				u.setUserId(rs.getInt("user_id"));
				u.setFirstName(rs.getString("first_name"));
				u.setMiddleName(rs.getString("middle_name"));
				u.setLastName(rs.getString("last_name"));
				u.setPhoneNo(rs.getString("phone_no"));
				u.setEmailId(rs.getString("emailId"));
				u.setPassword(rs.getString("password"));
			}
			assertEquals(user.getPassword(), u.getPassword());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected=SQLException.class)
	public void testLastNameNeg() throws SQLException {
		User user = new User();
		user.setFirstName("test");
		user.setMiddleName("mi");
		user.setLastName(null);
		user.setEmailId("first@husky.neu.edu");
		user.setPhoneNo("1112221234");
		user.setPassword("1318hjd@");
		try {
			userRegistrationDAOImpl.createNewUser(user);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNameNeg() throws SQLException {
		User user = new User();
		user.setFirstName("test");
		user.setMiddleName("mi");
		user.setLastName("ßå∑∂ƒ©∂ß你好");
		user.setEmailId("mi@husky.neu.edu");
		user.setPhoneNo("1112221234");
		user.setPassword("1318hjd@");
		try {
			userRegistrationDAOImpl.createNewUser(user);
			Connection conn = null;
			
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			String sql_statement = "select * from USER where emailId = ?;";

			PreparedStatement ppdStmt = conn.prepareStatement(sql_statement);
			ppdStmt.setString(1, user.getEmailId());
			
			ResultSet rs = ppdStmt.executeQuery();
			User u = new User();
			if (rs.next()) {
				
				u.setUserId(rs.getInt("user_id"));
				u.setFirstName(rs.getString("first_name"));
				u.setMiddleName(rs.getString("middle_name"));
				u.setLastName(rs.getString("last_name"));
				u.setPhoneNo(rs.getString("phone_no"));
				u.setEmailId(rs.getString("emailId"));
				u.setPassword(rs.getString("password"));
			}
			
			assertEquals(user.getEmailId(), u.getEmailId());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidatedUser() throws SQLException{
		User user = new User();
		user.setFirstName("test");
		user.setMiddleName("mi");
		user.setLastName("Validate");
		user.setEmailId("tValidate@husky.neu.edu");
		user.setPhoneNo("1112221234");
		user.setPassword("neu1234");
		try {
			userRegistrationDAOImpl.createNewUser(user);
			User u = userRegistrationDAOImpl.validateUser("tValidate@husky.neu.edu", "neu1234");
			assertEquals(user.getEmailId(), u.getEmailId());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInValidatedUser() throws SQLException{
		User user = new User();
		user.setFirstName("test");
		user.setMiddleName("mi");
		user.setLastName("InValidate");
		user.setEmailId("tInValidate@husky.neu.edu");
		user.setPhoneNo("1112221234");
		user.setPassword("neu1234");
		try {
			userRegistrationDAOImpl.createNewUser(user);
			User u = userRegistrationDAOImpl.validateUser("tValidate@husky.neu.edu", "1234");
			assertEquals(u, null);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUserId() throws SQLException{
		User user = new User();
		user.setFirstName("test");
		user.setMiddleName("mi");
		user.setLastName("testUserId");
		user.setEmailId("testUserId@husky.neu.edu");
		user.setPhoneNo("1112221234");
		user.setPassword("neu1234");
		try {
			User newUser = userRegistrationDAOImpl.createNewUser(user);
			Connection conn = null;
			
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			String sql_statement = "select * from USER where emailId = ?;";

			PreparedStatement ppdStmt = conn.prepareStatement(sql_statement);
			ppdStmt.setString(1, user.getEmailId());
			
			ResultSet rs = ppdStmt.executeQuery();
			User u = new User();
			if (rs.next()) {
				
				u.setUserId(rs.getInt("user_id"));
				u.setFirstName(rs.getString("first_name"));
				u.setMiddleName(rs.getString("middle_name"));
				u.setLastName(rs.getString("last_name"));
				u.setPhoneNo(rs.getString("phone_no"));
				u.setEmailId(rs.getString("emailId"));
				u.setPassword(rs.getString("password"));
			}
			assertEquals(newUser.getUserId(), u.getUserId());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	// clean up
	@After
    public void doYourOneTimeTeardown() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		
		String query = "DELETE FROM USER WHERE first_name='test'";
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		
		try{
			int val = pstmt.executeUpdate();	
			if(val!=0){
				System.out.println("SUCCEED: " + val);
			}
			}catch(Exception e){
				e.printStackTrace();
				throw e;
			}
    } 
	

}
