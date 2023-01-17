package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import controller.OrderController;
import model.Order;
import model.OrderLine;

public class OrderOverview extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldOrderNumber;
	private DefaultTable table;
	private JScrollPane scrollPane;
	private JLabel lblStatus;
	private OrderController orderController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderOverview frame = new OrderOverview();
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
	public OrderOverview() {
		initWindow();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 885, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_South = new JPanel();
		contentPane.add(panel_South, BorderLayout.SOUTH);
		panel_South.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel_South.add(panel, BorderLayout.WEST);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		lblStatus = new JLabel("Status");
		panel.add(lblStatus);
		
		JPanel panel_1 = new JPanel();
		panel_South.add(panel_1, BorderLayout.EAST);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JButton btnClose = new JButton("Afslut");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonClosePressed();
			}
		});
		btnClose.setMargin(new Insets(2, 20, 2, 20));
		GridBagConstraints gbc_btnClose = new GridBagConstraints();
		gbc_btnClose.fill = GridBagConstraints.BOTH;
		gbc_btnClose.gridx = 0;
		gbc_btnClose.gridy = 0;
		panel_1.add(btnClose, gbc_btnClose);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		initTable();
		
		JPanel panel_East = new JPanel();
		contentPane.add(panel_East, BorderLayout.EAST);
		GridBagLayout gbl_panel_East = new GridBagLayout();
		gbl_panel_East.columnWidths = new int[]{0, 0};
		gbl_panel_East.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_East.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_East.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_East.setLayout(gbl_panel_East);
		
		JButton btnAdd = new JButton("Tilføj");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 0;
		panel_East.add(btnAdd, gbc_btnAdd);
		
		JButton btnEdit = new JButton("Rediger");
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEdit.insets = new Insets(0, 0, 5, 0);
		gbc_btnEdit.gridx = 0;
		gbc_btnEdit.gridy = 1;
		panel_East.add(btnEdit, gbc_btnEdit);
		
		JButton btnDetails = new JButton("Detaljer");
		GridBagConstraints gbc_btnDetails = new GridBagConstraints();
		gbc_btnDetails.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDetails.insets = new Insets(0, 0, 5, 0);
		gbc_btnDetails.gridx = 0;
		gbc_btnDetails.gridy = 2;
		panel_East.add(btnDetails, gbc_btnDetails);
		
		JButton btnDelete = new JButton("Slet");
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.gridx = 0;
		gbc_btnDelete.gridy = 4;
		panel_East.add(btnDelete, gbc_btnDelete);
		
		JPanel panel_North = new JPanel();
		contentPane.add(panel_North, BorderLayout.NORTH);
		GridBagLayout gbl_panel_North = new GridBagLayout();
		gbl_panel_North.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_North.rowHeights = new int[]{0, 0};
		gbl_panel_North.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0};
		gbl_panel_North.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_North.setLayout(gbl_panel_North);
		
		textFieldOrderNumber = new JTextField();
		textFieldOrderNumber.setText("Ordernummer");
		GridBagConstraints gbc_textFieldOrderNumber = new GridBagConstraints();
		gbc_textFieldOrderNumber.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldOrderNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldOrderNumber.gridx = 0;
		gbc_textFieldOrderNumber.gridy = 0;
		panel_North.add(textFieldOrderNumber, gbc_textFieldOrderNumber);
		textFieldOrderNumber.setColumns(10);
		
		JCheckBox chckbxBusinessCustomer = new JCheckBox("Erhvervskunde");
		GridBagConstraints gbc_chckbxBusinessCustomer = new GridBagConstraints();
		gbc_chckbxBusinessCustomer.fill = GridBagConstraints.BOTH;
		gbc_chckbxBusinessCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxBusinessCustomer.gridx = 1;
		gbc_chckbxBusinessCustomer.gridy = 0;
		panel_North.add(chckbxBusinessCustomer, gbc_chckbxBusinessCustomer);
		
		JCheckBox chckbxOrderCreated = new JCheckBox("Order oprettet");
		GridBagConstraints gbc_chckbxOrderCreated = new GridBagConstraints();
		gbc_chckbxOrderCreated.fill = GridBagConstraints.BOTH;
		gbc_chckbxOrderCreated.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxOrderCreated.gridx = 2;
		gbc_chckbxOrderCreated.gridy = 0;
		panel_North.add(chckbxOrderCreated, gbc_chckbxOrderCreated);
		
		JLabel lblDateCreated = new JLabel("Oprettet dato");
		GridBagConstraints gbc_lblDateCreated = new GridBagConstraints();
		gbc_lblDateCreated.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateCreated.gridx = 3;
		gbc_lblDateCreated.gridy = 0;
		panel_North.add(lblDateCreated, gbc_lblDateCreated);
		
		JLabel lblDatePickup = new JLabel("Pickup dato");
		GridBagConstraints gbc_lblDatePickup = new GridBagConstraints();
		gbc_lblDatePickup.insets = new Insets(0, 0, 5, 5);
		gbc_lblDatePickup.gridx = 4;
		gbc_lblDatePickup.gridy = 0;
		panel_North.add(lblDatePickup, gbc_lblDatePickup);
		
		JButton btnSearch = new JButton("Søg");
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 5, 0);
		gbc_btnSearch.gridx = 5;
		gbc_btnSearch.gridy = 0;
		panel_North.add(btnSearch, gbc_btnSearch);
		
		JButton btnDateFilterCreated = new JButton("Dato");
		btnDateFilterCreated.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JComboBox comboBoxStatus = new JComboBox();
		comboBoxStatus.setModel(new DefaultComboBoxModel(new String[] {"Status"}));
		comboBoxStatus.setToolTipText("Status på ordre");
		GridBagConstraints gbc_comboBoxStatus = new GridBagConstraints();
		gbc_comboBoxStatus.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxStatus.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxStatus.gridx = 0;
		gbc_comboBoxStatus.gridy = 2;
		panel_North.add(comboBoxStatus, gbc_comboBoxStatus);
		
		JCheckBox chckbxPrivateCustomer = new JCheckBox("Privatkunde");
		GridBagConstraints gbc_chckbxPrivateCustomer = new GridBagConstraints();
		gbc_chckbxPrivateCustomer.fill = GridBagConstraints.BOTH;
		gbc_chckbxPrivateCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxPrivateCustomer.gridx = 1;
		gbc_chckbxPrivateCustomer.gridy = 2;
		panel_North.add(chckbxPrivateCustomer, gbc_chckbxPrivateCustomer);
		
		JCheckBox chckbxOrderPickup = new JCheckBox("Pickup");
		GridBagConstraints gbc_chckbxOrderPickup = new GridBagConstraints();
		gbc_chckbxOrderPickup.fill = GridBagConstraints.BOTH;
		gbc_chckbxOrderPickup.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxOrderPickup.gridx = 2;
		gbc_chckbxOrderPickup.gridy = 2;
		panel_North.add(chckbxOrderPickup, gbc_chckbxOrderPickup);
		GridBagConstraints gbc_btnDateFilterCreated = new GridBagConstraints();
		gbc_btnDateFilterCreated.insets = new Insets(0, 0, 5, 5);
		gbc_btnDateFilterCreated.gridx = 3;
		gbc_btnDateFilterCreated.gridy = 2;
		panel_North.add(btnDateFilterCreated, gbc_btnDateFilterCreated);
		
		JButton btnDateFilterPickup = new JButton("Dato");
		GridBagConstraints gbc_btnDateFilterPickup = new GridBagConstraints();
		gbc_btnDateFilterPickup.insets = new Insets(0, 0, 5, 5);
		gbc_btnDateFilterPickup.gridx = 4;
		gbc_btnDateFilterPickup.gridy = 2;
		panel_North.add(btnDateFilterPickup, gbc_btnDateFilterPickup);
		updateTable();
	}
	
	private void buttonClosePressed() {
		dispose();
	}
	
	private void initWindow() {
		this.orderController = new OrderController();
	}
	
	private void initTable() {
		String[] columns = { "Ordernummer", "Status", "Kundetype", "Dato oprettet", "Opsamlingsdato", "Størrelse"};
//		ArrayList<Product> dataArrayList = ProductContainer.getInstance().getProducts();
		String[][] data = null;
		table = new DefaultTable(data, columns);
		scrollPane.setViewportView(table);
		//Only allows user to select single a single row at the time
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		//User cannot change the ordering of the columns
		table.getTableHeader().setReorderingAllowed(false);
		//Make custom cellRenderer and give column 3, 4, 5 the new renderer
//		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
//		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
//		table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
//		table.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
//		table.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
	}
	
	private void updateTable() {
		int orderSize = 0;
		table.clear();
		for(Order o : orderController.getAllOrders()) {
			String[] metaData = new String[6];
			metaData[0] = o.getOrderNumber();
			metaData[1] = o.getStatusAsString();
			metaData[2] = "Kunde not specified yet";
			metaData[3] = o.getDate().toString();
			metaData[4] = o.getPickup().toString();
			for(OrderLine ol: o.getOrderLines()) {
				orderSize += ol.getQuantity();
			}
			metaData[5] = String.valueOf(orderSize);
			orderSize = 0;
			table.addRow(metaData);
		}
		//Not needed for now
//		calculateRowTotals();
//		updateTotal();
	}
	
	private void makeStatusMessage(String message, boolean isCorrelatedWithError) {
		lblStatus.setText(message);
		if(isCorrelatedWithError) {
			lblStatus.setBackground(Color.RED);
		}
		else {
			lblStatus.setBackground(Color.BLACK);
		}
	}
	
	//opens new window with same dimensions and location as old one
	private void resetFrame() {
		dispose();
		OrderOverview orderOverview = new OrderOverview();
		orderOverview.setVisible(true);
		orderOverview.setBounds(this.getBounds());
	}

}
