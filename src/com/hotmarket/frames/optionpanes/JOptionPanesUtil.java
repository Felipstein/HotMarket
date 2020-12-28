package com.hotmarket.frames.optionpanes;

import javax.swing.JOptionPane;

public class JOptionPanesUtil {
	
	public static void anErrorExcepted() {
		JOptionPane.showMessageDialog(null, "Um erro aconteceu ao processar essa ação! Verifique o arquivo de logs para informação concretas do código.", "ERRO", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void anErrorExcepted(String reason) {
		JOptionPane.showMessageDialog(null, reason, "ERRO", JOptionPane.ERROR_MESSAGE);
	}
	
	public static int areYouOkBro(String message, String title) {
		return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
	}
	
	public static void thatIsSoCool(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
}