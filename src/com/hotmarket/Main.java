package com.hotmarket;

import com.hotmarket.files.FileItems;
import com.hotmarket.files.FileLog;
import com.hotmarket.frames.market.MainFrame;
import com.hotmarket.frames.ui.UIFrame;
import com.hotmarket.logger.Logger;

public class Main {
	
	public static void main(String[] args) {
		FileLog.archive = new FileLog();
		Logger.logger = new Logger(true);
		FileItems.archive = new FileItems();
		
		UIFrame.loadWindowsForm();
		
		MainFrame frame = new MainFrame();	
		
	}
	
}