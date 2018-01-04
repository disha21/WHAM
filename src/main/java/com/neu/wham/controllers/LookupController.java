package com.neu.wham.controllers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.wham.model.Location;
import com.neu.wham.services.LookupService;

@Controller
public class LookupController {
	
	/*
	 * Controller provides lookup service - when given a abbreviated location name
	 * with Northeastern campus, which usually cannot be translated into an address
	 * using Google Maps, return the address of the Northeastern building
	 * 
	 * Eg - Input - 'WVH' gives an output of '440 Huntington Ave' (location of West Village H)
	 * */
	
	@Autowired
	private LookupService lookupService;
	
	@RequestMapping(value = "/lookup/{location}", method = RequestMethod.GET)
	public @ResponseBody Location lookup(@PathVariable String location) throws JSONException{
		List<Location> locList = new ArrayList<Location>();
		String[] addressArr = location.split("[ \\,]");
		for(String addr : addressArr){
			String address = lookupService.lookup(addr);
			locList.add(new Location(location, address));
		}
		
		for(Location loc : locList){
			if(!loc.getAddress().equals("error")){
				return loc;
			}
		}
		
		return new Location("location", "error");
	}
}
