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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.OrderController;
import controller.ProductController;
import model.Order;
import model.OrderLine;
import model.Product;
import model.ProductContainer;

public class CashRegister extends JFrame {

	private JPanel contentPane;
	private DefaultTable table;
	private static CashRegister frame;
	private JScrollPane scrollPane;
	private OrderController orderController;
	private JLabel lblStatus;
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
				OrderController oc = new OrderController();
				oc.createOrder(true);
				Order finishedOrder = addItemsToOrder(oc.getCurrentOrder());
				oc.addOrder();
			}
		});
		panel.add(btnPayment);
		
		JButton btnCancle = new JButton("Afbryd");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
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
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0,0,0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		lblStatus = new JLabel("");
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.gridwidth = 2;
		gbc_lblStatus.anchor = GridBagConstraints.WEST;
		gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 1;
		panel_3.add(lblStatus, gbc_lblStatus);
		
		JLabel lblTotal = new JLabel("Total:");
		GridBagConstraints gbc_lblTotal = new GridBagConstraints();
		gbc_lblTotal.gridwidth = 2;
		gbc_lblTotal.anchor = GridBagConstraints.WEST;
		gbc_lblTotal.insets = new Insets(0, 0, 5, 0);
		gbc_lblTotal.gridx = 8;
		gbc_lblTotal.gridy = 1;
		panel_3.add(lblTotal, gbc_lblTotal);
		
		JLabel lblTotalVat = new JLabel("Total u/ moms");
		GridBagConstraints gbc_lblTotalVat = new GridBagConstraints();
		gbc_lblTotalVat.gridwidth = 2;
		gbc_lblTotalVat.anchor = GridBagConstraints.WEST;
		gbc_lblTotalVat.gridx = 8;
		gbc_lblTotalVat.gridy = 2;
		panel_3.add(lblTotalVat, gbc_lblTotalVat);
		
		
		
		scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);
		initTable();
	}
	
	private void initTable() {
		String[] columns = { "Varenummer", "Navn", "Antal", "Pris"};
		ArrayList<Product> dataArrayList = ProductContainer.getInstance().getProducts();
		String[][] data = null;
		table = new DefaultTable(data, columns);
		scrollPane.setViewportView(table);
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
				//No row selected
			}
			
			
	}
	
	private void buttonDeletePressed() {
		int row = table.getSelectedRow();
		
		if(row != -1) {
			int result = JOptionPane.showOptionDialog(new JFrame().getContentPane(), "Vil du slette dette product", "Bekræft slet", 0, JOptionPane.INFORMATION_MESSAGE, null, new String[] {"Ja","Nej"}, null);
			
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
			//No row selected
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
	
	private void updateTable() {
		table.clear();
		for(OrderLine ol : orderController.getCurrentOrder().getOrderLines()) {
			String[] metaData = new String[4];
			metaData[0] = ol.getProduct().getName();
			metaData[1] = ol.getProduct().getDescription();
			metaData[2] = "" + ol.getQuantity();
			metaData[3] = ol.getProduct().getSalesPriceFormatted();
			table.addRow(metaData);
		}
	}
}
