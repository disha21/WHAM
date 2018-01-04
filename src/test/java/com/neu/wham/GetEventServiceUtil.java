package com.neu.wham;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.neu.wham.dao.PreferenceDAO;
import com.neu.wham.dao.PreferenceDAOImpl;
import com.neu.wham.model.Event;
import com.neu.wham.model.PreferencesStore;
import com.neu.wham.model.UserPreference;
import com.neu.wham.model.UserSelectedPreference;
import com.neu.wham.services.PreferenceServiceImpl;

public class GetEventServiceUtil {
	
	private static PreferenceServiceImpl prefServiceImpl;
	@Autowired
	private static PreferenceDAO preferenceDAO;
	
	public static JSONArray getCannedEventbriteResponse_withoutUserId() throws JSONException {
		JSONArray cannedEvents = new JSONArray();
		
		// first object
		JSONObject event1 = new JSONObject();
		
		// create address object
		JSONObject address1 = new JSONObject();
		address1.put("country", "KG");
		address1.put("city", "Bishkek");
		address1.put("address_1", "Gorky Street");
		address1.put("address_2", "null");
		address1.put("latitude", "42.856827");
		address1.put("longitude", "74.61858410000002");
		address1.put("region", "Chuy Province");
		address1.put("postal_code", "null");
		
		// create venue object
		JSONObject venue1 = new JSONObject();
		venue1.put("address", address1);
		
		// create name object
		JSONObject name1 = new JSONObject();
		name1.put("text", "Boston Calling - May 27, 28, 29, 2016");
		name1.put("html", "Boston Calling - May 27, 28, 29, 2016");
		
		// create description object
		JSONObject description1 = new JSONObject();
		String desText1 = "event1 description mock";
		description1.put("text", desText1);
		description1.put("html", desText1);
		
		// create start object
		JSONObject start1 = new JSONObject();
		start1.put("timezone", "Asia/Bishkek");
		start1.put("local", "2016-04-05T18:30:00");
		start1.put("utc", "2016-04-05T12:30:00Z");
		
		// create end object
		JSONObject end1 = new JSONObject();
		end1.put("timezone", "Asia/Bishkek");
		end1.put("local", "2016-04-05T20:30:00");
		end1.put("utc", "2016-04-05T14:30:00Z");
		
		event1.put("name", name1);
		event1.put("description", description1);
		event1.put("start", start1);
		event1.put("end", end1);
		event1.put("created", "2016-04-02T14:48:24Z");
		event1.put("changed", "2016-04-02T14:48:26Z");
		event1.put("venue", venue1);
		
		
		
		// second object
		JSONObject event2 = new JSONObject();
				
		// create address object
		JSONObject address2 = new JSONObject();
		address2.put("country", "UZ");
		address2.put("city", "Tashkent");
		address2.put("address_1", "A. Navoi street 21");
		address2.put("address_2", "Shaykhontokhur district");
		address2.put("latitude", "41.2994958");
		address2.put("longitude", "69.24007340000003");
		address2.put("region", "null");
		address2.put("postal_code", "null");
				
		// create venue object
		JSONObject venue2 = new JSONObject();
		venue2.put("address", address2);
				
		// create name object
		JSONObject name2 = new JSONObject();
		name2.put("text", "IELTS lessons taught by one of the top IELTS teachers in Uzbekistan");
		name2.put("html", "IELTS lessons taught by one of the top IELTS teachers in Uzbekistan");
				
		// create description object
		JSONObject description2 = new JSONObject();
		String desText2 = "event2 description mock";
		description2.put("text", desText2);
		description2.put("html", desText2);
				
		// create start object
		JSONObject start2 = new JSONObject();
		start2.put("timezone", "Asia/Tashkent");
		start2.put("local", "2016-04-04T15:30:00");
		start2.put("utc", "2016-04-04T10:30:00Z");
				
		// create end object
		JSONObject end2 = new JSONObject();
		end2.put("timezone", "Asia/Tashkent");
		end2.put("local", "2016-06-03T17:30:00");
		end2.put("utc", "2016-06-03T12:30:00Z");
				
		event2.put("name", name2);
		event2.put("description", description2);
		event2.put("start", start2);
		event2.put("end", end2);
		event2.put("created", "2016-03-23T13:15:47Z");
		event2.put("changed", "2016-04-04T11:33:11Z");
		event2.put("venue", venue2);
		
		
		
		// second object
		JSONObject event3 = new JSONObject();
						
		// create address object
		JSONObject address3 = new JSONObject();
		address3.put("country", "UZ");
		address3.put("city", "Tashkent");
		address3.put("address_1", "null");
		address3.put("address_2", "null");
		address3.put("latitude", "41.3125");
		address3.put("longitude", "69.39027399999998");
		address3.put("region", "null");
		address3.put("postal_code", "100001");
						
		// create venue object
		JSONObject venue3 = new JSONObject();
		venue3.put("address", address3);
						
	    // create name object
		JSONObject name3 = new JSONObject();
		name3.put("text", "Get Traction: The Virtual Growth Event [Tashkent]");
		name3.put("html", "Get Traction: The Virtual Growth Event [Tashkent]");
						
		// create description object
		JSONObject description3 = new JSONObject();
		String desText3 = "event3 description mock";
		description3.put("text", desText3);
		description3.put("html", desText3);
						
		// create start object
		JSONObject start3 = new JSONObject();
		start3.put("timezone", "Asia/Samarkand");
		start3.put("local", "2016-03-01T10:00:00");
		start3.put("utc", "2016-03-01T05:00:00Z");
						
		// create end object
		JSONObject end3 = new JSONObject();
		end3.put("timezone", "Asia/Samarkand");
		end3.put("local", "2016-08-04T14:00:00");
		end3.put("utc", "2016-08-04T09:00:00Z");
						
		event3.put("name", name3);
		event3.put("description", description3);
		event3.put("start", start3);
		event3.put("end", end3);
		event3.put("created", "2016-03-23T13:15:47Z");
		event3.put("changed", "2016-04-04T11:33:11Z");
		event3.put("venue", venue3);
		
		
		
		
		cannedEvents.put(event1);
		cannedEvents.put(event2);
		cannedEvents.put(event3);
				
		
		System.out.println("return cannedEvents: " + cannedEvents);
		return cannedEvents;
	}
	
