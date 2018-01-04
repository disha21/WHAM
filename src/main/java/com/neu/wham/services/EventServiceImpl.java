package com.neu.wham.services;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.neu.wham.dao.EventDAO;
import com.neu.wham.exceptions.InvalidAddressException;
import com.neu.wham.keys.Constants;
import com.neu.wham.keys.Keys;
import com.neu.wham.messages.EventPostResponse;
import com.neu.wham.messages.EventPostResponse.Status;
import com.neu.wham.model.Event;
import com.neu.wham.services.NewEventService;

/**
 * NewEventService implementation that decodes the location and updates the lat-long
 * of the event and delegates to EVENTDAO to store the event. It also prepares the 
 * image URL that will be stored in the server.
 * @author Vijet Badigannavar
 */
@Service
public class EventServiceImpl implements NewEventService {
	@Autowired
	private EventDAO eventDAO;
	@Autowired
	private EventPostResponse evntResponse;
	/*
	 * To update the creation time/ updation time
	 */
	private final Date tempDate = new Date();
	
	@Override
	public EventPostResponse submitNewEvent(Event event, MultipartFile imageFile) {
		//fetch the lat-lng
		try {
			getLatLongForEventLocation(event);
			//save the file if uploaded if uploaded in the request.
			event.setFilePath(saveUploadedFile(imageFile, event.getOrganiserName()));
			event.setCreationTime(tempDate);
			event.setLastUpdateTime(tempDate);
			eventDAO.addNewEvent(event);
			evntResponse.setMsg(Status.OK.name());
			evntResponse.setStatus(Status.OK);
		}catch (IOException e) {
			evntResponse.setMsg(e.getMessage());
			evntResponse.setStatus(Status.ERROR);
		}catch (InvalidAddressException e) {
			evntResponse.setMsg(e.getMessage());
			evntResponse.setStatus(Status.ERROR);
		} catch (SQLException e) {
			evntResponse.setMsg(e.getMessage());
			evntResponse.setStatus(Status.ERROR);
		}
		return evntResponse;
	}

	/**
	 * Updates the URL of the image that will be stored in the DB.
	 * @author Vijet Badigannavar
	 */
	private String saveUploadedFile(MultipartFile imageFile, String organiserName) throws IOException {
		if(imageFile != null && !imageFile.getName().isEmpty()){
			
			StringBuilder imageName = new StringBuilder();
			imageName.append(organiserName);
			imageName.append("_");
			imageName.append(tempDate.getTime());
			imageName.append("_");
			imageName.append(imageFile.getOriginalFilename());
			
			StringBuilder builder = new StringBuilder();
			builder.append(Constants.IMAGE_UPLOAD_PATH);
			builder.append(imageName.toString());
			
			StringBuilder imageURL = new StringBuilder();
			imageURL.append(Constants.AMAZON_IMAGE_URL);
			imageURL.append(imageName.toString());
			
			File tempFile = new File(builder.toString());
			FileUtils.writeByteArrayToFile(tempFile, imageFile.getBytes());
			return imageURL.toString();
		}
		return null;
	}

	/**
	 * Updates the location of the event to gather its lat-long and updates the event.
	 * @author Vijet Badigannavar
	 * @throws InvalidAddressException
	 */
	private void getLatLongForEventLocation(Event event) throws InvalidAddressException{
		if(event == null || event.getEventLocation() == null){
			throw new InvalidAddressException("Invalid Address.");
		}
		// Replace the API key below with a valid API key.
		GeoApiContext context = new GeoApiContext().setApiKey(Keys.GEO_CODING_API_KEY);
		GeocodingResult[] results = null;
		try {
			results = GeocodingApi.geocode(context,event.getEventLocation()).await();
		} catch (Exception e) {
			throw new InvalidAddressException("Invalid Address.");
		}
		event.setLatitude(results[0].geometry.location.lat);
		event.setLongitude(results[0].geometry.location.lng);
	}
	

}
