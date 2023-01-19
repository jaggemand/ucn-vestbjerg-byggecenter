package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CustomerController;
import model.Customer;
import model.Customer.customerType;
import model.CustomerContainer;
import model.Product;
import model.ProductContainer;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class CustomerOverview extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private String[] columns;
	private boolean[] activeColumns;
	private JLabel lblRowCounter;
	private JButton btnSearch;
	private JButton btnReset;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDetails;
	private JButton btnDelete;
	private JCheckBox chckBoxPrivate;
	private JCheckBox chckBoxBusiness;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerOverview frame = new CustomerOverview();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustomerOverview() {
		setTitle("Kundeoversigt");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 923, 644);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 790, 20, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 21, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		lblRowCounter = new JLabel("Antal: "+ table.getRowCount());
		GridBagConstraints gbc_lblRowCounter = new GridBagConstraints();
		gbc_lblRowCounter.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRowCounter.insets = new Insets(0, 0, 0, 5);
		gbc_lblRowCounter.gridx = 0;
		gbc_lblRowCounter.gridy = 0;
		panel.add(lblRowCounter, gbc_lblRowCounter);

		JButton btnCancel = new JButton("Afbryd");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnCancel.gridx = 3;
		gbc_btnCancel.gridy = 0;
		panel.add(btnCancel, gbc_btnCancel);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.EAST);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 85, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 21, 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		btnAdd = new JButton("Tilføj");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCustomer();
			}
		});
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.anchor = GridBagConstraints.NORTH;
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 1;
		panel_1.add(btnAdd, gbc_btnAdd);

		btnEdit = new JButton("Rediger");
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEdit.insets = new Insets(0, 0, 5, 0);
		gbc_btnEdit.anchor = GridBagConstraints.NORTH;
		gbc_btnEdit.gridx = 0;
		gbc_btnEdit.gridy = 2;
		panel_1.add(btnEdit, gbc_btnEdit);

		btnDetails = new JButton("Detaljer");
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewCustomerDetails();
			}
		});
		GridBagConstraints gbc_btnDetails = new GridBagConstraints();
		gbc_btnDetails.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDetails.insets = new Insets(0, 0, 5, 0);
		gbc_btnDetails.anchor = GridBagConstraints.NORTH;
		gbc_btnDetails.gridx = 0;
		gbc_btnDetails.gridy = 3;
		panel_1.add(btnDetails, gbc_btnDetails);

		btnDelete = new JButton("Slet");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteCustomer();
			}
		});
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.anchor = GridBagConstraints.NORTH;
		gbc_btnDelete.gridx = 0;
		gbc_btnDelete.gridy = 4;
		panel_1.add(btnDelete, gbc_btnDelete);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.NORTH);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 75, 44, 50, 133, 48, 172, 66, 75, 0, 0, 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		JLabel lblFilter = new JLabel("Filtre");
		lblFilter.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblFilter = new GridBagConstraints();
		gbc_lblFilter.anchor = GridBagConstraints.WEST;
		gbc_lblFilter.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilter.gridx = 1;
		gbc_lblFilter.gridy = 0;
		panel_2.add(lblFilter, gbc_lblFilter);

		chckBoxPrivate = new JCheckBox("Privat");
		chckBoxPrivate.setSelected(true);
		GridBagConstraints gbc_chckBoxPrivate = new GridBagConstraints();
		gbc_chckBoxPrivate.anchor = GridBagConstraints.WEST;
		gbc_chckBoxPrivate.insets = new Insets(0, 0, 5, 5);
		gbc_chckBoxPrivate.gridx = 1;
		gbc_chckBoxPrivate.gridy = 1;
		panel_2.add(chckBoxPrivate, gbc_chckBoxPrivate);

		JLabel lblPhone = new JLabel("Telefonnr.");
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblPhone = new GridBagConstraints();
		gbc_lblPhone.anchor = GridBagConstraints.WEST;
		gbc_lblPhone.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhone.gridx = 4;
		gbc_lblPhone.gridy = 1;
		panel_2.add(lblPhone, gbc_lblPhone);

		chckBoxBusiness = new JCheckBox("Erhverv");
		chckBoxBusiness.setSelected(true);
		GridBagConstraints gbc_chckBoxBusiness = new GridBagConstraints();
		gbc_chckBoxBusiness.anchor = GridBagConstraints.WEST;
		gbc_chckBoxBusiness.insets = new Insets(0, 0, 5, 5);
		gbc_chckBoxBusiness.gridx = 1;
		gbc_chckBoxBusiness.gridy = 2;
		panel_2.add(chckBoxBusiness, gbc_chckBoxBusiness);

		textField = new JTextField();
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 2;
		panel_2.add(textField, gbc_textField);

		btnSearch = new JButton("Søg");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
				rowCounter();
			}
		});
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridx = 7;
		gbc_btnSearch.gridy = 2;
		panel_2.add(btnSearch, gbc_btnSearch);

		btnReset = new JButton("Nulstil");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFilters();
				rowCounter();
			}
		});
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnReset.insets = new Insets(0, 0, 5, 5);
		gbc_btnReset.gridx = 8;
		gbc_btnReset.gridy = 2;
		panel_2.add(btnReset, gbc_btnReset);

		init();
		tempCustomers();
		setTable();
		rowCounter();
	}

	private void init() {

	}
	
	private void search() {
		
	}
	
	private void resetFilters() {
		
	}
	
	private void addCustomer() {
		
	}
	
	private void deleteCustomer() {
		
	}
	
	private void viewCustomerDetails() {
		
	}

	private void tempCustomers() {
		CustomerController cc = new CustomerController();

		cc.createCustomer("Jacob", "Godsbanen 19", "Hobrovej 450", "Godsbanen 19", "24245482", "jacob@mail.dk", 0.5,
				"9000", "Kyed Aps", customerType.PRIVATE);
		cc.createCustomer("Marcus", "Abekatvej 12", "Jyllandsgade 10", "Abekatvej 12", "20926381", "marcus@mail.dk", 0.1,
				"9000", "Marcus Aps", customerType.PRIVATE);
		cc.createCustomer("Mikkel", "Brenning 15", "Reberbahnsgade 2", "Brenning 15", "65748294", "mikkel@mail.dk", 0.9,
				"9000", "Mikkel Aps", customerType.BUSINESS);
		cc.createCustomer("Rasmus", "Støvringvej 248", "Hornevej 89", "Støvringvej 248", "25172085", "rasmus@mail.dk", 0.3,
				"9000", "Rasmus Aps", customerType.PRIVATE);
		cc.createCustomer("Nicolai", "Idrætsvej 1", "Udsigten 90", "Idrætsvej 1", "62719283", "nicolai@mail.dk", 0.8,
				"9000", "Niolai Aps", customerType.BUSINESS);
	}

	private String[][] convertToStringArray(ArrayList<Customer> dataArrayList) {

		int size = dataArrayList.size();
		String[][] data = new String[size][10];
		for (int i = 0; i < size; i++) {
			Customer current = dataArrayList.get(i);
			customerType type = current.getCustomerType();
			String typeName = "";
			
			switch (type) {
			case BUSINESS:
				typeName = "Erhverv";
				break;
			
			case PRIVATE:
				typeName = "Privat";
				break;
			default:
				typeName = "";
			}
			data[i][0] = typeName;
			data[i][1] = current.getAddress();
			data[i][2] = current.getDeliveryAddress();
			data[i][3] = current.getPaymentAddress();
			data[i][4] = current.getPhone();
			data[i][5] = current.getEmail();
			data[i][6] = current.getCredit() + "";
			data[i][7] = current.getPostcode();
			data[i][8] = current.getCompamyName();
			data[i][9] = current.getName();
		}
		return data;
	}

	private void rowCounter() {
		lblRowCounter.setText("Antal: " + table.getRowCount());
	}

	private void setTable() {
		activeColumns = new boolean[] { true, true, false, false, true, true, true, false, false, false, false };
		columns = new String[] { "Kundetype", "Adresse", "Leveringsadresse", "Betalingsadresse", "Telefonnummer",
				"Email", "Kredit", "Postnummer", "Firmanavn", "Navn", "Postnummer" };

		ArrayList<Customer> dataArrayList = CustomerContainer.getInstance().getCustomers();
		String[][] data = convertToStringArray(dataArrayList);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new DefaultTable(data, columns, activeColumns);

		table.getTableHeader().setReorderingAllowed(false);

		scrollPane.setViewportView(table);
	}

	private void closeWindow() {
		this.dispose();
		this.setVisible(false);
	}

}