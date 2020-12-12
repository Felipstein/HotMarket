package com.hotmarket.frames.recicle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@FunctionalInterface
public interface ConsumerAction extends ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e);
	
}