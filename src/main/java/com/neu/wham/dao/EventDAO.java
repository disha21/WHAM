package com.neu.wham.dao;

import java.sql.SQLException;

import java.util.List;


import org.json.JSONException;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.neu.wham.model.Event;
import com.neu.wham.model.UserSelectedPreference;

/**
 * DAO Inteface to store and retrive all the events
 * @author Vijet Badigannavar, Surbhi, Ryan, Ashwin
 */
public interface EventDAO {
	/**
	 * Add a new Event 
	 */
	public boolean addNewEvent(Event event) throws SQLException;
	/**
	 * Retrive all the event data
	 */
	public List<Event> getEventsData(String lat, String lon, String rad, UserSelectedPreference userPrefs) throws SQLException, JSONException, UnirestException; 
}

