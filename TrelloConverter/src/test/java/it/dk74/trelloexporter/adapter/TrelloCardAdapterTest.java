package it.dk74.trelloexporter.adapter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import it.dk74.trelloexporter.model.TrelloCard;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.junit.Test;

public class TrelloCardAdapterTest {

	@Test
	public void testReadJsonReader() {
		TrelloCard test = new TrelloCard();
		test.setId("5583f1f16c7e5fde3eed2742");
		test.setTitle("App 4.2 - esecuzione test su build ID maggio");
		test.setDescription("Esecuzione test su Build ID maggio");
		test.setIdList("5583eecff24dc5fb455835b6");
		test.setDueDate(null);
		TrelloCardAdapter adapter = new TrelloCardAdapter();
		URL fileURL = this.getClass().getResource("/Card.json");
		try {
			TrelloCard out = adapter.fromJson(new FileReader(fileURL.getFile()));
			assertEquals(test, out);
		} catch (FileNotFoundException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

}
