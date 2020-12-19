package com.hotmarket.exceptions;

import com.hotmarket.frames.optionpanes.JOptionPanesUtil;
import com.hotmarket.logger.Logger;

public class EException extends Exception {
	
	private static final long serialVersionUID = -5202322808465261733L;
	
	public EException(String message) {
		super(message);
	}
	
	public EException() {
		super();
	}
	
	public EException(String message, boolean showMessage) {
		super(message);
		if(showMessage) {
			this.showMessage();
		}
	}
	
	public void printToClient() {
		this.showMessage();
		this.log();
	}
	
	public void showMessage() {
		JOptionPanesUtil.anErrorExcepted(getMessage());
	}
	
	public void log() {
		Logger.logger.error(getMessage());
	}
	
}