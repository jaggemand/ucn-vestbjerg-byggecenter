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
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import controller.OrderController;
import controller.ProductController;
import model.Order;
import model.OrderLine;
import model.Product;
import model.ProductContainer;
import javax.swing.JTextField;

public class SalesOrder extends JFrame {

	private JPanel contentPane;
	private DefaultTable table;
	private static SalesOrder frame;
	private JScrollPane scrollPane;
	private OrderController orderController;
	private JLabel lblStatus;
	private JLabel lblTotal;
	private JLabel lblVat;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new SalesOrder();
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
	public SalesOrder() {
		setTitle("Kassesalg");
		
		orderController = new OrderController();
		orderController.createOrder(true);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 622, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnPayment = new JButton("Betaling");
		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPaymentPressed();
			}
		});
		
		JButton btnCancleCurrentSale = new JButton("Afbryd salg");
		btnCancleCurrentSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonCancleCurrentSalePressed();
			}
		});
		panel.add(btnCancleCurrentSale);
		panel.add(btnPayment);
		
		JButton btnCancle = new JButton("Afbryd");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonCanclePressed();
			}
		});
		panel.add(btnCancle);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.EAST);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JButton btnAmount = new JButton("Antal");
		btnAmount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonAmountPressed();
			}
		});
		GridBagConstraints gbc_btnAmount = new GridBagConstraints();
		gbc_btnAmount.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAmount.insets = new Insets(0, 0, 5, 0);
		gbc_btnAmount.gridx = 0;
		gbc_btnAmount.gridy = 1;
		panel_1.add(btnAmount, gbc_btnAmount);
		
		JButton btnAdd = new JButton("Tilføj");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonAddPressed();
			}
		});
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 2;
		panel_1.add(btnAdd, gbc_btnAdd);
		
		JButton btnDelete = new JButton("Slet");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonDeletePressed();
			}
		});
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnDelete.gridx = 0;
		gbc_btnDelete.gridy = 3;
		panel_1.add(btnDelete, gbc_btnDelete);
		
		JButton btnDetails = new JButton("Detaljer");
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonDetailsPressed();
			}
		});
		GridBagConstraints gbc_btnDetails = new GridBagConstraints();
		gbc_btnDetails.insets = new Insets(0, 0, 5, 0);
		gbc_btnDetails.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDetails.gridx = 0;
		gbc_btnDetails.gridy = 4;
		panel_1.add(btnDetails, gbc_btnDetails);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_UnderTableEast = new JPanel();
		FlowLayout fl_panel_UnderTableEast = (FlowLayout) panel_UnderTableEast.getLayout();
		fl_panel_UnderTableEast.setAlignment(FlowLayout.RIGHT);
		panel_3.add(panel_UnderTableEast, BorderLayout.EAST);
		
		lblVat = new JLabel("");
		panel_UnderTableEast.add(lblVat);
		
		lblTotal = new JLabel("Total:");
		panel_UnderTableEast.add(lblTotal);
		
		JPanel panel_UnderTableWest = new JPanel();
		panel_3.add(panel_UnderTableWest, BorderLayout.WEST);
		
		lblStatus = new JLabel("");
		panel_UnderTableWest.add(lblStatus);
		
		
		
		scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4, BorderLayout.NORTH);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_4.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JLabel lblNewLabel = new JLabel("Ordrenummer:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panel_4.add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		textField.setForeground(Color.BLACK);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panel_4.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Oprettelsesdato");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 1;
		panel_4.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 1;
		panel_4.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Status:");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 4;
		gbc_lblNewLabel_4.gridy = 1;
		panel_4.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 5;
		gbc_textField_4.gridy = 1;
		panel_4.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Kundenummer");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		panel_4.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		panel_4.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Afhentningsdato");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 2;
		panel_4.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 3;
		gbc_textField_3.gridy = 2;
		panel_4.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("Ændre status");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridx = 5;
		gbc_btnNewButton.gridy = 2;
		panel_4.add(btnNewButton, gbc_btnNewButton);
		
		JLabel lblNewLabel_5 = new JLabel("Produkter");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.gridwidth = 6;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 3;
		panel_4.add(lblNewLabel_5, gbc_lblNewLabel_5);
		initTable();
	}
	
	private void initTable() {
		String[] columns = { "Varenummer", "Navn", "Antal", "Pris", "Total"};
		ArrayList<Product> dataArrayList = ProductContainer.getInstance().getProducts();
		String[][] data = null;
		table = new DefaultTable(data, columns);
		scrollPane.setViewportView(table);
		//Only allows user to select single a single row at the time
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//User cannot change the ordering of the columns
		table.getTableHeader().setReorderingAllowed(false);
		//Make custom cellRenderer and give column 3, 4, 5 the new renderer
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
	}
	
	private void updateCartTable(String[] data) {
		table.addRow(data);
	}
	
	/**
	 * Given a itemNumber and an amount, the function returns a formatted string where individual item salesPrices is multiplied by amount
	 * @param itemNumber
	 * @param amount
	 * @return String formatted to Danish currency 
	 */
	private String changeItemPriceTotal(String itemNumber, int amount) {
		ProductController pc = new ProductController();
		Product p = pc.findProduct(itemNumber);
		DecimalFormat df = new DecimalFormat("0.00");
		double totalItemSalesPrice = p.getSalesPrice() * amount;
		return df.format(totalItemSalesPrice) + " DKK";
	}
	
	private Order addItemsToOrder(Order o) {
		int itemCount = table.getModel().getRowCount();
		int productAmount = 1;
		ProductController p = new ProductController();
		Product tempProduct = null;
		for(int i = 0; i < itemCount; i++) {
			tempProduct = p.findProduct((String) table.getModel().getValueAt(i, 0));
			productAmount = Integer.parseInt(((String) table.getModel().getValueAt(i, 2)));
			o.addProduct(tempProduct, productAmount);
		}
		return o;
	}

	private void buttonAddPressed() {
		//displays an error message if the product inserted does not exist
		DialogItemAdd newItem = new DialogItemAdd();
		newItem.setVisible(true);
		
		if (newItem.getNewProduct() == null) {
			lblStatus.setForeground(Color.red);
			lblStatus.setText("Tilføj afbrudt");
		}
		else {
			orderController.addProduct(newItem.getNewProduct().getBarcode(), newItem.getAmount());
			updateTable();
		}
	}
	private void buttonAmountPressed() {
			int row = table.getSelectedRow();
			if(row != -1) {
				DialogAmount newAmountDialog = new DialogAmount(orderController.getCurrentOrder().getOrderLines().get(row));
				newAmountDialog.setVisible(true);
				updateTable();
			}
			else {
				makeStatusMessage("Ingen række valgt", true);
			}
	}
	
	private void buttonDeletePressed() {
		int row = table.getSelectedRow();
		
		if(row != -1) {
			//int result = JOptionPane.showOptionDialog(new JFrame().getContentPane(), "Vil du slette dette product", "Bekræft slet", 0, JOptionPane.INFORMATION_MESSAGE, null, new String[] {"Ja","Nej"}, null);
			int result = GUIPopUpMessages.customBox("Vil du slette dette product", "Bekræft slet", new String[] {"Ja","Nej"}, JOptionPane.INFORMATION_MESSAGE);
			if(result == 0) {
				Product productToDelete = orderController.getCurrentOrder().getOrderLines().get(row).getProduct();
				//Confirmed
				orderController.getCurrentOrder().removeProduct(productToDelete.getBarcode());
				updateTable();
			}
			else {
				//Delete canceled
			}
		}
		else {
			makeStatusMessage("Ingen række valgt", true);
		}
	}
	
	private void buttonPaymentPressed() {
		if(table.getModel().getRowCount() > 0) {
			/*int input = JOptionPane.showOptionDialog(new JFrame(), "Er du sikker på at du vil gennemføre betalingen", "Betaling",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					new Object[] { "Betal", "Tilbage" }, JOptionPane.YES_OPTION);*/
			int input = GUIPopUpMessages.customBox("Er du sikker på at du vil gennemføre betalingen", "Betaling", new Object[] { "Betal", "Tilbage" }, JOptionPane.QUESTION_MESSAGE);
			if(input == 0) {
				orderController.addOrder();
				resetFrame();
			}
		}
		else {
			makeStatusMessage("Ingen produkter er scannet", true);
		}
	}
	
	private void buttonCancleCurrentSalePressed() {
		if(table.getModel().getRowCount() < 1) {
			resetFrame();
		}
		else {
			/*int input = JOptionPane.showOptionDialog(new JFrame(), "Er du sikker på at du vil afbryde nuværende salg?", "Afbryd salg",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null,
					new Object[] { "OK", "Tilbage" }, JOptionPane.YES_OPTION);*/
			int input = GUIPopUpMessages.okBackBox("Er du sikker på at du vil afbryde nuværende salg?", "Afbryd salg");
			if(input == 0) {
				resetFrame();
			}
		}
	}
	
	private void buttonDetailsPressed() {
		int row = table.getSelectedRow();
		
		if(row != -1) {
			Product productToRead = orderController.getCurrentOrder().getOrderLines().get(row).getProduct();
			ProductInformation productInfomation = new ProductInformation(productToRead,false);
			productInfomation.setVisible(true);
		}
		else {
			//No row selected
		}
	}
	
	private void buttonCanclePressed() {
		if(table.getModel().getRowCount() < 1) {
			dispose();
		}
		else {
			/*int input = JOptionPane.showOptionDialog(new JFrame(), "Er du sikker på at du vil afbryde nuværende salg og lukke modulet", "Afbryd salg og luk modul?",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					new Object[] { "OK", "Tilbage" }, JOptionPane.YES_OPTION);*/
			int input = GUIPopUpMessages.okBackBox("Er du sikker på at du vil afbryde nuværende salg og lukke modulet", "Afbryd salg og luk modul?");
			
			if(input == 0) {
				dispose();
			}
		}
	}
	
	private void updateTable() {
		table.clear();
		for(OrderLine ol : orderController.getCurrentOrder().getOrderLines()) {
			String[] metaData = new String[5];
			metaData[0] = ol.getProduct().getBarcode();
			metaData[1] = ol.getProduct().getName();
			metaData[2] = "" + ol.getQuantity();
			metaData[3] = ol.getProduct().getSalesPriceFormatted();
			metaData[4] = "Wait for format";
			table.addRow(metaData);
		}
		calculateRowTotals();
		updateTotal();
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
	
	private void calculateRowTotals() {
		int rowsToCalculate = orderController.getCurrentOrder().getOrderLines().size();
		ArrayList<OrderLine> ol = orderController.getCurrentOrder().getOrderLines();
		double productSalesPrice = 0;
		String rowAmount = "";
		String rowTotal = "";
		for(int i = 0; i < rowsToCalculate; i++) {
			productSalesPrice = ol.get(i).getProduct().getSalesPrice();
			rowAmount = (String) table.getModel().getValueAt(i, 2);
			rowTotal = getPriceFormatted(productSalesPrice * Integer.parseInt(rowAmount));
			table.getModel().setValueAt(rowTotal, i, 4);
		}
	}
	
	private String getPriceFormatted(double price) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(price);
	}
	
	private void updateTotal() {
		double total = 0;
		ArrayList<OrderLine> ol = orderController.getCurrentOrder().getOrderLines();
		for(int i = 0; i < ol.size(); i++) {
			total += (ol.get(i).getProduct().getSalesPrice() * ol.get(i).getQuantity());
		}
		lblTotal.setText("Total: " + getPriceFormatted(total));
		updateVAT(total);
	}
	
	private void updateVAT(double price) {
		lblVat.setText("moms: " + getPriceFormatted(price / 5));
	}
	
	private void resetFrame() {
		dispose();
		SalesOrder cashRegister = new SalesOrder();
		cashRegister.setVisible(true);
		cashRegister.setBounds(this.getBounds());
	}
}
