package it.dk74.trelloexporter.adapter;

import it.dk74.trelloexporter.model.TrelloAction;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class TrelloActionAdapter extends TypeAdapter<TrelloAction> {
	
	private static final Logger LOG = LogManager.getLogger(TrelloActionAdapter.class);
	
	private static final String KEY_MEMBER_CREATOR_USERNAME = "username";

	private static final String KEY_MEMBER_CREATOR_FULL_NAME = "fullName";

	private static final String KEY_MEMBER_CREATOR = "memberCreator";

	private static final String KEY_DATE = "date";

	private static final String KEY_TYPE = "type";

	private static final String KEY_DATA = "data";

	private static final String KEY_DATA_TEXT = "text";

	private static final String KEY_DATA_CARD = "card";

	private static final String KEY_DATA_CARD_ID = "id";

	@Override
	public TrelloAction read(JsonReader in) throws IOException {
		final String logHead = "read - ";
		LOG.info(logHead + "start method");
		TrelloAction out = new TrelloAction();
		in.beginObject();
		while (in.hasNext()) {
			String key = in.nextName();
			String value;
			if (key.equals(KEY_TYPE)) {
				value = in.nextString();
				LOG.debug(logHead + "<" + KEY_TYPE + "> key found; value [" + value + "]");
				out.setActionType(value);
			} else if (key.equals(KEY_DATE)) {
				value = in.nextString();
				LOG.debug(logHead + "<"+ KEY_DATE + "> key found; value [" + value + "]");
				out.setDate(value);
			} else if (key.equals(KEY_MEMBER_CREATOR)) {
				LOG.debug(logHead + "<" + KEY_MEMBER_CREATOR + "> key found");
				in.beginObject();
				while (in.hasNext()) {
					key = in.nextName();
					if (key.equals(KEY_MEMBER_CREATOR_FULL_NAME)) {
						value = in.nextString();
						LOG.debug(logHead + "<" + KEY_MEMBER_CREATOR_FULL_NAME + "> key found; value [" + value + "]");
						out.setEditorFullName(value);
					} else if (key.equals(KEY_MEMBER_CREATOR_USERNAME)) {
						value = in.nextString();
						LOG.debug(logHead + "<" + KEY_MEMBER_CREATOR_USERNAME + "> key found; value [" + value + "]");
						out.setEditorUserName(value);
					} else {
						in.skipValue();
					}
				}
				in.endObject();
			} else if (key.equals(KEY_DATA)) {
				LOG.debug(logHead + "<" + KEY_DATA + "> key found");
				in.beginObject();
				while (in.hasNext()) {
					key = in.nextName();
					if (key.equals(KEY_DATA_TEXT)) {
						value = in.nextString();
						LOG.debug(logHead + "<" + KEY_DATA_TEXT + "> key found; value [" + value + "]");
						out.setText(value);
					} else if (key.equals(KEY_DATA_CARD)) {
						LOG.debug(logHead + "<" + KEY_DATA_CARD + "> key found");
						in.beginObject();
						while (in.hasNext()) {
							key = in.nextName();
							if (key.equals(KEY_DATA_CARD_ID)) {
								value = in.nextString();
								LOG.debug(logHead + "<" + KEY_DATA_CARD_ID + "> key found; value [" + value + "]");
								out.setBelongingCardId(value);
							} else {
								in.skipValue();
							}
						}
						in.endObject();
					} else {
						in.skipValue();
					}
				}
				in.endObject();
			} else {
				in.skipValue();
			}
		}
		in.endObject();
		return out;
	}

	@Override
	public void write(JsonWriter out, TrelloAction value) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
