package it.dk74.trelloexporter.model;

public class TrelloCard {

	private String mTitle = "";
	private String mDueDate = "";
	private String mDescription = "";
	private String mIdList = "";
	private String mId;
	
	public TrelloCard() {
	}
	
	public TrelloCard(String id, String title, String description, String idList) {
		this.mId = id;
		this.mTitle = title;
		this.mDescription = description;
		this.mIdList = idList;
	}
	
	public String getId() {
		return mId;
	}

	public void setId(String id) {
		this.mId = id;
	}
	
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
			result = mId.equals(test.mId) 
					&& mTitle.equals(test.mTitle)
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
		return "{id: \"" + mId + "\"; "
				+ "title: \"" + mTitle + "\"; " 
				+ "description: \""	+ mDescription + "\"; " 
				+ "dueDate: \"" + mDueDate + "\"; "
				+ "idList: \"" + mIdList + "\"}";
	}
}
