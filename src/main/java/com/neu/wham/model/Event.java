package com.neu.wham.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * Model class for the events. All the events from all the datasources will 
 * be mapped to this event model
 * @author Vijet Badigannavar, Surbhi Gupta, Aswin 
 *
 */
public class Event {
	@NotEmpty(message="Event name is empty") 
	private String eventName;
	@NotEmpty(message="Event desc is empty") 
	private String eventDesc;
	@NotNull(message="Event location cannot be null") 
	private String eventLocation;
	private String phoneNumber;
	@NotNull(message="Organiser Mail is null") 
	private String emailId;
	@NotNull(message="Please check event start date or time")
	@DateTimeFormat(pattern="MM/dd/YYYY hh:mm a")
	private Date startDateAndTime;
	@NotNull(message="Please check event start date or time")
	@DateTimeFormat(pattern="MM/dd/YYYY hh:mm a")
	private Date endDateAndTime;
	private double latitude;
	private double longitude;
	private String filePath;
	@NotNull(message="Event organiser name is null") 
	private String organiserName;
	@NotNull(message="Event organiser desc is null") 
	private String organiserDesc;
	private boolean isOfficialEvent;
	private Date creationTime;
	private Date lastUpdateTime;
	//@NotNull(message="EventType cannot be null")
	private int eventType;
	//@NotNull(message="EventTopic cannot be null")
	private int eventTopic;
	private int eventSubtopic;
	private String extLink;
	
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventDesc() {
		return eventDesc;
	}
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
	public String getEventLocation() {
		return eventLocation;
	}
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getOrganiserName() {
		return organiserName;
	}
	public void setOrganiserName(String organiserName) {
		this.organiserName = organiserName;
	}
	public String getOrganiserDesc() {
		return organiserDesc;
	}
	public void setOrganiserDesc(String organiserDesc) {
		this.organiserDesc = organiserDesc;
	}
	public boolean isOfficialEvent() {
		return isOfficialEvent;
	}
	public void setOfficialEvent(boolean isOfficialEvent) {
		this.isOfficialEvent = isOfficialEvent;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	
	public Date getStartDateAndTime() {
		return startDateAndTime;
	}
	public void setStartDateAndTime(Date startDateAndTime) {
		this.startDateAndTime = startDateAndTime;
	}
	public Date getEndDateAndTime() {
		return endDateAndTime;
	}
	public void setEndDateAndTime(Date endDateAndTime) {
		this.endDateAndTime = endDateAndTime;
	}
	
	public int getEventType() {
		return eventType;
	}
	public void setEventType(int eventType) {
		this.eventType = eventType;
	}
	public int getEventTopic() {
		return eventTopic;
	}
	public void setEventTopic(int eventTopic) {
		this.eventTopic = eventTopic;
	}
	public int getEventSubtopic() {
		return eventSubtopic;
	}
	public void setEventSubtopic(int eventSubtopic) {
		this.eventSubtopic = eventSubtopic;
	}
	public void setExtLink(String link)
	{
		this.extLink = link;
	}
	public String getExtLink()
	{
		return extLink;
	}
	@Override
	public String toString() {
		return "Event [eventName=" + eventName + ", eventDesc=" + eventDesc + ", eventLocation=" + eventLocation
				+ ", phoneNumber=" + phoneNumber + ", emailId=" + emailId + ", startDateAndTime=" + startDateAndTime
				+ ", endDateAndTime=" + endDateAndTime + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", filePath=" + filePath + ", organiserName=" + organiserName + ", organiserDesc=" + organiserDesc
				+ ", isOfficialEvent=" + isOfficialEvent + ", creationTime=" + creationTime + ", lastUpdateTime="
				+ lastUpdateTime + ", eventType=" + eventType + ", eventTopic=" + eventTopic + ", eventSubtopic="
				+ eventSubtopic + "]";
	}
	
	/* 
	 * To check if two events are the same
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((endDateAndTime == null) ? 0 : endDateAndTime.hashCode());
		result = prime * result + ((eventDesc == null) ? 0 : eventDesc.hashCode());
		result = prime * result + ((eventLocation == null) ? 0 : eventLocation.hashCode());
		result = prime * result + ((eventName == null) ? 0 : eventName.hashCode());
		result = prime * result + eventSubtopic;
		result = prime * result + eventTopic;
		result = prime * result + eventType;
		result = prime * result + ((extLink == null) ? 0 : extLink.hashCode());
		result = prime * result + ((filePath == null) ? 0 : filePath.hashCode());
		result = prime * result + (isOfficialEvent ? 1231 : 1237);
		result = prime * result + ((lastUpdateTime == null) ? 0 : lastUpdateTime.hashCode());
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((organiserDesc == null) ? 0 : organiserDesc.hashCode());
		result = prime * result + ((organiserName == null) ? 0 : organiserName.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((startDateAndTime == null) ? 0 : startDateAndTime.hashCode());
		return result;
	}
	/* 
	 * To check if two events are the same
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (creationTime == null) {
			if (other.creationTime != null)
				return false;
		} else if (!creationTime.equals(other.creationTime))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (endDateAndTime == null) {
			if (other.endDateAndTime != null)
				return false;
		} else if (!endDateAndTime.equals(other.endDateAndTime))
			return false;
		if (eventDesc == null) {
			if (other.eventDesc != null)
				return false;
		} else if (!eventDesc.equals(other.eventDesc))
			return false;
		if (eventLocation == null) {
			if (other.eventLocation != null)
				return false;
		} else if (!eventLocation.equals(other.eventLocation))
			return false;
		if (eventName == null) {
			if (other.eventName != null)
				return false;
		} else if (!eventName.equals(other.eventName))
			return false;
		if (eventSubtopic != other.eventSubtopic)
			return false;
		if (eventTopic != other.eventTopic)
			return false;
		if (eventType != other.eventType)
			return false;
		if (extLink == null) {
			if (other.extLink != null)
				return false;
		} else if (!extLink.equals(other.extLink))
			return false;
		if (filePath == null) {
			if (other.filePath != null)
				return false;
		} else if (!filePath.equals(other.filePath))
			return false;
		if (isOfficialEvent != other.isOfficialEvent)
			return false;
		if (lastUpdateTime == null) {
			if (other.lastUpdateTime != null)
				return false;
		} else if (!lastUpdateTime.equals(other.lastUpdateTime))
			return false;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		if (organiserDesc == null) {
			if (other.organiserDesc != null)
				return false;
		} else if (!organiserDesc.equals(other.organiserDesc))
			return false;
		if (organiserName == null) {
			if (other.organiserName != null)
				return false;
		} else if (!organiserName.equals(other.organiserName))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (startDateAndTime == null) {
			if (other.startDateAndTime != null)
				return false;
		} else if (!startDateAndTime.equals(other.startDateAndTime))
			return false;
		return true;
	}
}
