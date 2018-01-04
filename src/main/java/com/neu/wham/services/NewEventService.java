package com.neu.wham.services;

import org.springframework.web.multipart.MultipartFile;

import com.neu.wham.messages.EventPostResponse;
import com.neu.wham.model.Event;
/**
 * Interface for handling custom manual Events
 * @author Vijet Badigannavar(badigannavar.v@husky.neu.edu)
 */
public interface NewEventService {
	/**
	 * Manipulates the event request.
	 * @param event the event information
	 * @param imageFile the file uploaded by the client(optional)
	 * @return com.new.wham.messages.EventPostResponse with status "OK" if request is accepted. "ERROR" 
	 * if request contains any errors.
	 * @see com.neu.wham.model.Event
	 */
	public EventPostResponse submitNewEvent(Event event, MultipartFile imageFile);
}
