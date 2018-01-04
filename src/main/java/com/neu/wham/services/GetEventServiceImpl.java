package com.neu.wham.services;

import com.neu.wham.keys.*;
import java.util.List;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;


import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.neu.wham.dao.EventDAO;
import com.neu.wham.model.Event;
import com.neu.wham.prefs.util.*;
import com.neu.wham.model.PreferencesStore;
import com.neu.wham.model.UserSelectedPreference;

@Service
public class GetEventServiceImpl implements GetEventService {
	
	@Autowired
	private EventDAO eventDAO;
	
	@Autowired
	private PreferenceService prefService;
	
	
	
	@Override
	public List<Event> getEvents(HashMap<String, String> params)
	{
		// set up event lists
		List<Event> DBEvents = new ArrayList<Event>();
		List<Event> APIEvents = new ArrayList<Event>();
		List<Event> NEUEvents = new ArrayList<Event>();
		List<Event> resultList = new ArrayList<Event>();
		UserSelectedPreference userPref = null;
		
		// read the params
		String lat = params.get("lat");
		String lon = params.get("lon");
		String rad = params.get("rad");
		String userId = params.get("userId");
		
		System.out.println("userId in getEvent service: " + userId);
		
		// build the Eventbrite preferences
		PreferencesStore prefStore = new PreferencesStore();
		if(null != userId) 
		{
			prefStore = prefService.buildPreferencesStore(userId);	
			userPref = prefService.getUserPreferences(userId);
		}
		
 		try
		{
 			System.out.println("prefStore is null?: " + prefStore);
 			APIEvents = getEventsFromAPI(lat, lon, rad, prefStore.getFormatsAsEventbrite(), 
 				prefStore.getCategoriesAsEventbrite(), prefStore.getSubcategoriesAsEventbrite());
 			DBEvents =  getEventsFromDB(lat, lon, rad, userPref);
			NEUEvents = getNEUEvents(prefStore.getFormats(), prefStore.getCategories(), prefStore.getSubcategories());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
 		resultList.addAll(APIEvents);
		resultList.addAll(DBEvents);
		resultList.addAll(NEUEvents);
 		
		return resultList;
	}
	
	public List<Event> getEventsFromAPI(String lat, String lon, String radius, 
			String[] formats, String[] categories, String[] subcategories) 
			throws UnirestException, JSONException, ParseException, URISyntaxException
	{
		JSONArray events = queryEventbrite(lat, lon, radius, formats, categories, subcategories);
		System.out.println("Events length: " + events.length());
		
		List<Event> eventList = new ArrayList<Event>();
		
		for(int i = 0; i < events.length(); i++){
			
			Event e = new Event();
			
			JSONObject event = events.getJSONObject(i);
			String eventName = event.getJSONObject("name").getString("text");
			e.setEventName(eventName);
			String eventDesc = event.getJSONObject("description").getString("text");
			e.setEventDesc(eventDesc);
			String eventLocation = event.getJSONObject("venue").getJSONObject("address").getString("address_1")+ " " + 
					event.getJSONObject("venue").getJSONObject("address").getString("address_2")+ " " +
					event.getJSONObject("venue").getJSONObject("address").getString("city")+ " " +
					event.getJSONObject("venue").getJSONObject("address").getString("region")+ " " +
					event.getJSONObject("venue").getJSONObject("address").getString("postal_code")+ " " +
					event.getJSONObject("venue").getJSONObject("address").getString("country");
			e.setEventLocation(eventLocation);
			e.setPhoneNumber(null);
			e.setEmailId(null);
			
			String startDateTime = event.getJSONObject("start").getString("local");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			TimeZone tz = TimeZone.getTimeZone(event.getJSONObject("start").getString("timezone"));
			formatter.setTimeZone(tz);
			Date startDate = formatter.parse(startDateTime);
			e.setStartDateAndTime(startDate);
			
			String endDateString = event.getJSONObject("end").getString("local");
			Date endDate = formatter.parse(endDateString);
			e.setEndDateAndTime(endDate);
			
			Double venueLat = event.getJSONObject("venue").getJSONObject("address").getDouble("latitude");
			e.setLatitude(venueLat);
			Double venueLong = event.getJSONObject("venue").getJSONObject("address").getDouble("longitude");
			e.setLongitude(venueLong);
			
			
			String creationTimeString = event.getString("created");
			Date creationTime = formatter.parse(creationTimeString);
			e.setCreationTime(creationTime);
			
			String lastUpdateTimeString = event.getString("changed");
			Date lastUpdateTime = formatter.parse(lastUpdateTimeString);
			e.setLastUpdateTime(lastUpdateTime);
			
			e.setOrganiserName(null);
			e.setOrganiserDesc(null);
			e.setOfficialEvent(false);
			e.setFilePath(null);
			
			
			eventList.add(e);
		}
		
		return eventList;
	}

	public List<Event> getEventsFromDB(String lat, String lon, String radius, UserSelectedPreference userPrefs) throws SQLException, JSONException, UnirestException{
		return eventDAO.getEventsData(lat, lon, radius, userPrefs);
	}
	
	public List<Event> getNEUEvents(String[] types, String[] categories, String[] sub_categories) throws URISyntaxException, UnirestException, IOException, JSONException, ParserConfigurationException, SAXException, TransformerException
	{
		List<Event> NEUCalenderEvents = new ArrayList<Event>();
		URL url = null;
		String url_string = null;
		if((types.length == 0 || types == null) && (categories.length == 0 || categories == null) &&
				(sub_categories.length == 0 || sub_categories == null))
		{
		   System.out.println("Events without Preferences");
		   url = new URL("http://calendar.northeastern.edu/widget/view?schools=northeastern&days=31&num=50&format=xml");
		}
		else
		{
			System.out.println("Events with Preferences");
		    url_string = "http://calendar.northeastern.edu/widget/view?schools=northeastern&types=";
			if(types != null)
			{
				for(int i =0; i < types.length; i++)
				{
					String id = NEUEventsPrefMapping.getNEUPref(types[i]);
					url_string += (id != null ? id + "%2C" : "");
				}
			}
			
			if(categories != null)
			{
				for(int i =0; i < categories.length; i++)
				{
					String id = NEUEventsPrefMapping.getNEUPref(categories[i]);
					url_string += (id != null ? id + "%2C" : "");
				}
			}
			
			if(sub_categories != null)
			{
				for(int i =0; i < sub_categories.length; i++)
				{
					String id = NEUEventsPrefMapping.getNEUPref(sub_categories[i]);
					url_string += (id != null ? id + "%2C" : "");
				}
			}
			url_string = url_string.substring(0, url_string.lastIndexOf("%2C"));
			url_string += "&days=31&num=50&format=xml";
			System.out.println("URL String:" + url_string);
		    url = new URL(url_string);	
		}
		
		
		URLConnection conn = url.openConnection();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(conn.getInputStream());
	    
		NodeList itemList = doc.getElementsByTagName("item");
		
		for (int i=0; i < itemList.getLength(); i++)
		{
			Node nNode = itemList.item(i);
			Element eElement = (Element) nNode;	
			if(eElement.getElementsByTagName("geo:lat").getLength() != 0 &&
					eElement.getElementsByTagName("geo:lng").getLength() != 0){

				try
				{
					Event e = new Event();
					if(eElement.getElementsByTagName("title").getLength() != 0)
						e.setEventName(eElement.getElementsByTagName("title").item(0).getTextContent());
					if(eElement.getElementsByTagName("description").getLength() != 0)
						e.setEventDesc(eElement.getElementsByTagName("description").item(0).getTextContent());
					if(eElement.getElementsByTagName("geo:lat").getLength() != 0)
						e.setLatitude((double)Double.parseDouble(eElement.getElementsByTagName("geo:lat").item(0).getTextContent()));
					if(eElement.getElementsByTagName("geo:lng").getLength() != 0)
						e.setLongitude((double)Double.parseDouble(eElement.getElementsByTagName("geo:lng").item(0).getTextContent()));
					if(eElement.getElementsByTagName("link").getLength() != 0)
						e.setExtLink(eElement.getElementsByTagName("link").item(0).getTextContent());
					if(eElement.getElementsByTagName("pubDate").getLength() != 0){
						DateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
						format.setTimeZone(TimeZone.getTimeZone("GMT-4:00"));
						Date date = format.parse(eElement.getElementsByTagName("pubDate").item(0).getTextContent());
						e.setStartDateAndTime(date);
					}
					e.setOfficialEvent(true);

					NEUCalenderEvents.add(e);
				}
				catch(Exception e)
				{
					System.out.println("Error:" + e.getStackTrace());
				}
			}
		}
		return NEUCalenderEvents;	
	}	

	
	public JSONArray queryEventbrite(String lat, String lon, String radius, 
			String[] formats, String[] categories, String[] subcategories) {
		
		URIBuilder builder;
		JSONArray events = null;
		try {
			builder = new URIBuilder("https://www.eventbriteapi.com/v3/events/search");
			builder.addParameter("expand", "venue");
			builder.addParameter("location.latitude", lat);
			builder.addParameter("location.longitude", lon);
			builder.addParameter("location.within", radius + "mi");
			builder.addParameter("token", Keys.EVENTBRITE_KEY);
			if(null != formats && formats.length > 0)
				builder.addParameter("formats", String.join(",", formats));
			if(null != categories && categories.length > 0)
				builder.addParameter("categories", String.join(",", categories));
			if(null != subcategories && subcategories.length > 0)
				builder.addParameter("subcategories", String.join(",", subcategories));
			
			HttpResponse<JsonNode> jsonResponse = Unirest.get(builder.toString()).asJson();
			JsonNode obj = jsonResponse.getBody();
			JSONObject response = obj.getObject();
			System.out.println("response length:" + response.length());
			System.out.println(response.toString());
			if(response.has("events"))
				events = response.getJSONArray("events");
			else
				events = new JSONArray();
				
			System.out.println(jsonResponse.getStatus());
			System.out.println("*****");
			System.out.println("Events: ");
			System.out.println(events);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return events;
	}
}
