package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CustomerController;
import controller.ProductController;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
	private JTextField txtPhone;
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
	private DefaultTable table;

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

		lblRowCounter = new JLabel("Antal: 0");
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
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editCustomerDetails();
			}
		});
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

		JLabel lblPhone = new JLabel("Telefonnummer");
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

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		GridBagConstraints gbc_txtPhone = new GridBagConstraints();
		gbc_txtPhone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPhone.insets = new Insets(0, 0, 5, 5);
		gbc_txtPhone.gridx = 4;
		gbc_txtPhone.gridy = 2;
		panel_2.add(txtPhone, gbc_txtPhone);

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
		setTable();
		rowCounter();
	}

	private void init() {

	}

	private void editCustomerDetails() {
		Customer customer = getIndexValueInTable();
		CustomerInformationDialog customerInformationDialog = new CustomerInformationDialog(customer, true);
		customerInformationDialog.setVisible(true);
	}

	private void search() {
		CustomerController customerController = new CustomerController();
		ArrayList<Customer> list = new ArrayList<>();
		ArrayList<Customer> customerList = customerController.getAllCustomers();
		list.clear();
		String phone = txtPhone.getText();

		if (chckBoxBusiness.isSelected()) {
			activeColumns = new boolean[] { true, true, false, true, true, true, true, true, true };
			if (!phone.isBlank()) {
				Customer foundCustomer = customerController.findCustomerByInformation(phone);
				if (foundCustomer != null && foundCustomer.getCustomerType() == customerType.BUSINESS) {
					list.add(foundCustomer);
					chckBoxPrivate.setSelected(false);
				}
			} else {
				for (int i = 0; i < customerList.size(); i++) {
					if (customerList.get(i).getCustomerType() == customerType.BUSINESS) {
						list.add(customerList.get(i));
					}
				}
			}
		}
		if (chckBoxPrivate.isSelected()) {
			activeColumns = new boolean[] { true, true, true, true, true, true, false, true, false };
			if (!phone.isBlank()) {
				Customer foundCustomer = customerController.findCustomerByInformation(phone);
				if (foundCustomer != null && foundCustomer.getCustomerType() == customerType.PRIVATE) {
					list.add(foundCustomer);
					chckBoxBusiness.setSelected(false);
				}
			} else {
				for (int j = 0; j < customerList.size(); j++) {
					if (customerList.get(j).getCustomerType() == customerType.PRIVATE) {
						list.add(customerList.get(j));
					}
				}
			}
		}
		if (chckBoxBusiness.isSelected() && chckBoxPrivate.isSelected()) {
			activeColumns = new boolean[] { true, true, false, false, true, true, false, true, false };
		}
		String[][] data = convertToStringArray(list);
		table.setNewData(data);
		table.setVisibleColumns(activeColumns);

		table.getTableHeader().setReorderingAllowed(false);

		scrollPane.setViewportView(table);
		// TODO Få Marcus til at fikse det her med opdatering af Data
	}

	private void resetFilters() {
		activeColumns = new boolean[] { true, true, false, false, true, true, false, true, false };
		table.setVisibleColumns(activeColumns);
		ArrayList<Customer> dataArrayList = CustomerContainer.getInstance().getCustomers();
		table.setNewData(convertToStringArray(dataArrayList));
		txtPhone.setText("");
		chckBoxBusiness.setSelected(true);
		chckBoxPrivate.setSelected(true);
	}

	private void addCustomer() {
		CustomerInformationDialog customerInformationDialog = new CustomerInformationDialog(null, true);
		customerInformationDialog.setVisible(true);
	}

	private void deleteCustomer() {
		int[] columnsToShow = new int[] { 0, 1 };
		ArrayList<String> dataToDelete = table.deleteData("Telefonnummer", columnsToShow);
		if (dataToDelete.size() != 0) {
			CustomerController cC = new CustomerController();
			for (int i = dataToDelete.size() - 1; i >= 0; i--) {
				cC.removeCustomer(dataToDelete.get(i));
			}
		}
	}

	private void viewCustomerDetails() {
		Customer customer = getIndexValueInTable();
		CustomerInformationDialog customerInformationDialog = new CustomerInformationDialog(customer, false);
		customerInformationDialog.setVisible(true);
	}

	private Customer getIndexValueInTable() {
		int index = table.findElement();
		Customer customer = null;

		if (index == -1) {
			GUIPopUpMessages.informationMessage("Ingen kunde valgt", "Fejl");
		} else {
			CustomerController customerController = new CustomerController();
			customer = customerController.findCustomerByInformation(table.getValueAt(index, 1).toString());
		}
		return customer;
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
			data[i][1] = current.getPhone();
			data[i][2] = current.getName();
			data[i][3] = current.getDeliveryAddress();
			data[i][4] = current.getPaymentAddress();
			data[i][5] = current.getPostcode();
			data[i][6] = current.getCompanyName();
			data[i][7] = current.getEmail();
			data[i][8] = current.getCredit() + "";
		}
		Arrays.sort(data, Comparator.comparing(o -> o[0])); // Comparator to compare and sort in alphabetical order
		return data;
	}

	private void rowCounter() {
		lblRowCounter.setText("Antal: " + table.getRowCount());
	}

	public void setTable() {
		activeColumns = new boolean[] { true, true, false, false, true, true, false, true, false };
		columns = new String[] { "Kundetype", "Telefonnummer", "Navn", "Leveringsadresse", "Faktureringsadresse",
				"Postnummer", "Firmanavn", "Email", "Kredit" };

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
