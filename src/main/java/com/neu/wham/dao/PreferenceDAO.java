package com.neu.wham.dao;

import com.neu.wham.model.UserSelectedPreference;
import com.neu.wham.model.UserPreference;

/**
 * Interface for storing and retriving Preferences
 * @author Vijet,Vaibhav,Harsh,Mavez,Yue, Ryan, Surbhi, Ashwin
 */
public interface PreferenceDAO {
	/**
	 * Retrieve all the Preferences
	 */
	public UserPreference getAllPreferences() throws Exception;
	/**
	 * Updates the Preferences of a particular User
	 */
	public UserSelectedPreference updatePreference(int userId, UserSelectedPreference userPref) throws Exception;
	/**
	 * Retrives the preferences of a particular User
	 */
	public UserSelectedPreference getUserPreferences(int userId) throws Exception;
	/**
	 * Retrives the Preference as a string
	 */
	public String getPreferenceString(int id, int tableId) throws Exception;
}
