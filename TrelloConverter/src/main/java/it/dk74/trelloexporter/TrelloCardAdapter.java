package it.dk74.trelloexporter;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class TrelloCardAdapter extends TypeAdapter<Card> {
	private static Logger LOG = LogManager.getLogger(TrelloCardAdapter.class);

	@Override
	public void write(JsonWriter out, Card value) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Card read(JsonReader in) throws IOException {
		final String logHead = "read - ";
		LOG.info(logHead + "start method");
		Card out = new Card();
		in.beginObject();
		while (in.hasNext()) {
			String key = in.nextName();
			String value;
			if (key.equals("desc")) {
				value = in.nextString();
				LOG.debug(logHead + "<desc> key found; value [" + value + "]");
				out.setDescription(value);
			} else if (key.equals("name")) {
				value = in.nextString();
				LOG.debug(logHead + "<name> key found; value [" + value + "]");
				out.setTitle(value);
			} else if(key.equals("due") && in.peek() != JsonToken.NULL) {
				value = in.nextString();
				LOG.debug(logHead + "<due> key found; value [" + value + "]");
				out.setDueDate(value);
			} else {
				in.skipValue();
			}
				
		}
		in.endObject();
		LOG.info(logHead + "end method");
		return out;
	}

}
