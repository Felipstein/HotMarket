package com.hotmarket.frames;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComponent;

public class ComponentList {
	
	private HashMap<String, JComponent> components;
	
	public ComponentList(HashMap<String, JComponent> components) {
		this.components = components;
	}
	
	public ComponentList() {
		this(new HashMap<>());
	}
	
	public void addComponent(String componentId, JComponent component) {
		if(hasComponent(componentId)) {
			this.removeComponent(componentId);
		}
		this.components.put(componentId, component);
	}
	
	public void removeComponent(String componentId) {
		this.components.remove(componentId);
	}
	
	public boolean hasComponent(String componentId) {
		return components.containsKey(componentId);
	}
	
	public JComponent getComponent(String componentId) {
		return components.get(componentId);
	}
	
	public List<JComponent> getOnlyComponents() {
		List<JComponent> components = new ArrayList<>();
		this.components.values().forEach(component -> components.add(component));
		return components;
	}
	
	public HashMap<String, JComponent> getComponents() {
		return components;
	}
	
}