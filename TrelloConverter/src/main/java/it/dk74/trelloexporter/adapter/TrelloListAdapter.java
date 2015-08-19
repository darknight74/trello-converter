package it.dk74.trelloexporter.adapter;

import it.dk74.trelloexporter.model.TrelloList;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class TrelloListAdapter extends TypeAdapter<TrelloList> {

	@Override
	public void write(JsonWriter out, TrelloList value)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TrelloList read(JsonReader in) throws IOException {
		TrelloList out = new TrelloList();
		in.beginObject();
		while (in.hasNext()) {
			String key = in.nextName();
			String value;
			if (key.equals("id")) {
				value = in.nextString();
				out.setId(value);
			} else if (key.equals("name")) {
				value = in.nextString();
				out.setName(value);
			} else {
				in.skipValue();
			}
		}
		in.endObject();
		return out;
	}

}
