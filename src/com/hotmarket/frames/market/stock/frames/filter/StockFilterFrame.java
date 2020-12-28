package com.hotmarket.frames.market.stock.frames.filter;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JComponent;

import com.hotmarket.client.items.Item;
import com.hotmarket.client.items.ItemList;
import com.hotmarket.files.FileItems;
import com.hotmarket.frames.market.stock.StockFrame;
import com.hotmarket.frames.market.stock.StockItemsTable;
import com.hotmarket.frames.market.stock.frames.actions.ColorWarnKeyPressAction;
import com.hotmarket.frames.market.stock.frames.actions.DisableAllComponentsPressAction;
import com.hotmarket.frames.market.stock.frames.filter.Setting.ComponentType;
import com.hotmarket.frames.market.stock.frames.filter.Setting.NameSearchOptions;
import com.hotmarket.frames.market.stock.frames.filter.Setting.SortingOptions;
import com.hotmarket.frames.optionpanes.JOptionPanesUtil;
import com.hotmarket.frames.ui.UIFrame;
import com.hotmarket.frames.ui.UIPanel;
import com.hotmarket.frames.ui.components.NamedUIRadioButton;
import com.hotmarket.frames.ui.components.UIButton;
import com.hotmarket.frames.ui.components.UIButtonGroup;
import com.hotmarket.frames.ui.components.UICheckBox;
import com.hotmarket.frames.ui.components.UIRadioButton;
import com.hotmarket.frames.ui.components.UITextField;
import com.hotmarket.frames.ui.components.UITextFieldWithLabel;
import com.hotmarket.utils.alignments.RadioButtonAlignmentX;
import com.hotmarket.utils.alignments.RadioButtonAlignmentY;

public class StockFilterFrame extends UIFrame {

	private static final long serialVersionUID = -5713401793785864260L;
	
	private final StockFrame frame;
	
	public final Setting<Integer> ID_VALUE;
	public final Setting<String> NAME_VALUE;
	public final Setting<NameSearchOptions> NAME_SEARCH_OPTIONS;
	public final Setting<Boolean> NAME_IGNORE_CASES;
	public final Setting<Integer> MIN_STOCK_VALUE;
	public final Setting<Integer> MAX_STOCK_VALUE;
	public final Setting<Float> MIN_PRICE_VALUE;
	public final Setting<Float> MAX_PRICE_VALUE;
	public final Setting<Float> MIN_DISCOUNT_VALUE;
	public final Setting<Float> MAX_DISCOUNT_VALUE;
	public final Setting<SortingOptions> SORTING_OPTIONS;
	
	public Set<UITextField> textFields;
	public Set<UICheckBox> checkBoxs;
	public Set<UIRadioButton> radioButtons;
	public Set<JComponent> componentsToDisable;
	
	public StockFilterFrame(StockFrame frame) {
		super("Filtro", 500, 600);
		this.frame = frame;
		this.configureFrame(false, false);
		this.textFields = new HashSet<>();
		this.checkBoxs = new HashSet<>();
		this.radioButtons = new HashSet<>();
		this.componentsToDisable = new HashSet<>();
		this.ID_VALUE = new Setting<>(ComponentType.TEXT_FIELD);
		this.NAME_VALUE = new Setting<>(ComponentType.TEXT_FIELD);
		this.NAME_SEARCH_OPTIONS = new Setting<>(ComponentType.RADIO_BUTTON);
		this.NAME_IGNORE_CASES = new Setting<>(ComponentType.CHECK_BOX);
		this.MIN_STOCK_VALUE = new Setting<>(ComponentType.TEXT_FIELD);
		this.MAX_STOCK_VALUE = new Setting<>(ComponentType.TEXT_FIELD);
		this.MIN_PRICE_VALUE = new Setting<>(ComponentType.TEXT_FIELD);
		this.MAX_PRICE_VALUE = new Setting<>(ComponentType.TEXT_FIELD);
		this.MIN_DISCOUNT_VALUE = new Setting<>(ComponentType.TEXT_FIELD);
		this.MAX_DISCOUNT_VALUE = new Setting<>(ComponentType.TEXT_FIELD);
		this.SORTING_OPTIONS = new Setting<>(ComponentType.RADIO_BUTTON);
		this.configureElements();
	}
	
