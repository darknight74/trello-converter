package it.dk74.trelloexporter.adapter;

import it.dk74.trelloexporter.model.TrelloCard;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class TrelloCardAdapter extends TypeAdapter<TrelloCard> {
	private static final String KEY_ID_LIST = "idList";
	private static final String KEY_DUE = "due";
	private static final String KEY_NAME = "name";
	private static final String KEY_DESC = "desc";
	private static final String KEY_ID = "id";
	private static Logger LOG = LogManager.getLogger(TrelloCardAdapter.class);

	@Override
	public void write(JsonWriter out, TrelloCard value) throws IOException {
		// TODO Auto-generated method stub
		
	}

	
	
	@Override
	public TrelloCard read(JsonReader in) throws IOException {
		final String logHead = "read - ";
		LOG.info(logHead + "start method");
		TrelloCard out = new TrelloCard();
		in.beginObject();
		while (in.hasNext()) {
			String key = in.nextName();
			String value;
			if (key.equals(KEY_ID)) {
				value = in.nextString();
				LOG.debug(logHead + "<" + KEY_ID + "> key found; value [" + value + "]");
				out.setId(value);
			} else if (key.equals(KEY_DESC)) {
				value = in.nextString();
				LOG.debug(logHead + "<" + KEY_DESC + "> key found; value [" + value + "]");
				out.setDescription(value);
			} else if (key.equals(KEY_NAME)) {
				value = in.nextString();
				LOG.debug(logHead + "<" + KEY_NAME + "> key found; value [" + value + "]");
				out.setTitle(value);
			} else if(key.equals(KEY_DUE) && in.peek() != JsonToken.NULL) {
				value = in.nextString();
				LOG.debug(logHead + "<" + KEY_DUE + "> key found; value [" + value + "]");
				out.setDueDate(value);
			} else if (key.equals(KEY_ID_LIST)) {
				value = in.nextString();
				LOG.debug(logHead + "<" + KEY_ID_LIST + "> key found; value [" + value + "]");
				out.setIdList(value);
			} else {
				in.skipValue();
			}
				
		}
		in.endObject();
		LOG.info(logHead + "end method");
		return out;
	}

}
