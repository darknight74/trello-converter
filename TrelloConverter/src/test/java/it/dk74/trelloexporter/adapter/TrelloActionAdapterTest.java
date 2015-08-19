package it.dk74.trelloexporter.adapter;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import it.dk74.trelloexporter.model.TrelloAction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class TrelloActionAdapterTest {
	
	private static Logger LOG = LogManager.getLogger(TrelloActionAdapterTest.class);

	@Test
	public void testDeserializationComment() {
		TrelloActionAdapter adapter = new TrelloActionAdapter();
		URL fileURL = this.getClass().getResource("/ActionComment.json");
		try {
			TrelloAction expected = new TrelloAction();
			expected.setBelongingCardId("55d43e14b23e44eb48b2ea1a");
			expected.setDate("2015-08-19T08:28:32.514Z");
			expected.setEditorFullName("Andrea Fabris");
			expected.setEditorUserName("andreafabriswind");
			expected.setText("Risolti attraverso editing resolv.conf da parte di sistemisti");
			expected.setActionType("commentCard");
			TrelloAction actual = adapter.fromJson(new FileReader(fileURL
					.getFile()));
			assertEquals(expected, actual);
		} catch (FileNotFoundException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testDeserializationCreate() {
		TrelloActionAdapter adapter = new TrelloActionAdapter();
		URL fileURL = this.getClass().getResource("/ActionCreateCard.json");
		try {
			TrelloAction expected = new TrelloAction();
			expected.setBelongingCardId("55d43e14b23e44eb48b2ea1a");
			expected.setDate("2015-08-19T08:28:04.233Z");
			expected.setEditorFullName("Andrea Fabris");
			expected.setEditorUserName("andreafabriswind");
			expected.setActionType("createCard");
			TrelloAction actual = adapter.fromJson(new FileReader(fileURL
					.getFile()));
			assertEquals(expected, actual);
		} catch (FileNotFoundException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testDeserializationUpdate() {
		TrelloActionAdapter adapter = new TrelloActionAdapter();
		URL fileURL = this.getClass().getResource("/ActionUpdateCard.json");
		try {
			TrelloAction expected = new TrelloAction();
			expected.setBelongingCardId("55b5d90265dbb9d2725392c3");
			expected.setDate("2015-08-03T07:10:15.240Z");
			expected.setEditorFullName("Walter Tocchi");
			expected.setEditorUserName("waltertocchi");
			expected.setActionType("updateCard");
			TrelloAction actual = adapter.fromJson(new FileReader(fileURL
					.getFile()));
			assertEquals(expected, actual);
		} catch (FileNotFoundException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

}
