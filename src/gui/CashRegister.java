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

import controller.CustomerController;
import controller.OrderController;
import controller.ProductController;
import model.Customer;
import model.Order;
import model.OrderLine;
import model.Product;
import model.ProductContainer;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CashRegister extends JFrame {

	private JPanel contentPane;
	private DefaultTable table;
	private static CashRegister frame;
	private JScrollPane scrollPane;
	private OrderController orderController;
	private CustomerController customerController;
	private JLabel lblStatus;
	private JLabel lblTotal;
	private JLabel lblVat;
	private JButton btnDetails;
	private JButton btnAmount;
	private JButton btnDelete;
	private JButton btnPayment;
	private JButton btnCancelCurrentSale;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new CashRegister();
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
	public CashRegister() {
		setTitle("Kassesalg");
		
		customerController = new CustomerController();
		
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
		
		btnPayment = new JButton("Betaling");
		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPaymentPressed();
				
			}
		});
		
		btnCancelCurrentSale = new JButton("Afbryd salg");
		btnCancelCurrentSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonCancleCurrentSalePressed();
			}
		});
		panel.add(btnCancelCurrentSale);
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
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		btnAmount = new JButton("Antal");
		btnAmount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonAmountPressed();
			}
		});
		
		JButton btnAdd = new JButton("Tilføj");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonAddPressed();
			}
		});
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 0);
		gbc_verticalStrut.gridx = 0;
		gbc_verticalStrut.gridy = 0;
		panel_1.add(verticalStrut, gbc_verticalStrut);
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 1;
		panel_1.add(btnAdd, gbc_btnAdd);
		
		btnDetails = new JButton("Detaljer");
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonDetailsPressed();
			}
		});
		GridBagConstraints gbc_btnDetails = new GridBagConstraints();
		gbc_btnDetails.insets = new Insets(0, 0, 5, 0);
		gbc_btnDetails.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDetails.gridx = 0;
		gbc_btnDetails.gridy = 2;
		panel_1.add(btnDetails, gbc_btnDetails);
		GridBagConstraints gbc_btnAmount = new GridBagConstraints();
		gbc_btnAmount.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAmount.insets = new Insets(0, 0, 5, 0);
		gbc_btnAmount.gridx = 0;
		gbc_btnAmount.gridy = 3;
		panel_1.add(btnAmount, gbc_btnAmount);
		
		btnDelete = new JButton("Slet");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonDeletePressed();
			}
		});
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnDelete.gridx = 0;
		gbc_btnDelete.gridy = 4;
		panel_1.add(btnDelete, gbc_btnDelete);
		
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
		initTable();
		initWindow();
	}
	
	private void initWindow() {
		grayOutCheck();
	}

	private void initTable() {
		String[] columns = { "Varenummer", "Navn", "Antal", "Pris", "Total"};
		ArrayList<Product> dataArrayList = ProductContainer.getInstance().getProducts();
		String[][] data = null;
		table = new DefaultTable(data, columns);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				grayOutCheck();
					
				
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
		DialogItemAdd newItem = new DialogItemAdd(this);
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
				DialogAmount newAmountDialog = new DialogAmount(this, orderController.getCurrentOrder().getOrderLines().get(row));
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
			//int result = JOptionPane.showOptionDialog(new JFrame().getContentPane(), "Vil du slette dette produkt", "Bekræft slet", 0, JOptionPane.INFORMATION_MESSAGE, null, new String[] {"Ja","Nej"}, null);
			int result = GUIPopUpMessages.customBox("Vil du slette dette product", "Bekræft slet",new String[] {"Ja","Nej"},JOptionPane.INFORMATION_MESSAGE );
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
		Customer customer = customerController.findCustomerByInformation("0");
		//Must have items in the table
		if(table.getModel().getRowCount() > 0) {
			//Must have added a customer
			if(customer != null) {
				/*int input = JOptionPane.showOptionDialog(new JFrame(), "Er du sikker på at du vil gennemføre betalingen", "Betaling",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				new Object[] { "Betal", "Tilbage" }, JOptionPane.YES_OPTION);*/
				int input = GUIPopUpMessages.customBox("Er du sikker på at du vil gennemføre betalingen", "Betaling",new Object[] { "Betal", "Tilbage" } , JOptionPane.QUESTION_MESSAGE);
				if(input == 0) {
					orderController.addOrder(customer.getPhone());
					resetFrame();
				}
			} else {
				//Customer not found
			}
			
		} else {
			//no items in the table
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
			int input = GUIPopUpMessages.okBackBox("Er du sikker på at du vil afbryde nuværende salg?", "Afbryd Salg");
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
		grayOutCheck();
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
		CashRegister cashRegister = new CashRegister();
		cashRegister.setVisible(true);
		cashRegister.setBounds(this.getBounds());
	}
	
	public void grayOutCheck() {
		if (table.getSelectedRow() != -1) {
			btnAmount.setEnabled(true);
			btnDelete.setEnabled(true);
			btnDetails.setEnabled(true);
		}
		else {
			btnAmount.setEnabled(false);
			btnDelete.setEnabled(false);
			btnDetails.setEnabled(false);
		}
		if (table.getRowCount() > 0) {
			btnPayment.setEnabled(true);
			btnCancelCurrentSale.setEnabled(true);
		}
		else {
			btnPayment.setEnabled(false);
			btnCancelCurrentSale.setEnabled(false);
		}
	}
}
