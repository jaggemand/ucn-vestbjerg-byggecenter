package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ProductController;
import model.Product;
import model.ProductContainer;

public class ProductOverview extends JFrame {

	private JPanel contentPane;
	private static ProductOverview frame;
	private DefaultTable table;
	private JTextField txtProductName;
	private JTextField txtMaxPrice;
	private JTextField txtMinPrice;
	private JComboBox<String> jcbCategories;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ProductOverview();
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
	public ProductOverview() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 923, 644);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		
		String[] columns = { "Varenummer", "Navn", "Lagerbeholdning", "Lagerlokation", "Butiksbeholdning", "Butikslokation"};
		int size = ProductContainer.getInstance().getProducts().size();
		ArrayList<Product> dataArrayList = ProductContainer.getInstance().getProducts();
		String[][] data = new String[size][6];
		for(int i = 0; i < size; i++) {
			Product current = dataArrayList.get(i);
			data[i][0] = current.getProductID();
			data[i][1] = current.getName();
			data[i][2] = Integer.toString(current.getStorageAmount());
			data[i][3] = current.getStorageLocation();
			data[i][4] = Integer.toString(current.getWarehouseAmount());
			data[i][5] = current.getWarehouseLocation();
		}
		
		table = new DefaultTable(data, columns);
		scrollPane.setViewportView(table);
		JPanel panel = new JPanel();
		panel.setLayout(getLayout());
		contentPane.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 56, 30, 97, 0, 0, 217, 0, 0, 0, 0, 76, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 23, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblFilter = new JLabel("Filtre");
		lblFilter.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_lblFilter = new GridBagConstraints();
		gbc_lblFilter.anchor = GridBagConstraints.WEST;
		gbc_lblFilter.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilter.gridx = 1;
		gbc_lblFilter.gridy = 0;
		panel.add(lblFilter, gbc_lblFilter);
		
		JCheckBox jrbStoreLocation = new JCheckBox("Butik");
		jrbStoreLocation.setSelected(true);
		GridBagConstraints gbc_jrbStoreLocation = new GridBagConstraints();
		gbc_jrbStoreLocation.anchor = GridBagConstraints.NORTHWEST;
		gbc_jrbStoreLocation.insets = new Insets(0, 0, 5, 5);
		gbc_jrbStoreLocation.gridx = 2;
		gbc_jrbStoreLocation.gridy = 1;
		panel.add(jrbStoreLocation, gbc_jrbStoreLocation);
		
		JLabel lblMinPrice = new JLabel("Min Pris");
		lblMinPrice.setFont(new Font("Tahoma", Font.BOLD, 10));
		GridBagConstraints gbc_lblMinPrice = new GridBagConstraints();
		gbc_lblMinPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMinPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinPrice.gridx = 3;
		gbc_lblMinPrice.gridy = 1;
		panel.add(lblMinPrice, gbc_lblMinPrice);
		
		JLabel lblMaxPrice = new JLabel("Max Pris");
		lblMaxPrice.setFont(new Font("Tahoma", Font.BOLD, 10));
		GridBagConstraints gbc_lblMaxPrice = new GridBagConstraints();
		gbc_lblMaxPrice.anchor = GridBagConstraints.WEST;
		gbc_lblMaxPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxPrice.gridx = 4;
		gbc_lblMaxPrice.gridy = 1;
		panel.add(lblMaxPrice, gbc_lblMaxPrice);
		
		JLabel lblProductName = new JLabel("Produkt Navn");
		lblProductName.setFont(new Font("Tahoma", Font.BOLD, 10));
		GridBagConstraints gbc_lblProductName = new GridBagConstraints();
		gbc_lblProductName.anchor = GridBagConstraints.WEST;
		gbc_lblProductName.insets = new Insets(0, 0, 5, 5);
		gbc_lblProductName.gridx = 6;
		gbc_lblProductName.gridy = 1;
		panel.add(lblProductName, gbc_lblProductName);
		
		JButton btnSearch = new JButton("Søg");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 5, 0);
		gbc_btnSearch.fill = GridBagConstraints.BOTH;
		gbc_btnSearch.gridx = 11;
		gbc_btnSearch.gridy = 1;
		panel.add(btnSearch, gbc_btnSearch);
		
		jcbCategories = new JComboBox();
		GridBagConstraints gbc_jcbCategories = new GridBagConstraints();
		gbc_jcbCategories.anchor = GridBagConstraints.NORTHWEST;
		gbc_jcbCategories.insets = new Insets(0, 0, 0, 5);
		gbc_jcbCategories.gridx = 1;
		gbc_jcbCategories.gridy = 2;
		panel.add(jcbCategories, gbc_jcbCategories);
		jcbCategories.addItem("Kategorier");
		
		JCheckBox jrbWarehouseLocation = new JCheckBox("Lager");
		jrbWarehouseLocation.setSelected(true);
		GridBagConstraints gbc_jrbWarehouseLocation = new GridBagConstraints();
		gbc_jrbWarehouseLocation.anchor = GridBagConstraints.NORTHWEST;
		gbc_jrbWarehouseLocation.insets = new Insets(0, 0, 0, 5);
		gbc_jrbWarehouseLocation.gridx = 2;
		gbc_jrbWarehouseLocation.gridy = 2;
		panel.add(jrbWarehouseLocation, gbc_jrbWarehouseLocation);
		
		txtMinPrice = new JTextField();
		txtMinPrice.setColumns(10);
		GridBagConstraints gbc_txtMinPrice = new GridBagConstraints();
		gbc_txtMinPrice.insets = new Insets(0, 0, 0, 5);
		gbc_txtMinPrice.anchor = GridBagConstraints.WEST;
		gbc_txtMinPrice.gridx = 3;
		gbc_txtMinPrice.gridy = 2;
		panel.add(txtMinPrice, gbc_txtMinPrice);
		
		txtMaxPrice = new JTextField();
		txtMaxPrice.setColumns(10);
		GridBagConstraints gbc_txtMaxPrice = new GridBagConstraints();
		gbc_txtMaxPrice.anchor = GridBagConstraints.WEST;
		gbc_txtMaxPrice.insets = new Insets(0, 0, 0, 5);
		gbc_txtMaxPrice.gridx = 4;
		gbc_txtMaxPrice.gridy = 2;
		panel.add(txtMaxPrice, gbc_txtMaxPrice);
		
		txtProductName = new JTextField();
		txtProductName.setColumns(10);
		GridBagConstraints gbc_txtProductName = new GridBagConstraints();
		gbc_txtProductName.insets = new Insets(0, 0, 0, 5);
		gbc_txtProductName.anchor = GridBagConstraints.WEST;
		gbc_txtProductName.gridx = 6;
		gbc_txtProductName.gridy = 2;
		panel.add(txtProductName, gbc_txtProductName);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.EAST);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JButton btnAdd = new JButton("Tilføj");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductInformation productInformation = new ProductInformation(null, true);
				productInformation.setVisible(true);
			}
			
		});
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 1;
		panel_1.add(btnAdd, gbc_btnAdd);
		
		JButton btnEdit = new JButton("Rediger");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editProduct();
			}
		});
		
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEdit.insets = new Insets(0, 0, 5, 0);
		gbc_btnEdit.gridx = 0;
		gbc_btnEdit.gridy = 2;
		panel_1.add(btnEdit, gbc_btnEdit);
		
		JButton btnDetails = new JButton("Detaljer");
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				detailsProduct();
			}
		});
		
		GridBagConstraints gbc_btnDetails = new GridBagConstraints();
		gbc_btnDetails.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDetails.insets = new Insets(0, 0, 5, 0);
		gbc_btnDetails.gridx = 0;
		gbc_btnDetails.gridy = 3;
		panel_1.add(btnDetails, gbc_btnDetails);
		
		JButton btnDelete = new JButton("Slet");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.deleteData();
			}
		});
		
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.gridx = 0;
		gbc_btnDelete.gridy = 4;
		panel_1.add(btnDelete, gbc_btnDelete);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnClose = new JButton("Afslut");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		
		panel_2.add(btnClose);
		
		//Initialize window
		initializeWindow();
		
	}
	private void closeWindow() {
		this.dispose();
		this.setVisible(false);
		
	}

	private void initializeWindow() {
		//Fill categories into the combobox
		//TODO cant access model layer directly???!!
		for(String e : ProductContainer.getInstance().getCategories()) {
			jcbCategories.addItem(e);
		}
	}
	
	private void editProduct() {
		int index = table.findElement();
		Product product = ProductContainer.getInstance().getProducts().get(index);
		
		ProductInformation productInformation = new ProductInformation(product, true);
		productInformation.setVisible(true);
	}
	
	private void detailsProduct() {
		int index = table.findElement();
		Product product = ProductContainer.getInstance().getProducts().get(index);
		
		ProductInformation productInformation = new ProductInformation(product, false);
		productInformation.setVisible(true);
	}
	
	private void search() {
		
		ArrayList<Product> products = ProductContainer.getInstance().getProducts();
		int size = products.size();
		ArrayList<Product> productResult = new ArrayList<>();
		
		if (txtMinPrice.getText().length() != 0) {
			double minPrice = Double.parseDouble(txtMinPrice.getText());
		}
		if (txtMaxPrice.getText().length() != 0) {
			double maxPrice = Double.parseDouble(txtMaxPrice.getText());
		}
		
		Iterator<Product> it = products.iterator();
		
		while(it.hasNext()) {
			Product p = it.next();
			
		List<String> categories = Arrays.asList(p.getCategory());
			
			if (p.equals(txtProductName.getText()) && p.getSalesPrice() >= minPrice && p.getSalesPrice() <= maxPrice
					&& categories.contains(jcbCategories.getSelectedItem())){
				productResult.add(p);
				System.out.println("succes");
			}
		}
	}
}