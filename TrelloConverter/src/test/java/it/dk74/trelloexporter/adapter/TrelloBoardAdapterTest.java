package it.dk74.trelloexporter.adapter;

import static org.junit.Assert.*;
import it.dk74.trelloexporter.adapter.TrelloBoardAdapter;
import it.dk74.trelloexporter.model.TrelloBoard;
import it.dk74.trelloexporter.model.TrelloCard;
import it.dk74.trelloexporter.model.TrelloList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class TrelloBoardAdapterTest {
	
	private static Logger LOG = LogManager.getLogger(TrelloBoardAdapterTest.class);

	@Test
	public void testDeserialization() {
		TrelloBoardAdapter adapter = new TrelloBoardAdapter();
		URL fileURL = this.getClass().getResource("/TrelloSimple.json");
		try {
			TrelloBoard expected = new TrelloBoard();
			expected.setId("5583eebeb204c1531f5fed82");
			expected.setName("MyWind issue");
			TrelloCard one = new TrelloCard();
			one.setDescription("Esecuzione test su Build ID maggio");
			one.setTitle("App 4.2 - esecuzione test su build ID maggio");
			one.setIdList("5583eecff24dc5fb455835b6");
			expected.addCard(one);
			TrelloCard two = new TrelloCard();
			two.setDescription("");
			two.setTitle("Porting api msite su WL e documentazione integrazione 4.1");
			two.setIdList("5583eecff24dc5fb455835b6");
			two.setDueDate("2015-06-24T10:00:00.000Z");
			expected.addCard(two);
			expected.addList(new TrelloList("5583eecff24dc5fb455835b6", "Open"));
			expected.addList(new TrelloList("5583eed320b369bb0647e352", "WIP"));
			expected.addList(new TrelloList("5583eeda816d632cdfda0ea3", "Closed"));
			TrelloBoard actual = adapter.fromJson(new FileReader(fileURL.getFile()));
			assertEquals(expected, actual);
		} catch (FileNotFoundException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

}
