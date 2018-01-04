package com.neu.wham.services;


import java.util.HashMap;
import java.util.List;

import org.joda.time.LocalDateTime;

import com.neu.wham.model.Event;

public interface GetEventService {
	public List<Event> getEvents(HashMap<String, String> params);
}





