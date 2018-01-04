package com.neu.wham.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.neu.wham.messages.EventPostResponse;
import com.neu.wham.messages.EventPostResponse.Status;
import com.neu.wham.model.Event;
import com.neu.wham.services.NewEventService;


/**
 * Controller for the users to manually upload the event. It receives a POST request from the
 * client which contains the information as specified in create_event.html. It forwards the requests
 * to the NewEventService.
 * @author Vijet Badigannavar(badigannavar.v@husky.neu.edu)
 * @see com.neu.wham.services.NewEventService
 * @see com.neu.wham.services.EventServiceImpl
 * Date : 03-21-2015
 */
@Controller
public class EventController {
	@Autowired
	private NewEventService newEventService;
	
	/**
	 * Request method that is used to handle manual event upload
	 * @param event the manual event uploaded by the client
	 * @param result the results that are associated with the automatic mapping of request to POJO
	 * @param file file uploaded by the client (optional)
	 * @return com.new.wham.messages.EventPostResponse with status "OK" if request is accepted. "ERROR" 
	 * if request contains any errors.
	 * @see com.new.wham.messages.EventPostResponse 
	 */
	@RequestMapping(value = "/newevent", method = RequestMethod.POST)
	public @ResponseBody EventPostResponse firstRequest(@Valid @ModelAttribute Event event, BindingResult result, @RequestParam(value="myFile",required=false) MultipartFile file,@RequestParam("isOfficialEvent") boolean officialEvent){
		if(result.hasErrors()){
			EventPostResponse errRes = new EventPostResponse();
			errRes.setMsg(result.getAllErrors().toString());
			errRes.setStatus(Status.ERROR);
			return errRes;
		}
		event.setOfficialEvent(officialEvent);
		return newEventService.submitNewEvent(event,file);
	}
}