	@SuppressWarnings("unused")
	private void configureElements() {
		UIPanel p = this.getContentPane().addPanel("settings", 5, 5, getContentPane().getWidth() - 10, getContentPane().getHeight() - 10);
		p.configurePanel();
		p.setPerfectBorder("Definições do Filtro");
		
		UIButton clear = new UIButton("Limpar campos", p.getWidth() - 140, 26, 125, 25, e -> this.clear());
		p.addComponent("b-clear", clear);
		
		UITextFieldWithLabel idField = new UITextFieldWithLabel(p, "ID:", false, 10, 35, 100, 20);
		idField.addKeyPressedAction(new DisableAllComponentsPressAction(this, idField, false).ignoreNull(true));
		p.addComponent("f-id", idField);
		this.textFields.add(idField);
		
		nameSettings: {
			UIPanel nameSettings = p.addPanel("namesettings", 10, 70, p.getWidth() - 20, 87);
			nameSettings.configurePanel();
			nameSettings.setPerfectBorder(null);
			this.componentsToDisable.add(nameSettings);
			
			UITextFieldWithLabel nameField = new UITextFieldWithLabel(nameSettings, "Nome:", true, 10, 10, nameSettings.getWidth() - 20, 25);
			nameSettings.addComponent("f-name", nameField);
			this.textFields.add(nameField);
			this.componentsToDisable.add(nameField);
			this.componentsToDisable.add(nameField.getLabel());
			
			NamedUIRadioButton nameRB1 = new NamedUIRadioButton("rb-name1", "Contendo", 0, 60);
			NamedUIRadioButton nameRB2 = new NamedUIRadioButton("rb-name2", "Iniciando com", 0, 60);
			NamedUIRadioButton nameRB3 = new NamedUIRadioButton("rb-name3", "Procurar por", 0, 60);
			
			nameRB1.setSelected(true);
			
			RadioButtonAlignmentX align = new RadioButtonAlignmentX(nameRB1, nameRB2, nameRB3);
			align.align(10, 4).forEach(button -> nameSettings.addComponent(((NamedUIRadioButton) button).getID(), button));
			UIButtonGroup.createButtonGroup(nameRB1, nameRB2, nameRB3);
			
			this.radioButtons.add(nameRB1);
			this.radioButtons.add(nameRB2);
			this.radioButtons.add(nameRB3);
			this.componentsToDisable.add(nameRB1);
			this.componentsToDisable.add(nameRB2);
			this.componentsToDisable.add(nameRB3);
			
			UICheckBox nameCB = new UICheckBox("Ignorar diferença de MAIÚS./minús.", align.getTotalWidth(4) + 5, 60);
			nameSettings.addComponent("cb-name", nameCB);
			nameCB.setSelected(true);
			this.checkBoxs.add(nameCB);
			this.componentsToDisable.add(nameCB);
		}
		
		stockSettings: {
			UIPanel stockSettings = p.addPanel("stocksettings", 10, 170, p.getWidth() - 20, 65);
			stockSettings.configurePanel();
			stockSettings.setPerfectBorder(null);
			this.componentsToDisable.add(stockSettings);
			
			UITextFieldWithLabel minField = new UITextFieldWithLabel(stockSettings, "Quantidade mínima no estoque:", true, 10, 10, stockSettings.getWidth() / 2 - 20, 25);
			minField.addKeyPressedAction(new ColorWarnKeyPressAction(minField, false).ignoreNull(true));
			stockSettings.addComponent("f-stockmin", minField);
			this.textFields.add(minField);
			this.componentsToDisable.add(minField);
			this.componentsToDisable.add(minField.getLabel());
			
			UITextFieldWithLabel maxField = new UITextFieldWithLabel(stockSettings, "Quantidade máxima no estoque:", true, stockSettings.getWidth() / 2 + 10, 10, stockSettings.getWidth() / 2 - 20, 25);
			maxField.addKeyPressedAction(new ColorWarnKeyPressAction(maxField, false).ignoreNull(true));
			stockSettings.addComponent("f-stockmax", maxField);
			this.textFields.add(maxField);
			this.componentsToDisable.add(maxField);
			this.componentsToDisable.add(maxField.getLabel());
		}
		
		priceAndDiscountSettings: {
			UIPanel pdSettings = p.addPanel("pdsettings", 10, 248, p.getWidth() - 20, 116);
			pdSettings.configurePanel();
			pdSettings.setPerfectBorder(null);
			this.componentsToDisable.add(pdSettings);
			
			UITextFieldWithLabel minPriceField = new UITextFieldWithLabel(pdSettings, "Quantidade mínima do preço:", true, 10, 10, pdSettings.getWidth() / 2 - 20, 25);
			minPriceField.addKeyPressedAction(new ColorWarnKeyPressAction(minPriceField, false).ignoreNull(true));
			pdSettings.addComponent("f-pricemin", minPriceField);
			this.textFields.add(minPriceField);
			this.componentsToDisable.add(minPriceField);
			this.componentsToDisable.add(minPriceField.getLabel());
			
			UITextFieldWithLabel maxPriceField = new UITextFieldWithLabel(pdSettings, "Quantidade máxima do preço:", true, pdSettings.getWidth() / 2 + 10, 10, pdSettings.getWidth() / 2 - 20, 25);
			maxPriceField.addKeyPressedAction(new ColorWarnKeyPressAction(maxPriceField, false).ignoreNull(true));
			pdSettings.addComponent("f-pricemax", maxPriceField);
			this.textFields.add(maxPriceField);
			this.componentsToDisable.add(maxPriceField);
			this.componentsToDisable.add(maxPriceField.getLabel());
			
			UITextFieldWithLabel minDiscountPrice = new UITextFieldWithLabel(pdSettings, "Quantidade mínima de desconto:", true, 10, 62, pdSettings.getWidth() / 2 - 20, 25);
			minDiscountPrice.addKeyPressedAction(new ColorWarnKeyPressAction(minDiscountPrice, false).ignoreNull(true));
			pdSettings.addComponent("f-discountmin", minDiscountPrice);
			this.textFields.add(minDiscountPrice);
			this.componentsToDisable.add(minDiscountPrice);
			this.componentsToDisable.add(minDiscountPrice.getLabel());
			
			UITextFieldWithLabel maxDiscountPrice = new UITextFieldWithLabel(pdSettings, "Quantidade máxima de desconto:", true, pdSettings.getWidth() / 2 + 10, 62, pdSettings.getWidth() / 2 - 20, 25);
			maxDiscountPrice.addKeyPressedAction(new ColorWarnKeyPressAction(maxDiscountPrice, false).ignoreNull(true));
			pdSettings.addComponent("f-discountmax", maxDiscountPrice);
			this.textFields.add(maxDiscountPrice);
			this.componentsToDisable.add(maxDiscountPrice);
			this.componentsToDisable.add(maxDiscountPrice.getLabel());
		}
		
		otherOptions: {
			UIPanel orderSettings = p.addPanel("ordersettings", 10, 377, p.getWidth() / 2 - 10, p.getHeight() - 390);
			orderSettings.configurePanel();
			orderSettings.setPerfectBorder("Ordernar por:");
			this.componentsToDisable.add(orderSettings);
			
			List<UIRadioButton> rbs = new ArrayList<>();
			rbs.add(new NamedUIRadioButton("rb-order1", "ID crescente", 10, 0));
			rbs.add(new NamedUIRadioButton("rb-order2", "ID decrescente", 10, 0));
			rbs.add(new NamedUIRadioButton("rb-order3", "Ordem alfabética", 10, 0));
			rbs.add(new NamedUIRadioButton("rb-order4", "Quantidade crescente", 10, 0));
			rbs.add(new NamedUIRadioButton("rb-order5", "Quantidade decrescente", 10, 0));
			rbs.add(new NamedUIRadioButton("rb-order6", "Preço crescente", 10, 0));
			rbs.add(new NamedUIRadioButton("rb-order7", "Preço decrescente", 10, 0));
			rbs.add(new NamedUIRadioButton("rb-order8", "Desconto crescente", 10, 0));
			rbs.add(new NamedUIRadioButton("rb-order9", "Desconto decrescente", 10, 0));
			
			rbs.forEach(rb -> orderSettings.addComponent(((NamedUIRadioButton) rb).getID(), rb));
			rbs.forEach(rb -> radioButtons.add(rb));
			rbs.forEach(rb -> componentsToDisable.add(rb));
			
			RadioButtonAlignmentY align = new RadioButtonAlignmentY(rbs);
			align.align(17, 6);
			UIButtonGroup.createButtonGroup(rbs);
			
			rbs.get(0).setSelected(true);
		}
		int height = p.getHeight() - 395;
		UIButton filter = new UIButton("Filtrar", 10 + p.getWidth() / 2, 395, p.getWidth() / 2 - 25, height / 2 - 25, e -> this.filter());
		UIButton cancel = new UIButton("Cancelar", 10 + p.getWidth() / 2, 10 + 380 + height / 2, p.getWidth() / 2 - 25, height / 2 - 25, e -> this.dispose());
		
		p.addComponent("b-filter", filter);
		p.addComponent("b-cancel", cancel);
	}
	
