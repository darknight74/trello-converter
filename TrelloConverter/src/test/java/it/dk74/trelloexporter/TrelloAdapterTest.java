package it.dk74.trelloexporter;

import static org.junit.Assert.*;
import it.dk74.trelloexporter.adapter.TrelloAdapter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class TrelloAdapterTest {
	
	private static Logger LOG = LogManager.getLogger(TrelloAdapterTest.class);

	@Test
	public void testReadJsonReader() {
		TrelloAdapter adapter = new TrelloAdapter();
		URL fileURL = this.getClass().getResource("/Trello.json");
		try {
			TrelloBoard board = adapter.fromJson(new FileReader(fileURL.getFile()));
		} catch (FileNotFoundException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

}
