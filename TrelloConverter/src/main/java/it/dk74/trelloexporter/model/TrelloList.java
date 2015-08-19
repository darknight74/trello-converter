package it.dk74.trelloexporter.model;


public class TrelloList {
	private String mName;
	private String mId;
	
	public TrelloList() {
	}
	
	public TrelloList(String id, String name) {
		this.mName = name;
		this.mId = id;
	}
	
	public String getName() {
		return mName;
	}
	
	public void setName(String mName) {
		this.mName = mName;
	}
	
	public String getId() {
		return mId;
	}

	public void setId(String mId) {
		this.mId = mId;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean ret = false;
		if (obj instanceof TrelloList) {
			TrelloList toTest = (TrelloList) obj;
			ret = this.mId.equals(toTest.mId)
					&& this.mName.equals(toTest.mName);
			
		}
		return ret;
	}
	
	@Override
	public String toString() {
		return "{id: \"" + mId + "\"; "
				+ "name: \"" + mName + "\"}";
	}
}
