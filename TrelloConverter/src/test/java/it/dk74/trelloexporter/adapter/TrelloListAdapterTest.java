package it.dk74.trelloexporter.adapter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import it.dk74.trelloexporter.TrelloList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.junit.Test;

public class TrelloListAdapterTest {

	@Test
	public void testReadJsonReader() {
		TrelloList expected= new TrelloList();
		expected.setId("5583eecff24dc5fb455835b6");
		expected.setName("Open");
		TrelloListAdapter adapter = new TrelloListAdapter();
		URL fileURL = this.getClass().getResource("/List.json");
		try {
			TrelloList actual = adapter.fromJson(new FileReader(fileURL.getFile()));
			assertEquals(expected, actual);
		} catch (FileNotFoundException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

}
