package it.dk74.trelloexporter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class TrelloBoard {
	
	private String mName = "";
	private String mId = "";
	private List<TrelloCard> mCards = new Vector<TrelloCard>();
	private Map<String, String> mLists = new HashMap<String, String>();
	
	public String getName() {
		return mName;
	}
	
	public void setName(String name) {
		this.mName = name;
	}
	
	public String getId() {
		return mId;
	}
	
	public void setId(String id) {
		this.mId = id;
	}
	
	public List<TrelloCard> getCards() {
		return mCards;
	}
	
	public void setCards(List<TrelloCard> cards) {
		this.mCards = cards;
	}
	
	public void addCard(TrelloCard card) {
		mCards.add(card);
	}
	
	public void addList(String listId, String listName) {
		mLists.put(listId, listName);
	}
	
	@Override
	public String toString() {
		return "{id: \"" + mId + "\"; " 
				+ "name: \""	+ mName + "\"; " 
				+  "\"}";
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean ret = false;
		if (obj instanceof TrelloBoard) {
			TrelloBoard toTest = (TrelloBoard) obj;
			ret = this.mId.equals(toTest.mId) //check BoardId
					&& this.mName.equals(toTest.mName) //check Board Name
					&& this.mCards.equals(toTest.mCards) //check Cards
					&& this.mLists.equals(toTest.mLists); //check Lists
		} 
		return ret;
	}
}
