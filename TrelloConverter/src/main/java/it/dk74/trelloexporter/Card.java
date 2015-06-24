package it.dk74.trelloexporter;

import java.util.Date;

public class Card {

	private String mTitle;
	private Date mdueDate;
	private String mDescription;
		
	public String getTitle() {
		return mTitle;
	}
	public void setTitle(String mTitle) {
		this.mTitle = mTitle;
	}
	public Date getDueDate() {
		return mdueDate;
	}
	public void setDueDate(Date mdueDate) {
		this.mdueDate = mdueDate;
	}
	public String getDescription() {
		return mDescription;
	}
	public void setDescription(String mDescription) {
		this.mDescription = mDescription;
	}
}
