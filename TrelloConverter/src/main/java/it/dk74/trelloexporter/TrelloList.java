package it.dk74.trelloexporter;

import java.util.ArrayList;
import java.util.List;

public class TrelloList {
	private String mName;
	private String mId;
	
	public TrelloList() {
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
}
