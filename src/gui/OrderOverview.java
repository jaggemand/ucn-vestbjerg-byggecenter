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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

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
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import controller.OrderController;
import controller.ProductController;
import model.Order;
import model.Order.OrderStatus;
import model.OrderLine;

public class OrderOverview extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldOrderNumber;
	private DefaultTable table;
	private JScrollPane scrollPane;
	private JLabel lblStatus;
	private OrderController orderController;
	private DialogDate dateCreated;
	private DialogDate datePickup;
	private JComboBox comboBoxStatus;
	private JCheckBox chckbxOrderCreated;
	private JCheckBox chckbxOrderPickup;
	private JButton btnDateFilterCreated;
	private JButton btnDateFilterPickup;
	private String[] comboBoksDefaultText = new String[] {"Status"};

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
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JButton btnAddOrder = new JButton("Opret Ordre");
		btnAddOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonCreateOrderPressed();
			}
		});
		GridBagConstraints gbc_btnAddOrder = new GridBagConstraints();
		gbc_btnAddOrder.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddOrder.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddOrder.gridx = 0;
		gbc_btnAddOrder.gridy = 0;
		panel_East.add(btnAddOrder, gbc_btnAddOrder);
		
		JButton btnShowOrder = new JButton("Vis Ordre");
		btnShowOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonShowOrderPressed();
			}
		});
		GridBagConstraints gbc_btnShowOrder = new GridBagConstraints();
		gbc_btnShowOrder.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnShowOrder.insets = new Insets(0, 0, 5, 0);
		gbc_btnShowOrder.gridx = 0;
		gbc_btnShowOrder.gridy = 1;
		panel_East.add(btnShowOrder, gbc_btnShowOrder);
		
		JButton btnDeleteOrdre = new JButton("Slet Ordre");
		btnDeleteOrdre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonDeleteOrderPressed();
			}
		});
		
		GridBagConstraints gbc_btnDeleteOrdre = new GridBagConstraints();
		gbc_btnDeleteOrdre.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDeleteOrdre.gridx = 0;
		gbc_btnDeleteOrdre.gridy = 4;
		panel_East.add(btnDeleteOrdre, gbc_btnDeleteOrdre);
		
		JPanel panel_North = new JPanel();
		contentPane.add(panel_North, BorderLayout.NORTH);
		GridBagLayout gbl_panel_North = new GridBagLayout();
		gbl_panel_North.columnWidths = new int[]{120, 0, 0, 220, 220, 79};
		gbl_panel_North.rowHeights = new int[]{0, 0};
		gbl_panel_North.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
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
		
		chckbxOrderCreated = new JCheckBox("Oprettelsesdato");
		GridBagConstraints gbc_chckbxOrderCreated = new GridBagConstraints();
		gbc_chckbxOrderCreated.fill = GridBagConstraints.BOTH;
		gbc_chckbxOrderCreated.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxOrderCreated.gridx = 2;
		gbc_chckbxOrderCreated.gridy = 0;
		panel_North.add(chckbxOrderCreated, gbc_chckbxOrderCreated);
		
		JLabel lblDateCreated = new JLabel("Oprettelsesdato");
		GridBagConstraints gbc_lblDateCreated = new GridBagConstraints();
		gbc_lblDateCreated.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateCreated.gridx = 3;
		gbc_lblDateCreated.gridy = 0;
		panel_North.add(lblDateCreated, gbc_lblDateCreated);
		
		JLabel lblDatePickup = new JLabel("Afhentningsdato");
		GridBagConstraints gbc_lblDatePickup = new GridBagConstraints();
		gbc_lblDatePickup.insets = new Insets(0, 0, 5, 5);
		gbc_lblDatePickup.gridx = 4;
		gbc_lblDatePickup.gridy = 0;
		panel_North.add(lblDatePickup, gbc_lblDatePickup);
		
		JButton btnSearch = new JButton("Søg");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonSearchPressed();
			}
		});
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 5, 0);
		gbc_btnSearch.gridx = 5;
		gbc_btnSearch.gridy = 0;
		panel_North.add(btnSearch, gbc_btnSearch);
		
		btnDateFilterCreated = new JButton("Vælg periode");
		btnDateFilterCreated.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonDateCreatedPressed();
			}
		});
		
		comboBoxStatus = new JComboBox();
		comboBoxStatus.addPopupMenuListener(new PopupMenuListener() {
			
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				if(comboBoxStatus.getSelectedItem().equals("ALLE STATUSER") || comboBoxStatus.getSelectedItem().equals("Status")) {
					comboBoksDefaultText = new String[] {"ALLE STATUSER"};
					comboBoxStatus.setModel(new DefaultComboBoxModel(comboBoksDefaultText));
				}
				initComboBox();
			}
			
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}
		
			
		});
		comboBoxStatus.setModel(new DefaultComboBoxModel(comboBoksDefaultText));
		comboBoxStatus.setToolTipText("Status på ordre");
		GridBagConstraints gbc_comboBoxStatus = new GridBagConstraints();
		gbc_comboBoxStatus.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxStatus.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxStatus.gridx = 0;
		gbc_comboBoxStatus.gridy = 2;
		panel_North.add(comboBoxStatus, gbc_comboBoxStatus);
		initComboBox();
		
		JCheckBox chckbxPrivateCustomer = new JCheckBox("Privatkunde");
		GridBagConstraints gbc_chckbxPrivateCustomer = new GridBagConstraints();
		gbc_chckbxPrivateCustomer.fill = GridBagConstraints.BOTH;
		gbc_chckbxPrivateCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxPrivateCustomer.gridx = 1;
		gbc_chckbxPrivateCustomer.gridy = 2;
		panel_North.add(chckbxPrivateCustomer, gbc_chckbxPrivateCustomer);
		
		chckbxOrderPickup = new JCheckBox("Afhentingsdato");
		GridBagConstraints gbc_chckbxOrderPickup = new GridBagConstraints();
		gbc_chckbxOrderPickup.fill = GridBagConstraints.BOTH;
		gbc_chckbxOrderPickup.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxOrderPickup.gridx = 2;
		gbc_chckbxOrderPickup.gridy = 2;
		panel_North.add(chckbxOrderPickup, gbc_chckbxOrderPickup);
		GridBagConstraints gbc_btnDateFilterCreated = new GridBagConstraints();
		gbc_btnDateFilterCreated.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDateFilterCreated.insets = new Insets(0, 0, 5, 5);
		gbc_btnDateFilterCreated.gridx = 3;
		gbc_btnDateFilterCreated.gridy = 2;
		panel_North.add(btnDateFilterCreated, gbc_btnDateFilterCreated);
		
		btnDateFilterPickup = new JButton("Vælg periode");
		btnDateFilterPickup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonDatePickupPressed();
			}
		});
		GridBagConstraints gbc_btnDateFilterPickup = new GridBagConstraints();
		gbc_btnDateFilterPickup.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDateFilterPickup.insets = new Insets(0, 0, 5, 5);
		gbc_btnDateFilterPickup.gridx = 4;
		gbc_btnDateFilterPickup.gridy = 2;
		panel_North.add(btnDateFilterPickup, gbc_btnDateFilterPickup);
		updateTable();
	}
	
	private void buttonShowOrderPressed() {
	if(table.getSelectedRowCount() > 0) {
			String selectedOrderNumber = (String) table.getModel().getValueAt(table.getSelectedRow(), 0);
			Order selectedOrder = orderController.findOrder(selectedOrderNumber);
			SalesOrder salesOrder = new SalesOrder(selectedOrder,false);
			salesOrder.setVisible(true);
		}
		else {
			makeStatusMessage("Ingen ordre valgt", true);
		}
	}
	
	private void buttonCreateOrderPressed() {
		SalesOrder salesOrder = new SalesOrder(null,true);
		salesOrder.setVisible(true);
		updateTable();
	}
	
	private void buttonDeleteOrderPressed() {
		deleteData();
		updateTable();
	}
	
	private void buttonClosePressed() {
		dispose();
	}
	
	private void buttonSearchPressed() {
		search();
	}
	
	private void buttonDateCreatedPressed() {
		dateCreated = new DialogDate();
		dateCreated.setVisible(true);
		if(!dateCreated.isOkPressed()) {
			//button text shouldn't change
		}
		else {
			btnDateFilterCreated.setText(setDateSelecterButtonFormat(dateCreated.getDateFrom(), dateCreated.getDateTo()));
		}
	}
	
	private void buttonDatePickupPressed() {
		datePickup = new DialogDate();
		datePickup.setVisible(true);
		if(!datePickup.isOkPressed()) {
			//button text shouldn't change
		}
		else {
			btnDateFilterPickup.setText(setDateSelecterButtonFormat(datePickup.getDateFrom(), datePickup.getDateTo()));
		}
	}
	
	private void initWindow() {
		this.orderController = new OrderController();
	}
	
	//Might not be how we want to code this...?
	@SuppressWarnings("unchecked")
	private void initComboBox() {
		ArrayList<OrderStatus> statuses = orderController.getAllStatusTypes();
		
		Collections.sort(statuses);
		
		for(OrderStatus os : statuses) {
			comboBoxStatus.addItem(os);
		}
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
	
	private void deleteData() {
		int[] columnsToShow = new int[]{0, 1};
		ArrayList<String> dataToDelete = table.deleteData("Ordernummer", columnsToShow);
		if(dataToDelete.size() != 0) {
			for(int i = dataToDelete.size()-1; i >= 0; i--) {
				orderController.removeOrder(dataToDelete.get(i));
			}
		}
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
	
	private String setDateSelecterButtonFormat(Date dateFrom, Date dateTo) {
		String pattern = "DD/MM/YYYY";
		DateFormat df = new SimpleDateFormat(pattern);
		String output = df.format(dateFrom) +" ---> "+ df.format(dateTo);
		return output;
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
	
	private void search() {
		ArrayList<Order> orders = orderController.getAllOrders();
		ArrayList<Order> orderResult = new ArrayList<>();
		
		Date pickupFrom = null;
		Date pickupTo = null;
		Date dateCreatedFrom = null;
		Date dateCreatedTo = null;
		
		if (datePickup != null) {
			pickupFrom = datePickup.getDateFrom();
			pickupTo = datePickup.getDateTo();
		}
		
		if(dateCreated != null) {
			dateCreatedFrom = dateCreated.getDateFrom();
			
			dateCreatedTo = dateCreated.getDateTo();
		}
		
		Iterator<Order> it = orders.iterator();
		
		while(it.hasNext()) {
			Order o = it.next();
			
//		List<OrderStatus> status = Arrays.asList(o.getStatus());
			
			if(o.getOrderNumber().toLowerCase().equals(textFieldOrderNumber.getText().toLowerCase()) || textFieldOrderNumber.getText().length() == 0 || textFieldOrderNumber.getText().equals("Ordernummer")) {
				if((chckbxOrderCreated.isSelected() && isOrderDateBetweenDates(o.getDateAsDateType(), dateCreatedFrom, dateCreatedTo)) || chckbxOrderPickup.isSelected() && isOrderDateBetweenDates(o.getPickupDateAsDateType(), pickupFrom, pickupTo)) {
					if(o.getStatus().equals(comboBoxStatus.getSelectedItem()) || comboBoxStatus.getSelectedIndex() == 0 || comboBoxStatus.getSelectedItem().equals("ALLE STATUSER")) {
						orderResult.add(o);
					}
				}
			}
		}
		table.setNewData(convertToStringArray(orderResult));
	}


	//returns true if the LocalDate given is between dateFrom and dateTo parameters from the dateFilter
	private boolean isOrderDateBetweenDates(Date dateToCompare, Date dateFrom, Date dateTo) {
		boolean output = false;
		Date orderDate = dateToCompare;
		if(orderDate.compareTo(dateFrom) >= 0 && orderDate.compareTo(dateTo) <= 0) {
			output = true;
		}
		return output;
	}
	
	private Date localDateToDateConv(LocalDate ld) {
		ZoneId deafaultZoneId = ZoneId.systemDefault();
		Date returnDate = Date.from(ld.atStartOfDay(deafaultZoneId).toInstant());
		return returnDate;
}

	private String[][] convertToStringArray(ArrayList<Order> dataArrayList) {
		int size = dataArrayList.size();
		String[][] data = new String[size][12];
		for(int i = 0; i < size; i++) {
			Order current = dataArrayList.get(i);
			data[i][0] = current.getOrderNumber();
			data[i][1] = current.getStatusAsString();
			data[i][2] = "Ingen kundetype";
			data[i][3] = current.getDate().toString();
			data[i][4] = current.getPickup().toString();
			data[i][5] = String.valueOf(current.getAmountOfProducts());
		}
		return data;
	}
}