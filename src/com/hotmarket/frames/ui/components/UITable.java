package com.hotmarket.frames.ui.components;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.hotmarket.frames.ui.UIPanel;

public class UITable extends JTable {
	
	private static final long serialVersionUID = -1843709782900525465L;
	
	private UIPanel panel;
	
	protected boolean cellEditable;
	
	{
		this.cellEditable = true;
	}
	
	public UITable(int x, int y, int width, int height) {
		this(null, x, y, width, height);
	}
	
	public UITable(UIPanel panel, int x, int y, int width, int height) {
		super();
		this.panel = panel;
		this.setBounds(x, y, width, height);
	}
	
	public void resizeColumns() {
		if(autoResizeMode != AUTO_RESIZE_OFF) {
			return;
		}
		for(int i = 0; i < getColumnCount(); i++) {
			TableColumn column = columnModel.getColumn(i);
			if(!column.getResizable()) {
				continue;
			}
			Object value = column.getHeaderValue();
			TableCellRenderer cellRenderer = column.getCellRenderer() == null ? 
					tableHeader.getDefaultRenderer() : column.getCellRenderer();
			int headerWidth = cellRenderer.getTableCellRendererComponent(this, value, false, false, -1, i).getPreferredSize().width;
			int rowWidth = 0;
			int maxWidth = column.getMaxWidth();
			for(int j = 0; j < getRowCount(); j++) {
				TableCellRenderer cellRenderer2 = getCellRenderer(j, i);
				Component component = prepareRenderer(cellRenderer2, j, i);
				rowWidth = rowWidth > component.getPreferredSize().width + getIntercellSpacing().width ? rowWidth : component.getPreferredSize().width + getIntercellSpacing().width;
				if(rowWidth >= maxWidth) {
					break;
				}
			}
			int preferredWidth = 6 + (headerWidth > rowWidth ? headerWidth : rowWidth);
			tableHeader.setResizingColumn(column);
			column.setWidth(preferredWidth);
		}
	}
	
	public UIPanel getPanel() {
		return panel;
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return cellEditable;
	}
	
	public void setCellEditable(boolean cellEditable) {
		this.cellEditable = cellEditable;
	}
	
}