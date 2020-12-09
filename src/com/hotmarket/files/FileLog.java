package com.hotmarket.files;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import com.hotmarket.utils.FileManager;

public class FileLog extends File {
	
	private static final long serialVersionUID = -7445997049474725835L;
	
	public static FileLog archive;
	
	public FileLog() {
		super("\\logs\\" + new SimpleDateFormat("dd_MM_yyyy").format(System.currentTimeMillis()) + ".txt");
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
		try {
			this.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}