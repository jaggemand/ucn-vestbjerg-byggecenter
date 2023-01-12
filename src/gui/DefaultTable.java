package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import controller.ProductController;
import model.ProductContainer;

public class DefaultTable extends JTable {
	private DefaultTableModel tabelModel;
	private int[] rows;
	
	/**
	 * Create the panel.
	 */
	public DefaultTable(String[][] data, String[] columns) {
		tabelModel = new DefaultTableModel(data, columns);
		setModel(tabelModel);
		setDefaultEditor(Object.class, null);
		rows = new int[0];
		
		getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				rows = getSelectedRows();
			}
		});
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
		for(int i = 0; i < len; i++) {
			tabelModel.addRow(data[i]);
		}
	}
	public void deleteData() {
		if(rows.length != 0) {
			
			ProductController pC = new ProductController();
			ArrayList<String> data = selectedProductID();
			
			boolean check = deleteItems(rows.length);
			if(check == true) {
				for(int i = rows.length-1; i>= 0;i--) {
					tabelModel.removeRow(rows[i]);
					pC.removeProduct(data.get(i));
				}
			}
		}
	}
	
	private boolean deleteItems(int amountQty) {
		
		StringBuilder products = new StringBuilder();
		
		for(int i = 0; i < amountQty; i++) {
			products.append(tabelModel.getValueAt(rows[i],0));
			products.append("\t" + tabelModel.getValueAt(rows[i],1));
			products.append("\n");
		}
		JLabel label = new JLabel("Vil du gerne slette disse produkter");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(label, BorderLayout.NORTH);
		
		JTextArea textArea = new JTextArea(products.toString());
		JScrollPane scrollPane = new JScrollPane(textArea);
		panel.add(scrollPane, BorderLayout.WEST);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true); 
		scrollPane.setPreferredSize(new Dimension(350, 150));
		
		int input = JOptionPane.showOptionDialog(new JFrame(), panel, "Afslut program",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null,
					new Object[] { "Ja", "Nej" }, JOptionPane.YES_OPTION);
		
		return input == 0;
	}
	
	public int findElement() {
		return this.getSelectedRows()[0];
	}
	
	public void addRow(String[] data) {
		tabelModel.addRow(data);
	}
	
	public ArrayList<String> selectedProductID() {
		String returnString = "";
		ArrayList<String> returnArr = new ArrayList<>();
		int amountQty = rows.length; 
		
		for(int i = 0; i < amountQty; i++) {
			returnString = (String) tabelModel.getValueAt(rows[i],0);
			returnArr.add(returnString);
			System.out.println(returnArr.get(i));
		}
		return returnArr;
	}
	
}