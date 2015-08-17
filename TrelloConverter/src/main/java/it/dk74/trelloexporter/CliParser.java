package it.dk74.trelloexporter;

import it.dk74.trelloexporter.adapter.TrelloBoardAdapter;
import it.dk74.trelloexporter.adapter.TrelloCardAdapter;
import it.dk74.trelloexporter.adapter.TrelloListAdapter;
import it.dk74.trelloexporter.model.TrelloBoard;
import it.dk74.trelloexporter.model.TrelloCard;
import it.dk74.trelloexporter.model.TrelloList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CliParser {
	
	private static final Logger LOG = LogManager.getLogger(CliParser.class);

	public static void main(String[] args) {
		LOG.info("Start Trello JSON parsing");
		Options options = new Options();
		Option input = Option.builder("i")
				.argName("json")
				.desc("Json input")
				.hasArg()
				.required()
				.build();
		Option output = Option.builder("o")
				.argName("xslx")
				.desc("xslx output")
				.hasArg()
				.required()
				.build();
		options.addOption(input);
		options.addOption(output);
		
		HelpFormatter hf = new HelpFormatter();
		
		DefaultParser parser = new DefaultParser();
		BufferedReader br;
		try {
			//Setup CLI parser
			CommandLine cli = parser.parse(options, args);
			String jsonFile = cli.getOptionValue("i");
			String xlsxOut = cli.getOptionValue("o");
			//Setup GSON
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(TrelloBoard.class,new TrelloBoardAdapter());
			gsonBuilder.registerTypeAdapter(TrelloList.class, new TrelloListAdapter());
			gsonBuilder.registerTypeAdapter(TrelloCard.class, new TrelloCardAdapter());
			Gson gson = gsonBuilder.create();
			String json = "";
			//Read Json String from input
			String line = "";
			FileReader fr = new FileReader(jsonFile);
			br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				json = json + line;
			}
			br.close();
			TrelloBoard board = gson.fromJson(json, TrelloBoard.class);
			ExcelGenerator generator = ExcelGenerator.getInstance();
			Workbook excel = generator.generate(board);
			excel.write(new FileOutputStream(xlsxOut));
			LOG.info("End Trello JSON parsing");
		} catch (FileNotFoundException e) {
			System.out.println("Missing input or output file");
			LOG.error("Missing file", e);
		} catch (Exception e) {
			hf.printHelp("TrelloConverter", options);
			LOG.error("General error", e);
		} 
		
	}

}
