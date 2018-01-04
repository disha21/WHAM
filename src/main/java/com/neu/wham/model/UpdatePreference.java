package com.neu.wham.model;

import java.util.List;

public class UpdatePreference {
	private String userId;
	private List<SelectedPreference> userPreference;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public List<SelectedPreference> getUserPreference() {
		return userPreference;
	}
	public void setUserPreference(List<SelectedPreference> userPreference) {
		this.userPreference = userPreference;
	}
	
	@Override
	public String toString() {
		return "UpdatePreference [userId=" + userId + ", userPreference=" + userPreference + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userPreference == null) ? 0 : userPreference.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpdatePreference other = (UpdatePreference) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userPreference == null) {
			if (other.userPreference != null)
				return false;
		} else if (!userPreference.equals(other.userPreference))
			return false;
		return true;
	}
	
	
	
	
}
