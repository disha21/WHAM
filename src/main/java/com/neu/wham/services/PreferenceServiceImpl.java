package com.neu.wham.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.neu.wham.dao.PreferenceDAO;
import com.neu.wham.model.PreferencesStore;
import com.neu.wham.model.SelectedPreference;
import com.neu.wham.model.UserSelectedPreference;
import com.neu.wham.model.UserPreference;

/**
 * PreferenceService Implementation that handles the PreferenceEngine
 * @author Vijet Badigannavar, Ryan Millay, Surbhi Gupta, Ashwin 
 */
@Service
public class PreferenceServiceImpl implements PreferenceService {
	/**
	 * DAO Class for storing the preferences.
	 */
	@Autowired
	private PreferenceDAO preferenceDAO;
	
	@Override
	public UserPreference getAllPreferences() {
		try {
			return preferenceDAO.getAllPreferences();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserSelectedPreference updatePreference(String userId, UserSelectedPreference userPref) {
		try{
			//UserSelectedPreference userPref = new Gson().fromJson(userPrefAsString, UserSelectedPreference.class);
			return preferenceDAO.updatePreference(Integer.valueOf(userId), userPref);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserSelectedPreference getUserPreferences(String userId) {
		try{
			return preferenceDAO.getUserPreferences(Integer.valueOf(userId));
		}catch(Exception e){
			//e.printStackTrace();
		}
		return null;
	}

	@Override
	public PreferencesStore buildPreferencesStore(String userId) {
		PreferencesStore prefStore = new PreferencesStore();
		
		// let's get the intermediate form of preferences from the local database
		UserSelectedPreference userPrefs = this.getUserPreferences(userId);
		
		// for each preference mapping, do a db lookup.  I'm going to pretend this is efficient :)
		String prefString = "";
		for(SelectedPreference pref : userPrefs.getSelectedPreference()) {
			try {
				prefString = preferenceDAO.getPreferenceString(pref.getEventId(), pref.getEventCategory());
				switch(pref.getEventCategory()) {
					case 0:
						prefStore.addFormat(prefString);
						break;
					case 1:
						prefStore.addCategory(prefString);
						break;
					case 2:
						prefStore.addSubcategory(prefString);
						break;
					default:
						System.out.println("Unknown preference type! " + pref.getEventCategory());
				}
				
			} catch(Exception e) {
				System.out.println("in exception");
				e.printStackTrace();
			}	
		}
		
		System.out.println("formats: " + prefStore.getFormats().toString());
		System.out.println("categories: " + prefStore.getCategories().toString());
		System.out.println("sub categories: " + prefStore.getSubcategories().toString());
		System.out.println("e formats: " + prefStore.getFormatsAsEventbrite().toString());
		System.out.println("e categories: " + prefStore.getCategoriesAsEventbrite().toString());
		System.out.println("e sub categories: " + prefStore.getSubcategoriesAsEventbrite().toString());
		
		
		// return the EventbritePreferences object
		return prefStore;
	}
}
