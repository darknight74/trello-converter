package it.dk74.trelloexporter;

import it.dk74.trelloexporter.model.TrelloBoard;
import it.dk74.trelloexporter.model.TrelloCard;
import it.dk74.trelloexporter.model.TrelloList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelGenerator {
	
	private static ExcelGenerator instance;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
	
	private static final Logger LOG = LogManager.getLogger(ExcelGenerator.class);
	
	public static ExcelGenerator getInstance() {
		if (instance == null) {
			instance = new ExcelGenerator();
		}
		return instance;
	}
	public Workbook generate(TrelloBoard board) {
		final String logHead = "generate - ";
		LOG.info(logHead + "Start workbook generation");
		XSSFWorkbook wb = new XSSFWorkbook();
		Sheet mainSheet = wb.createSheet(board.getName());
		CreationHelper createHelper = wb.getCreationHelper();
		List<TrelloCard> cards = board.getCards();
		List<TrelloList> lists = board.getLists();
		//Convert the list List into a searcheable Map
		Map<String, String> listsMap = new HashMap<String, String>();
		for (TrelloList trelloList : lists) {
			listsMap.put(trelloList.getId(), trelloList.getName());
		}
		
		//Create header cell style
		XSSFCellStyle headerCellStyle = wb.createCellStyle();
		Font headerFont = wb.createFont();
		headerFont.setBold(true); //font bold
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());;//gary cell background

		//Date cell style
		CellStyle dateCellStyle = wb.createCellStyle();
		dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
				
		//Add header row
		//create header
		Row header = mainSheet.createRow(0);
		//create header cells
		createCellAndApplyStyle(header, headerCellStyle, 0, "ID");
		createCellAndApplyStyle(header, headerCellStyle, 1, "Titolo");
		createCellAndApplyStyle(header, headerCellStyle, 2, "Stato");
		createCellAndApplyStyle(header, headerCellStyle, 3, "Descrizione");
		createCellAndApplyStyle(header, headerCellStyle, 4, "Due Date");
		
		
		int rowIdx = 1;
		for (TrelloCard trelloCard : cards) {
			Row row = mainSheet.createRow(rowIdx);
			setRowValues(row, String.valueOf(rowIdx), trelloCard, listsMap, dateCellStyle);
			rowIdx++;
		}
		LOG.info(logHead + "End workbook generation");
		return wb;
	}
	
	/**
	 * Helper method for formatting header cells
	 * @param row 
	 * Row where create cell
	 * @param cellStyle
	 * Style to apply
	 * @param index
	 * Cell index
	 * @param content
	 * Cell content
	 */
	private void createCellAndApplyStyle(Row row, CellStyle cellStyle, int index, String content) {
		Cell cell = row.createCell(index);
		if (cellStyle != null) {
			cell.setCellStyle(cellStyle);
		}
		cell.setCellValue(content);
	}
	
	private void createCellAndApplyStyle(Row row, CellStyle cellStyle, int index, Date content) {
		Cell cell = row.createCell(index);
		if (cellStyle != null) {
			cell.setCellStyle(cellStyle);
		}
		cell.setCellValue(content);
	}
	
	private void setRowValues(Row row, String idx, TrelloCard card, Map<String, String> lists, CellStyle dateCellStyle) {
		createCellAndApplyStyle(row, null, 0, idx);
		createCellAndApplyStyle(row, null, 1, card.getTitle());
		createCellAndApplyStyle(row, null, 2, lists.get(card.getIdList()));
		createCellAndApplyStyle(row, null, 3, card.getDescription());
		try {
			String date = card.getDueDate();
			if (date != null && !date.isEmpty()) {
				createCellAndApplyStyle(row, dateCellStyle, 4, sdf.parse(date));
			} else {
				createCellAndApplyStyle(row, null, 4, "");
			}
		} catch (ParseException e) {
			LOG.warn("Impossibile to parse date", e);
			createCellAndApplyStyle(row, null, 4, "");
		} 
	}
}
