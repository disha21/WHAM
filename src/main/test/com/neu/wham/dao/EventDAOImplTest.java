package com.neu.wham.dao;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
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
public class EventDAOImplTest {
	
	@Autowired
	private EventDAO eventDAO;
	
	/**
	 * Test for Location is null. (Negative Test case)
	 * @author Vijet Badigannavar
	 * @throws SQLException 
	 */
	@Test(expected=NullPointerException.class)
	public void eventLocationIsNull() throws SQLException{
		Event event = new Event();
		event.setEventName(null);
		eventDAO.addNewEvent(event);
	}
	
	/**
	 * Test for Event StartDateAndTime  is null. (Negative Test case)
	 * @author Vijet Badigannavar
	 * @throws SQLException 
	 */
	@Test(expected=NullPointerException.class)
	public void eventStartDateIsNull() throws SQLException{
		Event event = new Event();
		event.setEventName(null);
		eventDAO.addNewEvent(event);
	}
	
	/**
	 * Test for Event EndDateAndTime  is null. (Negative Test case)
	 * @author Vijet Badigannavar
	 * @throws SQLException 
	 */
	@Test(expected=NullPointerException.class)
	public void eventEndDateIsNull() throws SQLException{
		Event event = new Event();
		event.setEventName(null);
		event.setStartDateAndTime(new Date());
		event.setEndDateAndTime(null);
		eventDAO.addNewEvent(event);
	}
	
	/**
	 * Test for Event Creation time is null. (Negative Test case)
	 * @author Vijet Badigannavar
	 * @throws SQLException 
	 */
	@Test(expected=NullPointerException.class)
	public void eventCreationIsNull() throws SQLException{
		Event event = new Event();
		event.setEventLocation("Northeastern University");
		event.setStartDateAndTime(new Date());
		event.setEndDateAndTime(new Date());
		event.setCreationTime(null);
		event.setEventName(null);
		eventDAO.addNewEvent(event);
	}
	
	/**
	 * Test for Event Creation time is null. (Negative Test case)
	 * @author Vijet Badigannavar
	 * @throws SQLException 
	 */
	@Test(expected=NullPointerException.class)
	public void eventUpdationIsNull() throws SQLException{
		Event event = new Event();
		event.setEventLocation("Northeastern University");
		event.setStartDateAndTime(new Date());
		event.setEndDateAndTime(new Date());
		event.setCreationTime(new Date());
		event.setLastUpdateTime(null);
		event.setEventName(null);
		eventDAO.addNewEvent(event);
	}
	
	/**
	 * Test for Event Name is null. (Negative Test case)
	 * @author Vijet Badigannavar
	 * @throws SQLException 
	 */
	@Test(expected=SQLException.class)
	public void eventNameIsNull() throws SQLException{
		Event event = new Event();
		event.setEventLocation("Northeastern University");
		event.setStartDateAndTime(new Date());
		event.setEndDateAndTime(new Date());
		event.setCreationTime(new Date());
		event.setLastUpdateTime(new Date());
		event.setEventName(null);
		eventDAO.addNewEvent(event);
	}
	
	/**
	 * Test for Event Organizer is null. (Negative Test case)
	 * @author Vijet Badigannavar
	 * @throws SQLException 
	 */
	@Test(expected=SQLException.class)
	public void eventOrgainizerIsNull() throws SQLException{
		Event event = new Event();
		event.setEventLocation("Northeastern University");
		event.setStartDateAndTime(new Date());
		event.setEndDateAndTime(new Date());
		event.setCreationTime(new Date());
		event.setLastUpdateTime(new Date());
		event.setEventName("some event");
		event.setOrganiserName(null);
		eventDAO.addNewEvent(event);
	}
	
	/**
	 * Test for Event Organizer Desc is null. (Negative Test case)
	 * @author Vijet Badigannavar
	 * @throws SQLException 
	 */
	@Test(expected=SQLException.class)
	public void eventOrgainizerDescIsNull() throws SQLException{
		Event event = new Event();
		event.setEventLocation("Northeastern University");
		event.setStartDateAndTime(new Date());
		event.setEndDateAndTime(new Date());
		event.setCreationTime(new Date());
		event.setLastUpdateTime(new Date());
		event.setEventName("some event");
		event.setOrganiserName("some name");
		event.setOrganiserDesc(null);
		eventDAO.addNewEvent(event);
	}
	
	/**
	 * Test for Event Organizer Email is null. (Negative Test case)
	 * @author Vijet Badigannavar
	 * @throws SQLException 
	 */
	@Test(expected=SQLException.class)
	public void eventOrgainizerEmailIsNull() throws SQLException{
		Event event = new Event();
		event.setEventLocation("Northeastern University");
		event.setStartDateAndTime(new Date());
		event.setEndDateAndTime(new Date());
		event.setCreationTime(new Date());
		event.setLastUpdateTime(new Date());
		event.setEventName("some event");
		event.setOrganiserName("some name");
		event.setOrganiserDesc("somethign");
		event.setEmailId(null);
		eventDAO.addNewEvent(event);
	}
	
	/**
	 * Test for Event Location is null. (Negative Test case)
	 * @author Vijet Badigannavar
	 * @throws SQLException 
	 */
	@Test(expected=SQLException.class)
	public void eventOrgainizerLocationIsNull() throws SQLException{
		Event event = new Event();
		event.setEventLocation("Northeastern University");
		event.setStartDateAndTime(new Date());
		event.setEndDateAndTime(new Date());
		event.setCreationTime(new Date());
		event.setLastUpdateTime(new Date());
		event.setEventName("some event");
		event.setOrganiserName("some name");
		event.setOrganiserDesc("somethign");
		event.setEmailId("something");
		event.setEventLocation(null);
		eventDAO.addNewEvent(event);
	}
}
