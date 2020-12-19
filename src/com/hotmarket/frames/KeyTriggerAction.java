package com.hotmarket.frames;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.hotmarket.frames.ui.UIFrame;

public class KeyTriggerAction {
	
	private Map<String, Integer[]> componentsKeys;
	public final int KEY_STATUS;
	
	{
		this.KEY_STATUS = UIFrame.KEY_PRESSED;
	}
	
	public KeyTriggerAction() {
		this(new HashMap<>());
	}
	
	public KeyTriggerAction(Map<String, Integer[]> componentsKeys) {
		this.componentsKeys = componentsKeys == null ? new HashMap<>() : componentsKeys;
	}
	
	public List<String> getComponentsId(int key) {
		List<String> componentsId = new ArrayList<>();
		for(Entry<String, Integer[]> entry : this.componentsKeys.entrySet()) {
			int[] keys = this.toReservedTerm((Integer[]) entry.getValue());
			for(int k : keys) {
				if(k == key) {
					componentsId.add((String) entry.getKey());
				}
			}
		}
		return componentsId;
	}
	
	public int[] getKeys(String componentId) {
		return this.toReservedTerm(componentsKeys.get(componentId));
	}
	
	public boolean hasKeyRegistred(int key) {
		for(Integer[] keys : this.componentsKeys.values()) {
			for(Integer k : keys) {
				if(k == key) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean hasComponentId(String componentId) {
		return this.componentsKeys.containsKey(componentId);
	}
	
	private int[] toReservedTerm(Integer[] array) {
		int[] k = new int[array.length];
		for(int i = 0; i < array.length; ++i) {
			k[i] = array[i];
		}
		return k;
	}
	
	private Integer[] toClassTerm(int[] array) {
		Integer[] k = new Integer[array.length];
		for(int i = 0; i < array.length; ++i) {
			k[i] = array[i];
		}
		return k;
	}
	
	public void addKey(String componentId, int key) {
		int[] keys;
		if(hasComponentId(componentId)) {
			int[] keys2 = this.getKeys(componentId);
			keys = new int[keys2.length + 1];
			for(int i = 0; i < keys.length; ++i) {
				if(i == keys2.length) {
					keys[i] = key;
				} else {
					keys[i] = keys2[i];
				}
			}
		} else {
			keys = new int[] {key};
		}
		this.componentsKeys.put(componentId, toClassTerm(keys));
	}
	
	public Map<String, Integer[]> getComponentsKeys() {
		return componentsKeys;
	}
	
}