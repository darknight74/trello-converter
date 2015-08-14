package it.dk74.trelloexporter;

import it.dk74.trelloexporter.model.TrelloBoard;
import it.dk74.trelloexporter.model.TrelloCard;
import it.dk74.trelloexporter.model.TrelloList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelGenerator {
	
	private static ExcelGenerator instance;
	
	public static ExcelGenerator getInstance() {
		if (instance == null) {
			instance = new ExcelGenerator();
		}
		return instance;
	}
	public Workbook generate(TrelloBoard board) {
		Workbook wb = new XSSFWorkbook();
		Sheet mainSheet = wb.createSheet(board.getName());
		CreationHelper createHelper = wb.getCreationHelper();
		List<TrelloCard> cards = board.getCards();
		List<TrelloList> lists = board.getLists();
		//Convert the list List into a searcheable Map
		Map<String, String> listsMap = new HashMap<String, String>();
		for (TrelloList trelloList : lists) {
			listsMap.put(trelloList.getId(), trelloList.getName());
		}
		//Add header row
		Row header = mainSheet.createRow(0);
		header.createCell(0).setCellValue("ID");
		header.createCell(1).setCellValue("Titolo");
		header.createCell(2).setCellValue("Stato");
		header.createCell(3).setCellValue("Descrizione");
		header.createCell(4).setCellValue("Due Date");
		
		int rowIdx = 1;
		for (TrelloCard trelloCard : cards) {
			Row row = mainSheet.createRow(rowIdx);
			setRowValues(row, String.valueOf(rowIdx), trelloCard, listsMap);
			rowIdx++;
		}
		return wb;
	}
	
	private void setRowValues(Row row, String idx, TrelloCard card, Map<String, String> lists) {
		row.createCell(0).setCellValue(idx);
		row.createCell(1).setCellValue(card.getTitle());
		row.createCell(2).setCellValue(lists.get(card.getIdList()));
		row.createCell(3).setCellValue(card.getDescription());
		row.createCell(4).setCellValue(card.getDueDate());
	}
}
