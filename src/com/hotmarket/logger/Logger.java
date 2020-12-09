package com.hotmarket.logger;

import java.text.SimpleDateFormat;

import com.hotmarket.files.FileLog;

public class Logger {
	
	public static Logger logger;
	
	private boolean saveLogs;
	
	public Logger(boolean saveLogs) {
		this.saveLogs = saveLogs;
	}
	
	public void info(Object x) {
		this.log("[INFO] " + x, false);
	}
	
	public void warn(Object x) {
		this.log("[WARN] " + x, false);
	}
	
	public void error(Object x) {
		this.log("[ERROR] " + x, true);
	}
	
	public void fatal(Object x) {
		this.log("[FATAL/ERROR] " + x, true);
	}
	
	public void log(Object x, boolean error) {
		String out = "[" + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(System.currentTimeMillis()) + "] " + String.valueOf(x);
		(error ? System.err : System.out).println(out);
		if(saveLogs) {
			FileLog.archive.addLog(out);
		}
	}
	
}