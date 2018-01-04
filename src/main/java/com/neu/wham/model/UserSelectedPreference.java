package com.neu.wham.model;

import java.util.List;

public class UserSelectedPreference {
	private List<SelectedPreference> selectedPreference;

	public UserSelectedPreference() {
		// TODO Auto-generated constructor stub
	}
	
	
	public UserSelectedPreference(List<SelectedPreference> selectedPreference) {
		super();
		this.selectedPreference = selectedPreference;
	}


	public List<SelectedPreference> getSelectedPreference() {
		return selectedPreference;
	}

	public void setSelectedPreference(List<SelectedPreference> selectedPreference) {
		this.selectedPreference = selectedPreference;
	}
	
	
}
