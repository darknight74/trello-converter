package it.dk74.trelloexporter.adapter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import it.dk74.trelloexporter.model.TrelloAction;
import it.dk74.trelloexporter.model.TrelloBoard;
import it.dk74.trelloexporter.model.TrelloCard;
import it.dk74.trelloexporter.model.TrelloList;

import java.io.FileReader;
import java.net.URL;

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class TrelloBoardAdapterTest {
	
	private static Logger LOG = LogManager.getLogger(TrelloBoardAdapterTest.class);

	@Test
	public void testDeserialization() {
		LOG.info("Start TrelloBoardAdapterTest.testDeserialization");
		TrelloBoardAdapter adapter = new TrelloBoardAdapter();
		URL fileURL = this.getClass().getResource("/TrelloSimple.json");
		try {
			TrelloBoard expected = new TrelloBoard();
			expected.setId("5583eebeb204c1531f5fed82");
			expected.setName("MyWind issue");
			expected.addCard(new TrelloCard("5583f1f16c7e5fde3eed2742", "App 4.2 - esecuzione test su build ID maggio",
					"Esecuzione test su Build ID maggio",
					"5583eecff24dc5fb455835b6"));
			TrelloCard two = new TrelloCard("55897fb0b6f48c6a40aa0c9e", "Porting api msite su WL e documentazione integrazione 4.1",
					"",
					"5583eecff24dc5fb455835b6");
			two.setDueDate("2015-06-24T10:00:00.000Z");
			expected.addCard(two);
			expected.addList(new TrelloList("5583eecff24dc5fb455835b6", "Open"));
			expected.addList(new TrelloList("5583eed320b369bb0647e352", "WIP"));
			expected.addList(new TrelloList("5583eeda816d632cdfda0ea3", "Closed"));
			
			TrelloAction actionOne = new TrelloAction("commentCard",
					"Walter Tocchi",
					"waltertocchi",
					"2015-07-11T15:57:54.086Z");
			actionOne.setText("In rilascio domenica 12/7");
			actionOne.setBelongingCardId("559245160d2a011f5353adce");
			expected.addAction(actionOne);
			
			TrelloAction actionTwo = new TrelloAction("updateCard", 
					"Walter Tocchi", 
					"waltertocchi", 
					"2015-07-06T15:15:48.196Z");
			actionTwo.setBelongingCardId("5583f3a0f7a3500a0c2420be");
			expected.addAction(actionTwo);
			
			TrelloAction actionThree = new TrelloAction("createCard",
					"Andrea Fabris",
					"ingfabrisandrea",
					"2015-06-19T10:29:58.627Z");
			actionThree.setBelongingCardId("5583ef26f693d6e83147e2a2");
			expected.addAction(actionThree);
			
			expected.addAction(new TrelloAction("createList",
					"Andrea Fabris",
					"ingfabrisandrea",
					"2015-06-19T10:28:42.068Z"));
			
			expected.addAction(new TrelloAction("addToOrganizationBoard",
					"Andrea Fabris",
					"ingfabrisandrea",
					"2015-06-19T10:28:14.968Z"));
			
			expected.addAction(new TrelloAction("createBoard",
					"Andrea Fabris",
					"ingfabrisandrea",
					"2015-06-19T10:28:14.957Z"));
			
			TrelloBoard actual = adapter.fromJson(new FileReader(fileURL.getFile()));
			LOG.info("Comparing expected: " 
					+ PropertyUtils.describe(expected) 
					+ "to actual" 
					+ PropertyUtils.describe(actual));
			assertEquals(expected, actual);
		} catch (Exception e) {
			fail(e.getMessage());
		} 
		LOG.info("End TrelloBoardAdapterTest.testDeserialization");
	}

}
