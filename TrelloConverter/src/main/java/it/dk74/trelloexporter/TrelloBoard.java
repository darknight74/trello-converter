package it.dk74.trelloexporter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class TrelloBoard {
	
	private String mName = "";
	private String mId = "";
	private List<Card> mCards = new Vector<Card>();
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
	
	public List<Card> getCards() {
		return mCards;
	}
	
	public void setCards(List<Card> cards) {
		this.mCards = cards;
	}
	
	public void addCard(Card card) {
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
}
