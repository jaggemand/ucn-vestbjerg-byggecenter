package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class DefaultTable extends JTable {
	private DefaultTableModel tabelModel;
	private int[] rows;
	private String[][] data;
	private String[] columns;
	private boolean[] visibleColumns;
	
	private JPopupMenu popUp;
	/**
	 * Create the panel.
	 */
	public DefaultTable(String[][] data, String[] columns) {
		this.data = data;
		this.columns = columns;
		boolean[] visibleColumns = new boolean[columns.length];
		for(int i = 0; i<columns.length;i++) {
			visibleColumns[i] = true;
		}
		this.visibleColumns = visibleColumns;
		initializeTable(data, columns, visibleColumns);
	}
	public DefaultTable(String[][] data, String[] columns, boolean[] visibleColumns) {
		this.data = data;
		this.columns = columns;
		this.visibleColumns = visibleColumns;
		initializeTable(data, columns, visibleColumns);
	}
	
	private void initializeTable(String[][] data, String[] columns, boolean[] visibleColumns) {
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
		setVisibleColumns(visibleColumns);
		popUp = new JPopupMenu();
		JMenuItem addColumn = new JMenuItem("Tilføj kolonne");
		popUp.add(addColumn);
		
		ActionListener alDetails = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				selectNewColumns();
			}	
		};
		addColumn.addActionListener(alDetails);
		
		
		MouseAdapter ma = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == 3) {
					showPopUp(e);
				}
			}
		
		};
		getTableHeader().addMouseListener(ma);
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
	public ArrayList<String> deleteData(String column, int[] columnsConfirm) {
		ArrayList<String> dataToDelete = new ArrayList<>();
		if(rows.length != 0) {			
			boolean check = deleteItems(rows.length, columnsConfirm);
			if(check == true) {
				for(int i = rows.length-1; i >= 0; i--) {
					dataToDelete.add(tabelModel.getValueAt(rows[i],this.getColumn(column).getModelIndex()).toString());
					tabelModel.removeRow(rows[i]);
				}
			}
		}
		else {
			GUIPopUpMessages.informationMessage("Marker én linje der skal slettes", "Bekræft slet");
		}
		return dataToDelete;
	}
	
	
	private boolean deleteItems(int amountQty, int[] columns) {
		
		StringBuilder products = new StringBuilder();
		
		for(int i = 0; i < amountQty; i++) {
			for(int x = 0; x < columns.length; x++) {
				if(columns[x] <= tabelModel.getColumnCount() && columns[x] >= 0) {
				products.append(tabelModel.getValueAt(rows[i],columns[x])+ "\t");
				}
			}
			
			products.append("\n");
		}
		
		JLabel label = new JLabel("Følgende elementer slettes:");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(label, BorderLayout.NORTH);
		
		JTextArea textArea = new JTextArea(products.toString());
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		panel.add(scrollPane, BorderLayout.WEST);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true); 
		scrollPane.setPreferredSize(new Dimension(350, 150));
		
		int input = JOptionPane.showOptionDialog(new JFrame(), panel, "Slet element",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null,
					new Object[] {Box.createHorizontalStrut(250), "OK", "Afbryd"}, JOptionPane.YES_OPTION);
					
					
		return input == 1;
	}
	public int findElement() {
		if(rows.length != 0) {
			return this.getSelectedRows()[0];
		}
		else {
			return -1;
		}
	}

	
	public void addRow(String[] data) {
		tabelModel.addRow(data);
	}
	public void clear() {
		tabelModel.setRowCount(0);
	}
	public void selectNewColumns() {
		ColumnSelecter cs = new ColumnSelecter(visibleColumns, columns, this);
		cs.setVisible(true);
	}
	public void setVisibleColumns(boolean[] newColumn) {
		visibleColumns = newColumn;
		for(int i = 0; i < visibleColumns.length; i++) {
			if(!visibleColumns[i]) {
				hideColumn(i);
			}else if (visibleColumns[i]){
				showColumn(i);
		}
		}
	}
	private void hideColumn(int index) {
		getColumnModel().getColumn(index).setMinWidth(0);
		getColumnModel().getColumn(index).setMaxWidth(0);
	}
	private void showColumn(int index) {
		getColumnModel().getColumn(index).setMinWidth(10);
		getColumnModel().getColumn(index).setMaxWidth(5000);
		getColumnModel().getColumn(index).setWidth(50);
		getColumnModel().getColumn(index).setPreferredWidth(0);
	}
	private void showPopUp(MouseEvent e) {
		if (e.isPopupTrigger()) {
			popUp.show(e.getComponent(),e.getX(), e.getY());
		}
	}

}