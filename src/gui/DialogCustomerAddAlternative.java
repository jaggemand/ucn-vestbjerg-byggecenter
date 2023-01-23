package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import controller.CustomerController;
import controller.ProductController;
import model.Customer;
import model.CustomerContainer;
import model.OrderLine;
import model.Product;
import model.ProductContainer;
import model.Customer.customerType;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DialogCustomerAddAlternative extends JDialog {
	private Customer newCustomer;
	private CustomerController cController;
	private DefaultTable table;
	private JTextField textFieldSearch;
	private boolean[] activeColumns;
	private JLabel lblRowCounter;
	private JScrollPane scrollPane;
	private JButton btnAdd;

	private ArrayList<Customer> list;
	private String[] columns = { "Telefon", "Navn", "Adresse", "Email"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogCustomerAddAlternative dialog = new DialogCustomerAddAlternative();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogCustomerAddAlternative() {
		
		cController = new CustomerController();
		newCustomer = null;
		list = new ArrayList<>();
		
		setResizable(false);
		setTitle("Tilføj kunde");
		setModal(true);
		setBounds(100, 100, 400, 310);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
		}
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			getContentPane().add(panel, BorderLayout.NORTH);
			{
				JLabel lblNewLabel = new JLabel("Filter");
				panel.add(lblNewLabel);
			}
			{
				textFieldSearch = new JTextField();
				panel.add(textFieldSearch);
				textFieldSearch.setColumns(10);
			}
			{
				JButton btnSearch = new JButton("Søg");
				btnSearch.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonSearchPressed();
					}
				});
				panel.add(btnSearch);
			}
		}
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			getContentPane().add(panel, BorderLayout.SOUTH);
			{
				btnAdd = new JButton("Tilføj");
				btnAdd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonAddPressed();
					}
				});
				panel.add(btnAdd);
			}
			{
				JButton btnCancel = new JButton("Afbryd");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonCancelPressed();
					}
				});
				panel.add(btnCancel);
			}
		}
		
		initTable();
		initWindow();
	}
	
//	private String[][] convertToStringArray(ArrayList<Customer> dataArrayList) {
//
//		int size = dataArrayList.size();
//		String[][] data = new String[size][10];
//		for (int i = 0; i < size; i++) {
//			Customer current = dataArrayList.get(i);
//			customerType type = current.getCustomerType();
//			String typeName = "";
//
//			switch (type) {
//			case BUSINESS:
//				typeName = "Erhverv";
//				break;
//
//			case PRIVATE:
//				typeName = "Privat";
//				break;
//			default:
//				typeName = "";
//			}
//			data[i][0] = typeName;
//			data[i][1] = current.getPhone();
//			data[i][2] = current.getName();
//			data[i][3] = current.getDeliveryAddress();
//			data[i][4] = current.getPaymentAddress();
//			data[i][5] = current.getPostcode();
//			data[i][6] = current.getCompanyName();
//			data[i][7] = current.getEmail();
//			data[i][8] = current.getCredit() + "";
//		}
//		return data;
//	}
	private void initWindow() {
		btnAdd.setEnabled(false);
	}
	private void initTable() {
		list = cController.getAllCustomers();
		String[][] data = null;
		table = new DefaultTable(data, columns);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table.getSelectedRow() != -1) {
					btnAdd.setEnabled(true);
				}
			}
		});
		scrollPane.setViewportView(table);
		//Only allows user to select single a single row at the time
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//User cannot change the ordering of the columns
		table.getTableHeader().setReorderingAllowed(false);
		//Make custom cellRenderer and give column 3, 4, 5 the new renderer
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
		updateTable();
	}
	
	private void updateTable() {
//		private String[] columns = { "Telefon", "Navn", "Adresse", "Email"};
		table.clear();
		for(Customer c : list) {
			String[] metaData = new String[4];
			metaData[0] = c.getPhone();
			if(c.getCustomerType().equals(customerType.PRIVATE)) {
				metaData[1] = c.getName();
			}
			else {
				metaData[1] = c.getCompanyName();
			}
			
			metaData[2] = c.getDeliveryAddress();
			metaData[3] = c.getEmail();
			table.addRow(metaData);
		}
	}
	
	private void buttonSearchPressed() {
		String search = textFieldSearch.getText().toLowerCase();
		if(search.isBlank()){
			//Show all
			list = cController.getAllCustomers();
		} else {
			//Show selection if a customer contains the searchString
			list = new ArrayList<>();
			for(Customer c : cController.getAllCustomers()) {
				boolean found = false;
				if(c.getCompanyName().toLowerCase().contains(search)) {
					found = true;
				} else if(c.getDeliveryAddress().toLowerCase().contains(search)) {
					found = true;
				} else if(c.getEmail().toLowerCase().contains(search)) {
					found = true;
				} else if(c.getName().toLowerCase().contains(search)) {
					found = true;
				} else if(c.getPaymentAddress().toLowerCase().contains(search)) {
					found = true;
				} else if(c.getPhone().toLowerCase().contains(search)) {
					found = true;
				} else if(c.getPostcode().toLowerCase().contains(search)) {
					found = true;
				}
				if(found) {
					list.add(c);
				}
			}
		}
		updateTable();
	}
	
	private void buttonAddPressed() {
		int row = table.getSelectedRow();
		if(row != -1) {
			//A row has been selected
			newCustomer = list.get(row);
			this.dispose();
		} else {
			//No row has been selected
		}
	}
	
	private void buttonCancelPressed() {
		this.dispose();
	}

	public Customer getNewCustomer() {
		return newCustomer;
	}
	
}
