package it.dk74.trelloexporter;

import java.util.Date;

public class Card {

	private String mTitle = "";
	private String mdueDate = "";
	private String mDescription = "";
		
	public String getTitle() {
		return mTitle;
	}
	
	public void setTitle(String title) {
		if (title != null) {
			this.mTitle = title;
		}
	}
	
	public String getDueDate() {
		return mdueDate;
	}
	
	public void setDueDate(String dueDate) {
		if(dueDate != null) {
			this.mdueDate = dueDate;
		}
	}
	
	public String getDescription() {
		return mDescription;
	}
	
	public void setDescription(String description) {
		if (description != null) {
			this.mDescription = description;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Card) {
			Card test = (Card) obj;
			result = (mTitle.equals(test.getTitle()) &&
					mDescription.equals(test.getDescription()) &&
						mdueDate.equals(test.getDueDate()));
		}
		return result;
		
	}
	
	@Override
	public String toString() {
		return "{title: \"" + mTitle + "\"; " +  
				"description: \"" + mDescription + "\"; " + 
				"dueDate: \"" + mdueDate + "\"}";
	}
	
	
}
