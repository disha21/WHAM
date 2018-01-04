package com.neu.wham.model;

import java.util.ArrayList;

import com.neu.wham.prefs.util.EventbritePrefMapping;

public class PreferencesStore {
	private ArrayList<String> formats, categories, subcategories, eFormats, eCategories, eSubcategories;
	
	public PreferencesStore() {
		formats = new ArrayList<String>();
		categories = new ArrayList<String>();
		subcategories = new ArrayList<String>();
		eFormats = new ArrayList<String>();
		eCategories = new ArrayList<String>();
		eSubcategories = new ArrayList<String>();
	}
	
	public String[] getFormats() {
		String[] tmp = new String[formats.size()];
		tmp = formats.toArray(tmp);
		return tmp;
	}
	
	public String[] getCategories() {
		String[] tmp = new String[categories.size()];
		tmp = categories.toArray(tmp);
		return tmp;
	}
	
	public String[] getSubcategories() {
		String[] tmp = new String[subcategories.size()];
		tmp = subcategories.toArray(tmp);
		return tmp;
	}
	
	public String[] getFormatsAsEventbrite() {
		String[] tmp = new String[eFormats.size()];
		tmp = eFormats.toArray(tmp);
		return tmp;
	}
	
	public String[] getCategoriesAsEventbrite() {
		String[] tmp = new String[eCategories.size()];
		tmp = eCategories.toArray(tmp);
		return tmp;
	}
	
	public String[] getSubcategoriesAsEventbrite() {
		String[] tmp = new String[eSubcategories.size()];
		tmp = eSubcategories.toArray(tmp);
		return tmp;
	}
	
	public void addFormat(String newFormat) { 
		formats.add(newFormat);
		if(null != EventbritePrefMapping.getEventbritePref(newFormat))
			eFormats.add(EventbritePrefMapping.getEventbritePref(newFormat));
		else
			System.out.println("PreferenceStore: " + newFormat + " not found in lookup table.");
	}
	
	public void addCategory(String newCategory) {
		categories.add(newCategory);
		if(null != EventbritePrefMapping.getEventbritePref(newCategory))
			eCategories.add(EventbritePrefMapping.getEventbritePref(newCategory));
		else
			System.out.println("PreferenceStore: " + newCategory + " not found in lookup table.");
	}
	
	public void addSubcategory(String newSubcategory) {
		subcategories.add(newSubcategory);
		if(null != EventbritePrefMapping.getEventbritePref(newSubcategory))
			eSubcategories.add(EventbritePrefMapping.getEventbritePref(newSubcategory));
		else
			System.out.println("PreferenceStore: " + newSubcategory + " not found in lookup table.");
	}
}
