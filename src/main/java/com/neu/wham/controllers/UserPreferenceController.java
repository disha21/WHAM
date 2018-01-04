package com.neu.wham.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.neu.wham.model.UpdatePreference;
import com.neu.wham.model.UserPreference;
import com.neu.wham.model.UserSelectedPreference;
import com.neu.wham.services.PreferenceService;

/**
 * Controller to intercept UserPreferences
 * @author Vijet Badigannavar
 * @Date 07-04-2016
 */
@Controller
public class UserPreferenceController {
	/**
	 * Preference Service
	 */
	@Autowired
	private PreferenceService prefService;

	/**
	 * Updates the UserPreferences
	 * @param userPref
	 * @return UserSelectedPreference containing all the userPreferences if successful. null otherwise.
	 */
	@RequestMapping(value="/updatePref",method=RequestMethod.POST)
	public @ResponseBody UserSelectedPreference updateUserPreference(@RequestBody String userPref){
		Gson gson = new Gson();
		UpdatePreference pref = gson.fromJson(userPref, UpdatePreference.class);
		return prefService.updatePreference(String.valueOf(pref.getUserId()), new UserSelectedPreference(pref.getUserPreference()));
	}

	/**
	 * Retrieves the preferences for a specific user
	 * @param userId id of the user whose preferences will be fetched.
	 * @return UserSelectedPreference of the user specified using UserId
	 */
	@RequestMapping(value="/getPref",method=RequestMethod.GET)
	public @ResponseBody UserSelectedPreference getUserPreference(@RequestParam("userPreference") String userId){
		if(userId==null || userId.isEmpty()){
			return null;
		}
		return prefService.getUserPreferences(userId);
	}

	/**
	 * Retrieves the Preference list of all the users
	 * @return UserPreference 
	 */
	@RequestMapping(value="/getPrefList",method=RequestMethod.GET)
	public @ResponseBody UserPreference getPreferencesList(){
		return prefService.getAllPreferences();
	}
}