	public void clear() {
		this.textFields.forEach(tf -> tf.setText(""));
		this.textFields.forEach(tf -> tf.setBackground(Color.white));
		this.textFields.forEach(tf -> ((UITextFieldWithLabel) tf).getLabel().setForeground(Color.black));
		this.checkBoxs.forEach(cb -> cb.setSelected(false));
		this.componentsToDisable.forEach(c -> c.setEnabled(true));
		((UICheckBox) this.getContentPane().getPanel("settings").getPanel("namesettings").getComponent("cb-name")).setSelected(true);
		this.ignoreSettings();
	}
	
	private String getValueOfTextField(boolean inSettingsPanel, String panel, String textFieldId) {
		if(inSettingsPanel) {
			return ((UITextField) getContentPane().getPanel("settings").getPanel(panel).getComponent(textFieldId)).getText();
		} else {
			return ((UITextField) getContentPane().getPanel(panel).getComponent(textFieldId)).getText();
		}
	}
	
	private boolean getValueOfCheckBox(boolean inSettingsPanel, String panel, String checkBoxId) {
		if(inSettingsPanel) {
			return ((UICheckBox) getContentPane().getPanel("settings").getPanel(panel).getComponent(checkBoxId)).isSelected();
		} else {
			return ((UICheckBox) getContentPane().getPanel(panel).getComponent(checkBoxId)).isSelected();
		}
	}
	
