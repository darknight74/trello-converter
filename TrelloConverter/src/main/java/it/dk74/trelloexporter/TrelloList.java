package it.dk74.trelloexporter;

import java.util.ArrayList;
import java.util.List;

public class TrelloList {
	private String mName;
	private List<Card> mCards;
	
	public TrelloList() {
		mName = null;
		mCards = new ArrayList<Card>();
	}
	
	public String getName() {
		return mName;
	}
	
	public void setName(String mName) {
		this.mName = mName;
	}
	
	public List<Card> getCards() {
		return mCards;
	}
	
	public void addCard(Card card) {
		mCards.add(card);
	}
}
