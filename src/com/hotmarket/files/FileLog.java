package com.hotmarket.files;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import com.hotmarket.logger.Logger;
import com.hotmarket.utils.FileManager;

public class FileLog extends File {
	
	private static final long serialVersionUID = -7445997049474725835L;
	
	public static FileLog archive;
	
	public FileLog() {
		super("logs\\" + new SimpleDateFormat("dd_MM_yyyy").format(System.currentTimeMillis()) + ".txt");
	}
	
	public void addLog(String message) {
		this.checkExistence();
		try {
			FileManager.appendText(this, message, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkExistence() {
		if(exists()) {
			return true;
		}
		File logsFolder = new File("logs");
		if(!logsFolder.exists()) {
			logsFolder.mkdir();
		}
		try {
			this.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void clearLog(String date) {
		File log = new File("logs\\" + date + (date.endsWith(".txt") ? "" : ".txt"));
		if(!log.exists()) {
			Logger.logger.error("O arquivo logs\\" + date + ".txt não existe.");
			return;
		}
		log.delete();
	}
	
	public int clearLogs() {
		int total = 0;
		File[] logs = new File("logs").listFiles();
		for(File log : logs) {
			log.delete();
			++total;
		}
		return total;
	}
	
}