	private boolean getValueOfRadioButton(boolean inSettingsPanel, String panel, String radioButtonId) {
		if(inSettingsPanel) {
			return ((UIRadioButton) getContentPane().getPanel("settings").getPanel(panel).getComponent(radioButtonId)).isSelected();
		} else {
			return ((UIRadioButton) getContentPane().getPanel(panel).getComponent(radioButtonId)).isSelected();
		}
	}
	
	public void ignoreSettings() {
		this.ID_VALUE.setIgnoreValue(false);
		this.NAME_VALUE.setIgnoreValue(false);
		this.NAME_SEARCH_OPTIONS.setIgnoreValue(false);
		this.NAME_IGNORE_CASES.setIgnoreValue(false);
		this.MIN_STOCK_VALUE.setIgnoreValue(false);
		this.MAX_STOCK_VALUE.setIgnoreValue(false);
		this.MIN_PRICE_VALUE.setIgnoreValue(false);
		this.MAX_PRICE_VALUE.setIgnoreValue(false);
		this.MIN_DISCOUNT_VALUE.setIgnoreValue(false);
		this.MAX_DISCOUNT_VALUE.setIgnoreValue(false);
		this.SORTING_OPTIONS.setIgnoreValue(false);
	}
	
	public boolean updateSettings() {
		this.ignoreSettings();
		String idString = getValueOfTextField(false, "settings", "f-id");
		if(idString.trim().isEmpty()) {
			this.ID_VALUE.setIgnoreValue(true);
		} else {
			int id = 0;
			try {
				id = Integer.parseInt(idString);
				if(id <= 0) {
					JOptionPanesUtil.anErrorExcepted("O valor \"ID\" não pode ser negativo ou igual a zero.");
					return false;
				}
			} catch(NumberFormatException e) {
				JOptionPanesUtil.anErrorExcepted("O valor \"ID\" aceita apenas valores númericos.");
				return false;
			}
			this.ID_VALUE.setValue(id);
		}
		String name = getValueOfTextField(true, "namesettings", "f-name");
		if(name.trim().isEmpty()) {
			this.NAME_VALUE.setIgnoreValue(true);
		} else {
			this.NAME_VALUE.setValue(name);
		}
		if(getValueOfRadioButton(true, "namesettings", "rb-name1")) {
			this.NAME_SEARCH_OPTIONS.setValue(NameSearchOptions.CONTAINS);
		} else if(getValueOfRadioButton(true, "namesettings", "rb-name2")) {
			this.NAME_SEARCH_OPTIONS.setValue(NameSearchOptions.STARTS_WITH);
		} else if(getValueOfRadioButton(true, "namesettings", "rb-name3")) {
			this.NAME_SEARCH_OPTIONS.setValue(NameSearchOptions.EQUALS);
		}
		this.NAME_IGNORE_CASES.setValue(getValueOfCheckBox(true, "namesettings", "cb-name"));
		String minStockString = getValueOfTextField(true, "stocksettings", "f-stockmin");
		String maxStockString = getValueOfTextField(true, "stocksettings", "f-stockmax");
		if(minStockString.trim().isEmpty()) {
			this.MIN_STOCK_VALUE.setIgnoreValue(true);
			this.MIN_STOCK_VALUE.setValue(0);
		} else {
			int minStock = 0;
			try {
				minStock = Integer.parseInt(minStockString);
				if(minStock < 0) {
					JOptionPanesUtil.anErrorExcepted("O valor \"Quantidade mínima no estoque\" não pode ser negativo.");
					return false;
				}
			} catch(NumberFormatException e) {
				JOptionPanesUtil.anErrorExcepted("O valor \"Quantidade mínima no estoque\" aceita apenas valores númericos.");
				return false;
			}
			this.MIN_STOCK_VALUE.setValue(minStock);
		}
		if(maxStockString.trim().isEmpty()) {
			this.MAX_STOCK_VALUE.setIgnoreValue(true);
			this.MAX_STOCK_VALUE.setValue(0);
		} else {
			int maxStock = 0;
			try {
				maxStock = Integer.parseInt(maxStockString);
				if(maxStock < 0) {
					JOptionPanesUtil.anErrorExcepted("O valor \"Quantidade máxima no estoque\" não pode ser negativo.");
					return false;
				}
			} catch(NumberFormatException e) {
				JOptionPanesUtil.anErrorExcepted("O valor \"Quantidade máxima no estoque\" aceita apenas valores númericos.");
				return false;
			}
			this.MAX_STOCK_VALUE.setValue(maxStock);
		}
		String minPriceString = getValueOfTextField(true, "pdsettings", "f-pricemin");
		String maxPriceString = getValueOfTextField(true, "pdsettings", "f-pricemax");
		String minDiscountString = getValueOfTextField(true, "pdsettings", "f-discountmin");
		String maxDiscountString = getValueOfTextField(true, "pdsettings", "f-discountmax");
		if(minPriceString.trim().isEmpty()) {
			this.MIN_PRICE_VALUE.setIgnoreValue(true);
			this.MIN_PRICE_VALUE.setValue(0.0f);
		} else {
			float minPrice = 0;
			try {
				minPrice = Float.parseFloat(minPriceString);
				if(minPrice < 0) {
					JOptionPanesUtil.anErrorExcepted("O valor \"Quantidade mínima do preço\" não pode ser negativo.");
					return false;
				}
			} catch(NumberFormatException e) {
				JOptionPanesUtil.anErrorExcepted("O valor \"Quantidade mínima do preço\" aceita apenas valores númericos.");
				return false;
			}
			this.MIN_PRICE_VALUE.setValue(minPrice);
		}
		if(maxPriceString.trim().isEmpty()) {
			this.MAX_PRICE_VALUE.setIgnoreValue(true);
			this.MAX_PRICE_VALUE.setValue(0.0f);
		} else {
			float maxPrice = 0;
			try {
				maxPrice = Float.parseFloat(maxPriceString);
				if(maxPrice < 0) {
					JOptionPanesUtil.anErrorExcepted("O valor \"Quantidade máxima do preço\" não pode ser negativo.");
					return false;
				}
			} catch(NumberFormatException e) {
				JOptionPanesUtil.anErrorExcepted("O valor \"Quantidade máxima do preço\" aceita apenas valores númericos.");
				return false;
			}
			this.MAX_PRICE_VALUE.setValue(maxPrice);
		}
		if(minDiscountString.trim().isEmpty()) {
			this.MIN_DISCOUNT_VALUE.setIgnoreValue(true);
			this.MIN_DISCOUNT_VALUE.setValue(0.0f);
		} else {
			float minDiscount = 0;
			try {
				minDiscount = Float.parseFloat(minDiscountString);
				if(minDiscount < 0) {
					JOptionPanesUtil.anErrorExcepted("O valor \"Quantidade mínima de desconto\" não pode ser negativo.");
					return false;
				}
			} catch(NumberFormatException e) {
				JOptionPanesUtil.anErrorExcepted("O valor \"Quantidade mínima de desconto\" aceita apenas valores númericos.");
				return false;
			}
			this.MIN_DISCOUNT_VALUE.setValue(minDiscount);
		}
		if(maxDiscountString.trim().isEmpty()) {
			this.MAX_DISCOUNT_VALUE.setIgnoreValue(true);
			this.MAX_DISCOUNT_VALUE.setValue(0.0f);
		} else {
			float maxDiscount = 0;
			try {
				maxDiscount = Float.parseFloat(maxDiscountString);
				if(maxDiscount < 0) {
					JOptionPanesUtil.anErrorExcepted("O valor \"Quantidade máxima de desconto\" não pode ser negativo.");
					return false;
				}
			} catch(NumberFormatException e) {
				JOptionPanesUtil.anErrorExcepted("O valor \"Quantidade máxima de desconto\" aceita apenas valores númericos.");
				return false;
			}
			this.MAX_DISCOUNT_VALUE.setValue(maxDiscount);
		}
		if(getValueOfRadioButton(true, "ordersettings", "rb-order1")) {
			this.SORTING_OPTIONS.setValue(SortingOptions.ID_INC);
		} else if(getValueOfRadioButton(true, "ordersettings", "rb-order2")) {
			this.SORTING_OPTIONS.setValue(SortingOptions.ID_DEC);
		} else if(getValueOfRadioButton(true, "ordersettings", "rb-order3")) {
			this.SORTING_OPTIONS.setValue(SortingOptions.ALPHABETICAL_ORDER);
		} else if(getValueOfRadioButton(true, "ordersettings", "rb-order4")) {
			this.SORTING_OPTIONS.setValue(SortingOptions.STOCK_INC);
		} else if(getValueOfRadioButton(true, "ordersettings", "rb-order5")) {
			this.SORTING_OPTIONS.setValue(SortingOptions.STOCK_DEC);
		} else if(getValueOfRadioButton(true, "ordersettings", "rb-order6")) {
			this.SORTING_OPTIONS.setValue(SortingOptions.PRICE_INC);
		} else if(getValueOfRadioButton(true, "ordersettings", "rb-order7")) {
			this.SORTING_OPTIONS.setValue(SortingOptions.PRICE_DEC);
		} else if(getValueOfRadioButton(true, "ordersettings", "rb-order8")) {
			this.SORTING_OPTIONS.setValue(SortingOptions.DISCOUNT_INC);
		} else if(getValueOfRadioButton(true, "ordersettings", "rb-order9")) {
			this.SORTING_OPTIONS.setValue(SortingOptions.DISCOUNT_DEC);
		}
		return true;
	}
	
