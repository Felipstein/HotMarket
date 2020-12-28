package com.hotmarket;

import com.hotmarket.client.Cashier;
import com.hotmarket.files.FileItems;
import com.hotmarket.files.FileLog;
import com.hotmarket.frames.market.main.MainFrame;
import com.hotmarket.frames.ui.UIFrame;
import com.hotmarket.logger.Logger;

public class Main {
	
	public static final String VERSION;
	
	static {
		FileLog.archive = new FileLog();
		Logger.logger = new Logger(true);
		FileItems.archive = new FileItems();
		VERSION = "0.0.1-SNAPSHOT";
	}
	
	public static void main(String[] args) {
		Logger.logger.info("Iniciando...");
		
		UIFrame.loadWindowsForm();
		
	//	LoginFrame frame = new LoginFrame();
		MainFrame frame = new MainFrame(new Cashier(1, "Fulano Perento"));
		
		frame.setVisible(true);
		
	}
	
	public static void shutdown() {
		Logger.logger.info("Finalizando...");
		System.exit(0);
	}
	
}