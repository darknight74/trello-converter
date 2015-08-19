package it.dk74.trelloexporter.model;

public class TrelloAction {
	
	private String mDate;
	private String mBelongingCardId = "";
	private String mText = "";
	private String mEditorFullName;
	private String mEditorUserName;
	private String mActionType;
	
	public TrelloAction(){
		
	}
	
	public TrelloAction(String actionType, String editorFullName, String editorUserName, String date) {
		this.mActionType = actionType;
		this.mEditorFullName = editorFullName;
		this.mEditorUserName = editorUserName;
		this.mDate = date;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof TrelloAction) {
			TrelloAction test = (TrelloAction) obj;
			result = mBelongingCardId.equals(test.mBelongingCardId)
					&& mDate.equals(test.mDate)
					&& mEditorFullName.equals(test.mEditorFullName) 
					&& mEditorUserName.equals(test.mEditorUserName)
					&& mText.equals(test.mText)
					&& mActionType.equals(test.mActionType);
		}
		return result;
	}
	/**
	 * @return the mActionType
	 */
	public String getActionType() {
		return mActionType;
	}
	/**
	 * @return the mBelongingCardId
	 */
	public String getBelongingCardId() {
		return mBelongingCardId;
	}
	/**
	 * @return the mDate
	 */
	public String getDate() {
		return mDate;
	}
	/**
	 * @return the mEditorFullName
	 */
	public String getEditorFullName() {
		return mEditorFullName;
	}
	/**
	 * @return the mEditorUserName
	 */
	public String getEditorUserName() {
		return mEditorUserName;
	}
	/**
	 * @return the mText
	 */
	public String getText() {
		return mText;
	}
	/**
	 * @param mActionType the mActionType to set
	 */
	public void setActionType(String actionType) {
		this.mActionType = actionType;
	}
	/**
	 * @param mBelongingCardId the mBelongingCardId to set
	 */
	public void setBelongingCardId(String belongingCardId) {
		this.mBelongingCardId = belongingCardId;
	}
	/**
	 * @param mDate the mDate to set
	 */
	public void setDate(String date) {
		this.mDate = date;
	}
	
	/**
	 * @param mEditorFullName the mEditorFullName to set
	 */
	public void setEditorFullName(String editorFullName) {
		this.mEditorFullName = editorFullName;
	}
	/**
	 * @param mEditorUserName the mEditorUserName to set
	 */
	public void setEditorUserName(String editorUserName) {
		this.mEditorUserName = editorUserName;
	}
	/**
	 * @param mText the mText to set
	 */
	public void setText(String text) {
		this.mText = text;
	}
	
	@Override
	public String toString() {
		return "{cardId: \"" + mBelongingCardId + "\"; " 
				+ "date: \""	+ mDate + "\"; " 
				+ "editorFullName: \"" + mEditorFullName + "\"; "
				+ "editorUserName: \"" + mEditorUserName + "\"; "
				+ "actionType: \"" + mActionType + "\"; "
				+ "text: \"" + mText + "\"}";
	}
	

}
