package com.neu.wham.prefs.util;

import java.util.HashMap;
import java.util.Map;


public final class NEUEventsPrefMapping {
	private static final Map<String, String> NEUEvents = new HashMap<String,String>();
	
	static{
		NEUEvents.put("Academic Events", "12896%2C14363%2C12877%2C12881%2C12891%2C12905%2C12897%2C3000%2C12898%2C12894");
		NEUEvents.put("Lecture", "12881");
		NEUEvents.put("Meeting", "12891");
		NEUEvents.put("Open Mic", "12905");
		NEUEvents.put("PhD defense", "3000");
		NEUEvents.put("Rally", "12898");
		NEUEvents.put("Class", "12877");
		NEUEvents.put("Lecture", "12881");
		NEUEvents.put("Family and Education", "14360");
		NEUEvents.put("Alumni", "14360");
		NEUEvents.put("Sports and Fitness", "12876");
		NEUEvents.put("Hobbies And Special Interest", "12890%2C12902");
		NEUEvents.put("Business And Professional", "12901%2C16080%2C12880");
		NEUEvents.put("Community And Culture", "12892%2C12893");
		NEUEvents.put("Charity And Causes", "12900");
		NEUEvents.put("Film, Media And Entertainment", "12878%2C12885");
		NEUEvents.put("Concert or Performance", "12879");
		NEUEvents.put("Conference", "12884");
		NEUEvents.put("Music", "12895%2C12888");
		NEUEvents.put("Performing And Visual Arts", "12903%2C12886%2C12887%2C46561%2C12889%2C12904");
		NEUEvents.put("Food And Drink", "12899");
		NEUEvents.put("Seminar or Talk", "12882");
		NEUEvents.put("Conference", "12884");
		NEUEvents.put("Class, Training, or Workshop", "12883");
        NEUEvents.put("Cultural", "12895");
        NEUEvents.put("Dance", "12903");
        NEUEvents.put("Film", "12885");
        NEUEvents.put("Other", "12906");
	}
	
	public static final String getNEUPref(final String mappedType){
		return NEUEvents.get(mappedType);
	}
}
