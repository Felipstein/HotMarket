package com.hotmarket.frames.ui;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.JComponent;
import javax.swing.JPanel;

import com.hotmarket.frames.ComponentList;
import com.hotmarket.frames.recicle.InputListener;

public class UIPanel extends JPanel {
	
	private static final long serialVersionUID = 7252822652371841742L;
	
	private UIFrame frame;
	private Dimension dimension;
	
	private ComponentList components;
	
	private Map<String, UIPanel> panels;
	
	public UIPanel(UIFrame frame, Dimension dimension) {
		super();
		this.frame = frame;
		this.dimension = dimension;
		this.components = new ComponentList();
		this.panels = new HashMap<>();
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent var1) {
				UIPanel.this.onResized();
			}
			@Override
			public void componentMoved(ComponentEvent var1) {
				UIPanel.this.onMoved();
			}
		});
		this.addKeyListener(new InputListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				UIPanel.this.onKeyTyped(e.getKeyCode(), e.getKeyChar(), 0);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				UIPanel.this.onKeyTyped(e.getKeyCode(), e.getKeyChar(), 1);
			}
		});
		this.addMouseListener(new InputListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				UIPanel.this.onMousePressed(e.getButton(), e.getX(), e.getY(), 0);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				UIPanel.this.onMousePressed(e.getButton(), e.getX(), e.getY(), 1);
			}
		});
	}
	
	public void configurePanel() {
		this.setLayout(null);
		this.setPreferredSize(dimension);
	}
	
	public void onResized() {}

	public void onMoved() {}
	
	public void onMousePressed(int mouseButton, int x, int y, int status) {}
	
	public void onKeyTyped(int keyCode, char keyChar, int status) {}

	public void addComponent(String componentId, JComponent component) {
		this.components.addComponent(componentId, component);
	}
	
	public void removeComponent(String componentId) {
		this.components.removeComponent(componentId);
	}
	
	public JComponent getComponent(String componentId) {
		return this.components.getComponent(componentId);
	}
	
	public String getComponentId(JComponent component) {
		for(Entry<String, JComponent> entry : this.components.getComponents().entrySet()) {
			if((JComponent) entry.getValue() == component) {
				return (String) entry.getKey();
			}
		}
		return null;
	}
	
	public boolean hasComponent(String componentId) {
		return this.components.hasComponent(componentId);
	}
	
	public Set<UIPanel> getPanels() {
		Set<UIPanel> panels = new HashSet<>();
		this.panels.values().forEach(panel -> panels.add(panel));
		return panels;
	}
	
	public UIPanel getPanel(String panelName) {
		if(panels.containsKey(panelName)) {
			return panels.get(panelName);
		}
		return null;
	}
	
	public boolean hasPanel(String panelName) {
		return getPanel(panelName) != null;
	}
	
	public UIPanel addPanel(String panelName, Dimension dimension) {
		UIPanel panel = new UIPanel(frame, dimension);
		this.addPanel(panelName, panel);
		return panel;
	}
	
	public void addPanel(String panelName, UIPanel panel) {
		this.panels.put(panelName, panel);
	}
	
	public void removePanel(String panel) {
		this.panels.remove(panel);
	}
	
	public Map<String, UIPanel> getPanelsMap(){
		return panels;
	}
	
	public ComponentList getComponentList() {
		return components;
	}
	
	public UIFrame getFrame() {
		return frame;
	}
	
	public Dimension getDimension() {
		return dimension;
	}
	
}