	public static JSONArray getCannedEventbriteResponse_withUserId13() throws JSONException {
		JSONArray cannedEvents = new JSONArray();
		
		
		// first object
		JSONObject event1 = new JSONObject();
		
		// create address object
		JSONObject address1 = new JSONObject();
		address1.put("country", "US");
		address1.put("city", "Boston");
		address1.put("address_1", "25 Union Street");
		address1.put("address_2", "null");
		address1.put("latitude", "42.36099480000001");
		address1.put("longitude", "-71.05671869999998");
		address1.put("region", "MA");
		address1.put("postal_code", "02108");
		
		// create venue object
		JSONObject venue1 = new JSONObject();
		venue1.put("address", address1);
		
		// create name object
		JSONObject name1 = new JSONObject();
		name1.put("text", "Laugh Owt Loud 2.0");
		name1.put("html", "Laugh Owt Loud 2.0");
		
		// create description object
		JSONObject description1 = new JSONObject();
		String desText1 = "event1 description mock";
		description1.put("text", desText1);
		description1.put("html", desText1);
		
		// create start object
		JSONObject start1 = new JSONObject();
		start1.put("timezone", "America/New_York");
		start1.put("local", "2016-04-10T18:30:00");
		start1.put("utc", "2016-04-10T22:30:00Z");
		
		// create end object
		JSONObject end1 = new JSONObject();
		end1.put("timezone", "America/New_York");
		end1.put("local", "2016-04-10T21:30:00");
		end1.put("utc", "2016-04-11T01:30:00Z");
		
		event1.put("name", name1);
		event1.put("description", description1);
		event1.put("start", start1);
		event1.put("end", end1);
		event1.put("created", "2016-03-11T00:29:04Z");
		event1.put("changed", "2016-04-07T00:34:37Z");
		event1.put("venue", venue1);
		
		
		// second object
		JSONObject event2 = new JSONObject();
				
		// create address object
		JSONObject address2 = new JSONObject();
		address2.put("country", "US");
		address2.put("city", "Boston");
		address2.put("address_1", "180 Berkeley Street");
		address2.put("address_2", "null");
		address2.put("latitude", "42.3496554");
		address2.put("longitude", "-71.07227549999999");
		address2.put("region", "MA");
		address2.put("postal_code", "02116");
				
		// create venue object
		JSONObject venue2 = new JSONObject();
		venue2.put("address", address2);
				
		// create name object
		JSONObject name2 = new JSONObject();
		name2.put("text", "Dan Crohn's Live Album Recording!");
		name2.put("html", "Dan Crohn's Live Album Recording!");
				
		// create description object
		JSONObject description2 = new JSONObject();
		String desText2 = "event2 description mock";
		description2.put("text", desText2);
		description2.put("html", desText2);
				
		// create start object
		JSONObject start2 = new JSONObject();
		start2.put("timezone", "America/New_York");
		start2.put("local", "2016-04-21T20:30:00");
		start2.put("utc", "2016-04-22T00:30:00Z");
				
		// create end object
		JSONObject end2 = new JSONObject();
		end2.put("timezone", "America/New_York");
		end2.put("local", "2016-04-21T22:30:00");
		end2.put("utc", "2016-04-22T02:30:00Z");
				
		event2.put("name", name2);
		event2.put("description", description2);
		event2.put("start", start2);
		event2.put("end", end2);
		event2.put("created", "2016-03-30T04:58:17Z");
		event2.put("changed", "2016-04-05T18:05:15Z");
		event2.put("venue", venue2);
		
		
		
		// third object
		JSONObject event3 = new JSONObject();
						
		// create address object
		JSONObject address3 = new JSONObject();
		address3.put("country", "US");
		address3.put("city", "Somerville");
		address3.put("address_1", "255 Elm Street");
		address3.put("address_2", "null");
		address3.put("latitude", "42.3955175");
		address3.put("longitude", "-71.1218808");
		address3.put("region", "MA");
		address3.put("postal_code", "02143");
						
		// create venue object
		JSONObject venue3 = new JSONObject();
		venue3.put("address", address3);
						
	    // create name object
		JSONObject name3 = new JSONObject();
		name3.put("text", "Roast Battle Boston");
		name3.put("html", "Roast Battle Boston");
						
		// create description object
		JSONObject description3 = new JSONObject();
		String desText3 = "event3 description mock";
		description3.put("text", desText3);
		description3.put("html", desText3);
						
		// create start object
		JSONObject start3 = new JSONObject();
		start3.put("timezone", "America/New_York");
		start3.put("local", "2016-04-28T19:00:00");
		start3.put("utc", "2016-04-28T23:00:00Z");
						
		// create end object
		JSONObject end3 = new JSONObject();
		end3.put("timezone", "America/New_York");
		end3.put("local", "2016-04-28T21:00:00");
		end3.put("utc", "2016-04-29T01:00:00Z");
						
		event3.put("name", name3);
		event3.put("description", description3);
		event3.put("start", start3);
		event3.put("end", end3);
		event3.put("created", "2016-02-06T20:34:54Z");
		event3.put("changed", "2016-04-04T18:59:06Z");
		event3.put("venue", venue3);
		
		
		
		// 4th object
		JSONObject event4 = new JSONObject();
								
		// create address object
		JSONObject address4 = new JSONObject();
		address4.put("country", "US");
		address4.put("city", "Boston");
		address4.put("address_1", "736 Commonwealth Avenue");
		address4.put("address_2", "null");
		address4.put("latitude", "42.350267");
		address4.put("longitude", "-71.10774000000004");
		address4.put("region", "MA");
		address4.put("postal_code", "02215");
								
		// create venue object
		JSONObject venue4 = new JSONObject();
		venue4.put("address", address4);
								
		// create name object
		JSONObject name4 = new JSONObject();
		name4.put("text", "Pavement Comedy Night: Spring Has Sprung");
		name4.put("html", "Pavement Comedy Night: Spring Has Sprung");
								
		// create description object
		JSONObject description4 = new JSONObject();
		String desText4 = "event4 description mock";
	    description4.put("text", desText4);
		description4.put("html", desText4);
								
		// create start object
		JSONObject start4 = new JSONObject();
		start4.put("timezone", "America/New_York");
		start4.put("local", "2016-04-15T19:00:00");
		start4.put("utc", "2016-04-15T23:00:00Z");
								
		// create end object
		JSONObject end4 = new JSONObject();
		end4.put("timezone", "America/New_York");
		end4.put("local", "2016-04-15T20:30:00");
		end4.put("utc", "2016-04-16T00:30:00Z");
							
		event4.put("name", name4);
		event4.put("description", description4);
		event4.put("start", start4);
		event4.put("end", end4);
		event4.put("created", "2016-02-06T20:34:54Z");
		event4.put("changed", "2016-04-04T18:59:06Z");
		event4.put("venue", venue4);
		
		
		
		
		cannedEvents.put(event1);
		cannedEvents.put(event2);
		cannedEvents.put(event3);
		cannedEvents.put(event4);
		
		System.out.println("finish eventbrite response with 13");
		
		return cannedEvents;
	}
	
