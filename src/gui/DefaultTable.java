package gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class DefaultTable extends JTable {
	private DefaultTableModel tabelModel;
	/**
	 * Create the panel.
	 */
	public DefaultTable(String[][] data, String[] columns) {
		tabelModel = new DefaultTableModel(data, columns);
		setModel(tabelModel);
		setDefaultEditor(Object.class, null);
	}
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component c = super.prepareRenderer(renderer, row, column);
		if (!isRowSelected(row))
			c.setBackground(row % 2 == 0 ? getBackground() : Color.LIGHT_GRAY);
		return c;
	}
	public void setNewData(String[][] data) {
		tabelModel.setRowCount(0);
		if(data == null) {
			tabelModel.addRow(new Object[] {null});
		}
		int len = data.length;
		for(int i = 0;i < len;i++) {
			tabelModel.addRow(data[i]);
		}
	}
	public void deleteData() {
		int[] rows = this.getSelectedRows();
		if(rows.length != 0) {
			for(int i = rows.length-1; i>= 0;i--) {
				tabelModel.removeRow(i);
			}
		}
	}
	public int findElement() {
		return this.getSelectedRows()[0];
	}
	
}
