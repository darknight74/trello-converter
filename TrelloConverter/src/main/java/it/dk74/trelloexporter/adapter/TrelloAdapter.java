package it.dk74.trelloexporter.adapter;

import it.dk74.trelloexporter.Card;
import it.dk74.trelloexporter.TrelloBoard;
import it.dk74.trelloexporter.TrelloList;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class TrelloAdapter extends TypeAdapter<TrelloBoard> {
	
	private static Logger LOG = LogManager.getLogger(TrelloAdapter.class);

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
			if (key.equals("id")) {
				value = in.nextString();
				LOG.debug(logHead + "<id> key found; value [" + value + "]");
				out.setId((String) value);
			} else if (key.equals("name")) {
				value = in.nextString();
				LOG.debug(logHead + "<name> key found; value [" + value + "]");
				out.setName((String) value);
			} else if(key.equals("cards")) {
				in.beginArray();
				TrelloCardAdapter cardAdapter = new TrelloCardAdapter();
				while (in.hasNext()) {
					Card cardToAdd = cardAdapter.read(in);
					out.addCard(cardToAdd);
					LOG.debug(logHead + "adding <card>; value [" + cardToAdd + "]");
				}
				in.endArray();
			} else if(key.equals("lists")) {
				in.beginArray();
				TrelloListAdapter listAdapter = new TrelloListAdapter();
				while(in.hasNext()) {
					TrelloList list = listAdapter.read(in);
					out.addList(list.getId(), list.getName());
					LOG.debug(logHead + "adding <list>; value [" + list.getId() + ", " + list.getName() + "]");
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