	public static UserSelectedPreference getUserPreference_withUserId13() throws NumberFormatException, Exception {
		PreferenceDAOImpl preferenceDAO = new PreferenceDAOImpl();
		String userId = "13";
		
		UserSelectedPreference userPref = preferenceDAO.getUserPreferences(Integer.valueOf(userId));
		
		return userPref;
	}
	
	public static List<Event> getCannedDBResponse_withUserId13() throws ParseException{
		List<Event> events = new ArrayList<Event>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = sdf.parse("2016-04-01 11:00:01");
		
		//events 
		Event e1 = new Event();
		Event e2 = new Event();
		
		e1.setEventName("event1 from DB");
		e1.setEventDesc("dummy event for test");
		e1.setLatitude(42.4667);
		e1.setLongitude(-71.3456);
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
		e1.setEventTopic(5);
		e1.setEventType(1);
		
		
		e2.setEventName("event2 from DB");
		e2.setEventDesc("dummy event for test");
		e2.setLatitude(42.4667);
		e2.setLongitude(-71.3456);
		e2.setStartDateAndTime(d);
		e2.setEndDateAndTime(d);
		e2.setCreationTime(d);
		e2.setLastUpdateTime(d);
		e2.setOfficialEvent(false);
		e2.setPhoneNumber("65243535234");
		e2.setEmailId("dummy@husky.neu.edu");
		e2.setEventLocation("360 Huntington Ave., Boston, Massachusetts 02115");
		e2.setOrganiserName("CCIS");
		e2.setOrganiserDesc("a description");
		e2.setFilePath("");
		e2.setEventTopic(429);
		e2.setEventType(2);
		
		events.add(e1);
		events.add(e2);
		
		System.out.println("finish DB response with 13");
		return events;
	}
	
	public static PreferencesStore cannedUserPrefStore(){
		
		String userId = "13";
		PreferencesStore result = prefServiceImpl.buildPreferencesStore(userId);
		
		return result;
	}

}
