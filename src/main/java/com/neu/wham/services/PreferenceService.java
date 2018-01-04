package com.neu.wham.services;

import com.neu.wham.model.PreferencesStore;
import com.neu.wham.model.UserPreference;
import com.neu.wham.model.UserSelectedPreference;

/**
 * Interface that handles the PreferenceEngine
 * @author Vijet Badigannavar
 *
 */
public interface PreferenceService {
	/**
	 * Returns all the Preferences
	 * @return
	 */
	public UserPreference getAllPreferences();
	/**
	 * Update the preference of a particular user specified by UserId
	 */
	public UserSelectedPreference updatePreference(String userId, UserSelectedPreference userPref);
	/**
	 * Retrieves the Preferences for a specific User
	 */
	public UserSelectedPreference getUserPreferences(String userId);
	/**
	 * Builds the PreferencesStore for a particular User
	 */
	public PreferencesStore buildPreferencesStore(String userId);
}
