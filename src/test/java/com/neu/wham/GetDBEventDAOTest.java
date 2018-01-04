package com.neu.wham;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.neu.wham.dao.EventDAOImpl;
import com.neu.wham.dao.PreferenceDAOImpl;
import com.neu.wham.model.Event;
import com.neu.wham.model.UserSelectedPreference;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("test-context.xml")
public class GetDBEventDAOTest {
	
	
	// JDBC driver name and database URL
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private final String DB_URL = "jdbc:mysql://ec2-52-87-159-69.compute-1.amazonaws.com:3306/whamDB";

	//  Database credentials
	private final String USER = "wham";
	private final String PASS = "wham@123";
	
	private EventDAOImpl eventDAOImpl = new EventDAOImpl();
	
	@Autowired
    private WebApplicationContext context;

	
	//events 
	Event e1 = new Event();
	Event e2 = new Event();
	Event e3 = new Event();
	Event e4 = new Event();
	
	
	 @Before
	 public void setup() throws ParseException, SQLException {
			
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date d = sdf.parse("2016-03-21 11:00:01");
			
			
			e1.setEventName("e1");
			e1.setEventDesc("dummy event for test");
			e1.setLatitude(42.3389);
			e1.setLongitude(-71.0903);
			e1.setStartDateAndTime(d);
			e1.setEndDateAndTime(d);
			e1.setCreationTime(d);
			e1.setLastUpdateTime(d);
			e1.setOfficialEvent(true);
			e1.setPhoneNumber("617232645");
			e1.setEmailId("email@husky.neu.edu");
			e1.setEventLocation("360 Huntington Ave., Boston, Massachusetts 02115");
			e1.setOrganiserName("Mike");
			e1.setOrganiserDesc("a description");
			e1.setFilePath("");
			
			
			e2.setEventName("e2");
			e2.setEventDesc("dummy event for test");
			e2.setLatitude(45);
			e2.setLongitude(45);
			e2.setStartDateAndTime(d);
			e2.setEndDateAndTime(d);
			e2.setCreationTime(d);
			e2.setLastUpdateTime(d);
			e2.setOfficialEvent(true);
			e2.setPhoneNumber("617232645");
			e2.setEmailId("email@husky.neu.edu");
			e2.setEventLocation("360 Huntington Ave., Boston, Massachusetts 02115");
			e2.setOrganiserName("Mike");
			e2.setOrganiserDesc("a description");
			e2.setFilePath("");
			
			
			 //4 miles from e1                  
			e3.setEventName("e3");      
			e3.setEventDesc("dummy event for test");
			e3.setLatitude(42.2775);
			e3.setLongitude(-71.1234);
			e3.setStartDateAndTime(d);
			e3.setEndDateAndTime(d);
			e3.setCreationTime(d);
			e3.setLastUpdateTime(d);
			e3.setOfficialEvent(true);
			e3.setPhoneNumber("617232645");
			e3.setEmailId("email@husky.neu.edu");
			e3.setEventLocation("360 Huntington Ave., Boston, Massachusetts 02115");
			e3.setOrganiserName("Mike");
			e3.setOrganiserDesc("a description");
			e3.setFilePath("");
			e4.setEventType(3);
			
			//15 miles from e1                       
			e4.setEventName("e4");
			e4.setEventDesc("dummy event for test");
			e4.setLatitude(42.4667);
			e4.setLongitude(-71.3456);
			e4.setStartDateAndTime(d);
			e4.setEndDateAndTime(d);
			e4.setCreationTime(d);
			e4.setLastUpdateTime(d);
			e4.setOfficialEvent(true);
			e4.setPhoneNumber("617232645");
			e4.setEmailId("email@husky.neu.edu");
			e4.setEventLocation("360 Huntington Ave., Boston, Massachusetts 02115");
			e4.setOrganiserName("Mike");
			e4.setOrganiserDesc("a description");
			e4.setFilePath("");
			e4.setEventTopic(5);
			e4.setEventType(1);
			
			eventDAOImpl.addNewEvent(e1);
			eventDAOImpl.addNewEvent(e2);
			eventDAOImpl.addNewEvent(e3);
			eventDAOImpl.addNewEvent(e4);
		 
	 }
	
	// testcase 21
	// get database events according to latitude, longtitude and radius
	@Test 
	public void getDBEventTest_withoutUserId() throws SQLException, JSONException, UnirestException{
		
		List<Event> eventList = eventDAOImpl.getEventsData("45", "45", "15", null);
				
		Assert.isTrue(eventList.size() == 1);
		Assert.isTrue(eventList.get(0).getEventName().equals("e2"));
	}
	
	
	/*
	 * getDBEvent based on userId
	 */
	
	// valid userId
	// get database events according to latitude, longitude, radius and valid userId
	// invalid userId is tested in getEventServiceImpl
	@Test 
	public void getDBEventTest_withValidUserId() throws NumberFormatException, Exception{
				
		// get 9 preference items
		UserSelectedPreference userPref = GetEventServiceUtil.getUserPreference_withUserId13();
			
		List<Event> eventList = eventDAOImpl.getEventsData("42.4667", "-71.3456", "15", userPref);
			
		// after filter, there is only 1 
		Assert.isTrue(eventList.size() == 1);
		Assert.isTrue(eventList.get(0).getEventName().equals("e4"));
	}
	
	
	
	// clean up
	@After
    public void doYourOneTimeTeardown() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		
		String query = "DELETE FROM EVENT WHERE name='e1' OR name='e2' OR name='e3' OR name='e4'";
		
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