	public void filter() {
		if(!updateSettings()) {
			return;
		}
		ItemList defaultItems = new ItemList(FileItems.archive.getItems(), false);
		ItemList items = new ItemList(false);
		if(ID_VALUE.ignoreValue()) {
			for(Item item : defaultItems.getItems()) {
				if(!NAME_VALUE.ignoreValue()) {
					String itemName = NAME_IGNORE_CASES.getValue() ? item.getName().toLowerCase() : item.getName();
					String valueName = NAME_IGNORE_CASES.getValue() ? NAME_VALUE.getValue().toLowerCase() : NAME_VALUE.getValue();
					if(NAME_SEARCH_OPTIONS.getValue() == NameSearchOptions.CONTAINS) {
						if(itemName.contains(valueName)) {
							items.addItem(item);
						}
					} else if(NAME_SEARCH_OPTIONS.getValue() == NameSearchOptions.EQUALS) {
						if(itemName.equals(valueName)) {
							items.addItem(item);
						}
					} else if(NAME_SEARCH_OPTIONS.getValue() == NameSearchOptions.STARTS_WITH) {
						if(itemName.startsWith(valueName)) {
							items.addItem(item);
						}
					}
					continue;
				}
				if(!MIN_STOCK_VALUE.ignoreValue()) {
					if(item.getAmountStock() < MIN_STOCK_VALUE.getValue()) {
						continue;
					}
				}
				if(!MAX_STOCK_VALUE.ignoreValue()) {
					if(item.getAmountStock() > MAX_STOCK_VALUE.getValue()) {
						continue;
					}
				}
				if(!MIN_PRICE_VALUE.ignoreValue()) {
					if(item.getPrice() < MIN_PRICE_VALUE.getValue()) {
						continue;
					}
				}
				if(!MAX_PRICE_VALUE.ignoreValue()) {
					if(item.getPrice() > MAX_PRICE_VALUE.getValue()) {
						continue;
					}
				}
				if(!MIN_DISCOUNT_VALUE.ignoreValue()) {
					if(item.getDiscount() < MIN_DISCOUNT_VALUE.getValue()) {
						continue;
					}
				}
				if(!MAX_DISCOUNT_VALUE.ignoreValue()) {
					if(item.getDiscount() > MAX_DISCOUNT_VALUE.getValue()) {
						continue;
					}
				}
				items.addItem(item);
			}
			switch(SORTING_OPTIONS.getValue()) {
				case ID_INC:
					
					break;
				case ID_DEC:
					
					break;
				case ALPHABETICAL_ORDER:
					
					break;
				case STOCK_INC:
					
					break;
				case STOCK_DEC:
					
					break;
				case PRICE_INC:
					
					break;
				case PRICE_DEC:
					
					break;
				case DISCOUNT_INC:
					
					break;
				case DISCOUNT_DEC:
					
					break;
			}
		} else {
			Item item = defaultItems.getItem(ID_VALUE.getValue());
			if(item != null) {
				items.addItem(item);
			}
		}
		this.frame.bottomPanel.setTable(new StockItemsTable(frame.bottomPanel, items));
		this.frame.topPanel.startFilter();
		this.dispose();
	}
	
	@Override
	public void onClosing() {
		this.dispose();
	}
	
	public StockFrame getFrame() {
		return frame;
	}
	
}