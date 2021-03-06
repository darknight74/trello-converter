package it.dk74.trelloexporter.model;

public class TrelloCard {

	private String mTitle = "";
	private String mDueDate = "";
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
		return mDueDate;
	}

	public void setDueDate(String dueDate) {
		if (dueDate != null) {
			this.mDueDate = dueDate;
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
		if (obj instanceof TrelloCard) {
			TrelloCard test = (TrelloCard) obj;
			result = mTitle.equals(test.mTitle)
					&& mDescription.equals(test.mDescription)
					&& mDueDate.equals(test.mDueDate) 
					&& mIdList.equals(test.mIdList);
		}
		return result;

	}

	public String getIdList() {
		return mIdList;
	}

		public void setIdList(String listId) {
		this.mIdList = listId;
	}

	@Override
	public String toString() {
		return "{title: \"" + mTitle + "\"; " 
				+ "description: \""	+ mDescription + "\"; " 
				+ "dueDate: \"" + mDueDate + "\"; "
				+ "idList: \"" + mIdList + "\"}";
	}
}
