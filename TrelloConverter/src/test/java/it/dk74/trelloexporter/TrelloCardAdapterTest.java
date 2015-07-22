package it.dk74.trelloexporter;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.junit.Test;

public class TrelloCardAdapterTest {

	@Test
	public void testReadJsonReader() {
		Card test = new Card();
		test.setTitle("App 4.2 - esecuzione test su build ID maggio");
		test.setDescription("Esecuzione test su Build ID maggio");
		test.setDueDate(null);
		TrelloCardAdapter adapter = new TrelloCardAdapter();
		URL fileURL = this.getClass().getResource("/Card.json");
		try {
			Card out = adapter.fromJson(new FileReader(fileURL.getFile()));
			assertEquals(test, out);
			
		} catch (FileNotFoundException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

}
