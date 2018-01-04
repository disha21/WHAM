package com.neu.wham.services;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.neu.wham.messages.EventPostResponse;
import com.neu.wham.messages.EventPostResponse.Status;
import com.neu.wham.model.Event;

/**
 * Test class to test Service Layer
 * @author Vijet Badigannavar
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-servlet-context.xml" })
 public class EventServiceImplTest {
	@Autowired 
	private NewEventService eventserviceImpl; 
	/**
	 * Test for Location is null. (Negative Test case)
	 * @author Vijet Badigannavar
	 */
	@Test
	public void eventLocationIsNull(){
		Event event = new Event();
		event.setEventName(null);
		EventPostResponse testResponse = eventserviceImpl.submitNewEvent(event, null);
		assertEquals(Status.ERROR, testResponse.getStatus());
	}
	
	/**
	 * Test for Event Name is null. (Negative Test case)
	 * @author Vijet Badigannavar
	 */
	@Test
	public void eventNameisNull(){
		Event event = new Event();
		event.setEventName(null);
		event.setEventLocation("Northeastern University");
		event.setCreationTime(new Date());
		event.setLastUpdateTime(new Date());
		event.setEndDateAndTime(new Date());
		event.setStartDateAndTime(new Date());
		EventPostResponse testResponse = eventserviceImpl.submitNewEvent(event, null);
		assertEquals(Status.ERROR, testResponse.getStatus());
	}
	
	/**
	 * Test for Event Desc is null. (Negative Test case)
	 * @author Vijet Badigannavar
	 */
	@Test
	public void eventDescisNull(){
		Event event = new Event();
		event.setEventName(null);
		event.setEventDesc(null);
		event.setEventLocation("Northeastern University");
		event.setCreationTime(new Date());
		event.setLastUpdateTime(new Date());
		event.setEndDateAndTime(new Date());
		event.setStartDateAndTime(new Date());
		EventPostResponse testResponse = eventserviceImpl.submitNewEvent(event, null);
		assertEquals(Status.ERROR, testResponse.getStatus());
	}
	
	/**
	 * Test for eventEmail is null. (Negative Test case)
	 * @author Vijet Badigannavar
	 */
	@Test
	public void eventEmailisNull(){
		Event event = new Event();
		event.setEventName(null);
		event.setEventDesc(null);
		event.setEmailId(null);
		event.setEventLocation("Northeastern University");
		event.setCreationTime(new Date());
		event.setLastUpdateTime(new Date());
		event.setEndDateAndTime(new Date());
		event.setStartDateAndTime(new Date());
		EventPostResponse testResponse = eventserviceImpl.submitNewEvent(event, null);
		assertEquals(Status.ERROR, testResponse.getStatus());
	}
	
	/**
	 * Test for eventOrgName is null. (Negative Test case)
	 * @author Vijet Badigannavar
	 */
	@Test
	public void eventOrgNameisNull(){
		Event event = new Event();
		event.setEventName(null);
		event.setEventDesc(null);
		event.setEmailId(null);
		event.setOrganiserName(null);
		event.setEventLocation("Northeastern University");
		event.setCreationTime(new Date());
		event.setLastUpdateTime(new Date());
		event.setEndDateAndTime(new Date());
		event.setStartDateAndTime(new Date());
		EventPostResponse testResponse = eventserviceImpl.submitNewEvent(event, null);
		assertEquals(Status.ERROR, testResponse.getStatus());
	}
	
	/**
	 * Test for eventOrganizationDescription is null. (Negative Test case)
	 * @author Vijet Badigannavar
	 */
	@Test
	public void eventOrgDescisNull(){
		Event event = new Event();
		event.setEventName(null);
		event.setEventDesc(null);
		event.setEmailId(null);
		event.setOrganiserDesc(null);
		event.setEventLocation("Northeastern University");
		event.setCreationTime(new Date());
		event.setLastUpdateTime(new Date());
		event.setEndDateAndTime(new Date());
		event.setStartDateAndTime(new Date());
		EventPostResponse testResponse = eventserviceImpl.submitNewEvent(event, null);
		assertEquals(Status.ERROR, testResponse.getStatus());
	}
	
	/**
	 * Test for valid Event is null. (Positive Test case)
	 * @author Vijet Badigannavar
	 */
	@Test
	public void putAnEvent(){
		Event event = new Event();
		event.setEventName("Holi123");
		event.setEventDesc("Festival of Colors");
		event.setEmailId("sanskruti@husky.neu.edu");
		event.setOrganiserName("NEU-SANSKRUTI");
		event.setOfficialEvent(true);
		event.setOrganiserDesc("NEU Sanskruti team");
		event.setEventLocation("Northeastern University");
		event.setCreationTime(new Date());
		event.setLastUpdateTime(new Date());
		event.setEndDateAndTime(new Date());
		event.setStartDateAndTime(new Date());
		EventPostResponse testResponse = eventserviceImpl.submitNewEvent(event, null);
		assertEquals(Status.OK, testResponse.getStatus());
		deleteInsertedEvent();
	}
	/**
	 * Delete the inserted event
	 */
	private void deleteInsertedEvent(){
		String stmt = "delete from EVENT where name='Holi123'";
		
		// JDBC driver name and database URL
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		final String DB_URL = "jdbc:mysql://ec2-52-87-159-69.compute-1.amazonaws.com:3306/whamDB";

		//  Database credentials
		final String USER = "wham";
		final String PASS = "wham@123";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			Statement stmt1 = conn.createStatement();
		      stmt1.executeUpdate(stmt);
		      conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		}
	}
}
