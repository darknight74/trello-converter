package it.dk74.trelloexporter.adapter;

import it.dk74.trelloexporter.model.TrelloAction;
import it.dk74.trelloexporter.model.TrelloBoard;
import it.dk74.trelloexporter.model.TrelloCard;
import it.dk74.trelloexporter.model.TrelloList;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class TrelloBoardAdapter extends TypeAdapter<TrelloBoard> {
	
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_CARDS = "cards";
	private static final String KEY_LISTS = "lists";
	private static final String  KEY_ACTIONS = "actions";
	private static Logger LOG = LogManager.getLogger(TrelloBoardAdapter.class);

	@Override
	public void write(JsonWriter out, TrelloBoard value) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TrelloBoard read(JsonReader in) throws IOException {
		final String logHead = "read - ";
		LOG.info(logHead + "start method");
		TrelloBoard out = new TrelloBoard();
		in.beginObject();
		while (in.hasNext()) {
			String key = in.nextName();
			Object value;
			if (key.equals(KEY_ID)) {
				value = in.nextString();
				LOG.debug(logHead + "<id> key found; value [" + value + "]");
				out.setId((String) value);
			} else if (key.equals(KEY_NAME)) {
				value = in.nextString();
				LOG.debug(logHead + "<name> key found; value [" + value + "]");
				out.setName((String) value);
			} else if(key.equals(KEY_CARDS)) {
				in.beginArray();
				TrelloCardAdapter cardAdapter = new TrelloCardAdapter();
				while (in.hasNext()) {
					TrelloCard cardToAdd = cardAdapter.read(in);
					out.addCard(cardToAdd);
					LOG.debug(logHead + "adding <card>; value [" + cardToAdd + "]");
				}
				in.endArray();
			} else if(key.equals(KEY_LISTS)) {
				in.beginArray();
				TrelloListAdapter listAdapter = new TrelloListAdapter();
				while(in.hasNext()) {
					TrelloList list = listAdapter.read(in);
					out.addList(list);
					LOG.debug(logHead + "adding <list>; value [" + list.getId() + ", " + list.getName() + "]");
				}
				in.endArray();
			} else if (key.equals(KEY_ACTIONS)) {
				in.beginArray();
				TrelloActionAdapter actionAdapter = new TrelloActionAdapter();
				while (in.hasNext()) {
					TrelloAction action = actionAdapter.read(in);
					out.addAction(action);
					LOG.debug(logHead + "adding <action>");
				}
				in.endArray();				
			} else {
				in.skipValue();
			}
				
		}
		in.endObject();
		LOG.info(logHead + "end method");
		return out;
	}

}
