package it.dk74.trelloexporter;

public class Card {

	private String mTitle = "";
	private String mdueDate = "";
	private String mDescription = "";
	private String mIdList = "";

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
		if (dueDate != null) {
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
			result = (mTitle.equals(test.getTitle())
					&& mDescription.equals(test.getDescription())
					&& mdueDate.equals(test.getDueDate()) && mIdList
					.equals(test.getIdList()));
		}
		return result;

	}

	public String getIdList() {
		return mIdList;
	}

	@Override
	public String toString() {
		return "{title: \"" + mTitle + "\"; " 
				+ "description: \""	+ mDescription + "\"; " 
				+ "dueDate: \"" + mdueDate + "\"; "
				+ "idList: \"" + mIdList + "\"}";
	}

	public void setIdList(String listId) {
		this.mIdList = listId;
	}

}
