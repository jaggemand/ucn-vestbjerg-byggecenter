package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import controller.ProductController;
import model.Product;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductInformation extends JFrame {

	private JPanel contentPane;
	private JTextField txtWarehouseAmount;
	private JTextField txtStoreAmount;
	private JTextField txtWarehouseLocation;
	private JTextField txtStoreLocation;
	private JTextField txtCostPrice;
	private JTextField txtSuggestedSalesPrice;
	private JTextField txtSalesPrice;
	private JTextField txtProductName;
	private JTextField txtBarcode;
	private JTextField txtProductID;
	private JTextArea txtProductDescription;
	private JList categoryList;
	private static Product product;
	private static boolean editMode;
	private JButton btnCategoryAdd;
	private JButton btnCategoryRemove;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductInformation frame = new ProductInformation(null, false);
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

	public ProductInformation(Product product, boolean editMode) {
		setTitle("Produkt");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 20, 0, 0, 0, 111, 0, 0, 0, 0, 0, 32, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JPanel panel_general = new JPanel();
		panel_general.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_general.setBackground(new Color(192, 192, 192));
		GridBagConstraints gbc_panel_general = new GridBagConstraints();
		gbc_panel_general.gridheight = 11;
		gbc_panel_general.insets = new Insets(0, 0, 5, 5);
		gbc_panel_general.fill = GridBagConstraints.BOTH;
		gbc_panel_general.gridx = 1;
		gbc_panel_general.gridy = 1;
		contentPane.add(panel_general, gbc_panel_general);
		GridBagLayout gbl_panel_general = new GridBagLayout();
		gbl_panel_general.columnWidths = new int[] { 24, 26, 0 };
		gbl_panel_general.rowHeights = new int[] { 0, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_general.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel_general.rowWeights = new double[] { 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0,
				1.0, Double.MIN_VALUE };
		panel_general.setLayout(gbl_panel_general);

		JLabel lblHeaderGenerel = new JLabel("Generel");
		lblHeaderGenerel.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_lblHeaderGenerel = new GridBagConstraints();
		gbc_lblHeaderGenerel.gridwidth = 2;
		gbc_lblHeaderGenerel.insets = new Insets(0, 0, 5, 0);
		gbc_lblHeaderGenerel.gridx = 0;
		gbc_lblHeaderGenerel.gridy = 0;
		panel_general.add(lblHeaderGenerel, gbc_lblHeaderGenerel);

		JLabel lblProduktID = new JLabel("Varenummer");
		GridBagConstraints gbc_lblProduktID = new GridBagConstraints();
		gbc_lblProduktID.fill = GridBagConstraints.VERTICAL;
		gbc_lblProduktID.gridwidth = 2;
		gbc_lblProduktID.insets = new Insets(0, 0, 5, 0);
		gbc_lblProduktID.gridx = 0;
		gbc_lblProduktID.gridy = 2;
		panel_general.add(lblProduktID, gbc_lblProduktID);

		txtProductID = new JTextField();
		GridBagConstraints gbc_txtProductID = new GridBagConstraints();
		gbc_txtProductID.gridwidth = 2;
		gbc_txtProductID.insets = new Insets(0, 0, 5, 0);
		gbc_txtProductID.fill = GridBagConstraints.BOTH;
		gbc_txtProductID.gridx = 0;
		gbc_txtProductID.gridy = 3;
		panel_general.add(txtProductID, gbc_txtProductID);
		txtProductID.setColumns(10);

		JLabel lblBarcode = new JLabel("Stregkode");
		GridBagConstraints gbc_lblBarcode = new GridBagConstraints();
		gbc_lblBarcode.gridwidth = 2;
		gbc_lblBarcode.insets = new Insets(0, 0, 5, 0);
		gbc_lblBarcode.gridx = 0;
		gbc_lblBarcode.gridy = 5;
		panel_general.add(lblBarcode, gbc_lblBarcode);

		txtBarcode = new JTextField();
		GridBagConstraints gbc_txtBarcode = new GridBagConstraints();
		gbc_txtBarcode.gridwidth = 2;
		gbc_txtBarcode.insets = new Insets(0, 0, 5, 0);
		gbc_txtBarcode.fill = GridBagConstraints.BOTH;
		gbc_txtBarcode.gridx = 0;
		gbc_txtBarcode.gridy = 6;
		panel_general.add(txtBarcode, gbc_txtBarcode);
		txtBarcode.setColumns(10);

		JLabel lblProductName = new JLabel("Produktnavn");
		GridBagConstraints gbc_lblProductName = new GridBagConstraints();
		gbc_lblProductName.fill = GridBagConstraints.VERTICAL;
		gbc_lblProductName.gridwidth = 2;
		gbc_lblProductName.insets = new Insets(0, 0, 5, 0);
		gbc_lblProductName.gridx = 0;
		gbc_lblProductName.gridy = 8;
		panel_general.add(lblProductName, gbc_lblProductName);

		txtProductName = new JTextField();
		GridBagConstraints gbc_txtProductName = new GridBagConstraints();
		gbc_txtProductName.gridwidth = 2;
		gbc_txtProductName.insets = new Insets(0, 0, 5, 0);
		gbc_txtProductName.fill = GridBagConstraints.BOTH;
		gbc_txtProductName.gridx = 0;
		gbc_txtProductName.gridy = 9;
		panel_general.add(txtProductName, gbc_txtProductName);
		txtProductName.setColumns(10);

		JLabel lblProductDescription = new JLabel("Beskrivelse");
		GridBagConstraints gbc_lblProductDescription = new GridBagConstraints();
		gbc_lblProductDescription.gridwidth = 2;
		gbc_lblProductDescription.insets = new Insets(0, 0, 5, 0);
		gbc_lblProductDescription.gridx = 0;
		gbc_lblProductDescription.gridy = 11;
		panel_general.add(lblProductDescription, gbc_lblProductDescription);

		txtProductDescription = new JTextArea();
		GridBagConstraints gbc_txtProductDescription = new GridBagConstraints();
		gbc_txtProductDescription.insets = new Insets(0, 0, 5, 0);
		gbc_txtProductDescription.gridwidth = 2;
		gbc_txtProductDescription.fill = GridBagConstraints.BOTH;
		gbc_txtProductDescription.gridx = 0;
		gbc_txtProductDescription.gridy = 12;
		panel_general.add(txtProductDescription, gbc_txtProductDescription);

		JPanel panel_price = new JPanel();
		panel_price.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_price.setBackground(new Color(192, 192, 192));
		GridBagConstraints gbc_panel_price = new GridBagConstraints();
		gbc_panel_price.gridwidth = 2;
		gbc_panel_price.gridheight = 5;
		gbc_panel_price.insets = new Insets(0, 0, 5, 5);
		gbc_panel_price.fill = GridBagConstraints.BOTH;
		gbc_panel_price.gridx = 3;
		gbc_panel_price.gridy = 1;
		contentPane.add(panel_price, gbc_panel_price);
		GridBagLayout gbl_panel_price = new GridBagLayout();
		gbl_panel_price.columnWidths = new int[] { 23, 23, 0 };
		gbl_panel_price.rowHeights = new int[] { 0, 0, 24, 0, 0, 23, 21, 0, 0, 0, 0, 0 };
		gbl_panel_price.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel_price.rowWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0,
				Double.MIN_VALUE };
		panel_price.setLayout(gbl_panel_price);

		JLabel lblHeaderPrice = new JLabel("Pris");
		lblHeaderPrice.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_lblHeaderPrice = new GridBagConstraints();
		gbc_lblHeaderPrice.gridwidth = 2;
		gbc_lblHeaderPrice.insets = new Insets(0, 0, 5, 0);
		gbc_lblHeaderPrice.gridx = 0;
		gbc_lblHeaderPrice.gridy = 0;
		panel_price.add(lblHeaderPrice, gbc_lblHeaderPrice);

		JLabel lblCostPrice = new JLabel("Indkøbspris");
		GridBagConstraints gbc_lblCostPrice = new GridBagConstraints();
		gbc_lblCostPrice.gridwidth = 2;
		gbc_lblCostPrice.insets = new Insets(0, 0, 5, 0);
		gbc_lblCostPrice.gridx = 0;
		gbc_lblCostPrice.gridy = 2;
		panel_price.add(lblCostPrice, gbc_lblCostPrice);

		txtCostPrice = new JTextField();
		GridBagConstraints gbc_txtCostPrice = new GridBagConstraints();
		gbc_txtCostPrice.gridwidth = 2;
		gbc_txtCostPrice.insets = new Insets(0, 0, 5, 0);
		gbc_txtCostPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCostPrice.gridx = 0;
		gbc_txtCostPrice.gridy = 3;
		panel_price.add(txtCostPrice, gbc_txtCostPrice);
		txtCostPrice.setColumns(10);

		JLabel lblSuggestedSalesPrice = new JLabel("Vejledende pris");
		GridBagConstraints gbc_lblSuggestedSalesPrice = new GridBagConstraints();
		gbc_lblSuggestedSalesPrice.gridwidth = 2;
		gbc_lblSuggestedSalesPrice.insets = new Insets(0, 0, 5, 0);
		gbc_lblSuggestedSalesPrice.gridx = 0;
		gbc_lblSuggestedSalesPrice.gridy = 5;
		panel_price.add(lblSuggestedSalesPrice, gbc_lblSuggestedSalesPrice);

		txtSuggestedSalesPrice = new JTextField();
		txtSuggestedSalesPrice.setColumns(10);
		GridBagConstraints gbc_txtSuggestedSalesPrice = new GridBagConstraints();
		gbc_txtSuggestedSalesPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSuggestedSalesPrice.gridwidth = 2;
		gbc_txtSuggestedSalesPrice.insets = new Insets(0, 0, 5, 0);
		gbc_txtSuggestedSalesPrice.gridx = 0;
		gbc_txtSuggestedSalesPrice.gridy = 6;
		panel_price.add(txtSuggestedSalesPrice, gbc_txtSuggestedSalesPrice);

		JLabel lblSalesPrice = new JLabel("Salgspris");
		GridBagConstraints gbc_lblSalesPrice = new GridBagConstraints();
		gbc_lblSalesPrice.fill = GridBagConstraints.VERTICAL;
		gbc_lblSalesPrice.gridwidth = 2;
		gbc_lblSalesPrice.insets = new Insets(0, 0, 5, 0);
		gbc_lblSalesPrice.gridx = 0;
		gbc_lblSalesPrice.gridy = 8;
		panel_price.add(lblSalesPrice, gbc_lblSalesPrice);

		txtSalesPrice = new JTextField();
		txtSalesPrice.setColumns(10);
		GridBagConstraints gbc_txtSalesPrice = new GridBagConstraints();
		gbc_txtSalesPrice.insets = new Insets(0, 0, 5, 0);
		gbc_txtSalesPrice.gridwidth = 2;
		gbc_txtSalesPrice.fill = GridBagConstraints.BOTH;
		gbc_txtSalesPrice.gridx = 0;
		gbc_txtSalesPrice.gridy = 9;
		panel_price.add(txtSalesPrice, gbc_txtSalesPrice);

		JPanel panel_location = new JPanel();
		panel_location.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_location.setBackground(new Color(192, 192, 192));
		GridBagConstraints gbc_panel_location = new GridBagConstraints();
		gbc_panel_location.gridwidth = 2;
		gbc_panel_location.gridheight = 10;
		gbc_panel_location.insets = new Insets(0, 0, 5, 5);
		gbc_panel_location.fill = GridBagConstraints.BOTH;
		gbc_panel_location.gridx = 6;
		gbc_panel_location.gridy = 1;
		contentPane.add(panel_location, gbc_panel_location);
		GridBagLayout gbl_panel_location = new GridBagLayout();
		gbl_panel_location.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_location.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_location.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel_location.rowWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0,
				0.0, Double.MIN_VALUE };
		panel_location.setLayout(gbl_panel_location);

		JLabel lblHeaderLocation = new JLabel("Lokation");
		lblHeaderLocation.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_lblHeaderLocation = new GridBagConstraints();
		gbc_lblHeaderLocation.gridwidth = 2;
		gbc_lblHeaderLocation.insets = new Insets(0, 0, 5, 0);
		gbc_lblHeaderLocation.gridx = 0;
		gbc_lblHeaderLocation.gridy = 0;
		panel_location.add(lblHeaderLocation, gbc_lblHeaderLocation);

		JLabel lblStoreLocation = new JLabel("Butikslokation");
		GridBagConstraints gbc_lblStoreLocation = new GridBagConstraints();
		gbc_lblStoreLocation.fill = GridBagConstraints.VERTICAL;
		gbc_lblStoreLocation.insets = new Insets(0, 0, 5, 0);
		gbc_lblStoreLocation.gridwidth = 2;
		gbc_lblStoreLocation.gridx = 0;
		gbc_lblStoreLocation.gridy = 2;
		panel_location.add(lblStoreLocation, gbc_lblStoreLocation);

		txtStoreLocation = new JTextField();
		GridBagConstraints gbc_txtStoreLocation = new GridBagConstraints();
		gbc_txtStoreLocation.insets = new Insets(0, 0, 5, 0);
		gbc_txtStoreLocation.gridwidth = 2;
		gbc_txtStoreLocation.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtStoreLocation.gridx = 0;
		gbc_txtStoreLocation.gridy = 3;
		panel_location.add(txtStoreLocation, gbc_txtStoreLocation);
		txtStoreLocation.setColumns(10);

		JLabel lblWarehouseLocation = new JLabel("Lagerlokation");
		GridBagConstraints gbc_lblWarehouseLocation = new GridBagConstraints();
		gbc_lblWarehouseLocation.gridwidth = 2;
		gbc_lblWarehouseLocation.insets = new Insets(0, 0, 5, 0);
		gbc_lblWarehouseLocation.gridx = 0;
		gbc_lblWarehouseLocation.gridy = 5;
		panel_location.add(lblWarehouseLocation, gbc_lblWarehouseLocation);

		txtWarehouseLocation = new JTextField();
		GridBagConstraints gbc_txtWarehouseLocation = new GridBagConstraints();
		gbc_txtWarehouseLocation.insets = new Insets(0, 0, 5, 0);
		gbc_txtWarehouseLocation.gridwidth = 2;
		gbc_txtWarehouseLocation.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtWarehouseLocation.gridx = 0;
		gbc_txtWarehouseLocation.gridy = 6;
		panel_location.add(txtWarehouseLocation, gbc_txtWarehouseLocation);
		txtWarehouseLocation.setColumns(10);

		JLabel lblStoreAmount = new JLabel("Butiksbeholdning");
		GridBagConstraints gbc_lblStoreAmount = new GridBagConstraints();
		gbc_lblStoreAmount.insets = new Insets(0, 0, 5, 0);
		gbc_lblStoreAmount.gridwidth = 2;
		gbc_lblStoreAmount.gridx = 0;
		gbc_lblStoreAmount.gridy = 8;
		panel_location.add(lblStoreAmount, gbc_lblStoreAmount);

		txtStoreAmount = new JTextField();
		GridBagConstraints gbc_txtStoreAmount = new GridBagConstraints();
		gbc_txtStoreAmount.insets = new Insets(0, 0, 5, 0);
		gbc_txtStoreAmount.gridwidth = 2;
		gbc_txtStoreAmount.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtStoreAmount.gridx = 0;
		gbc_txtStoreAmount.gridy = 9;
		panel_location.add(txtStoreAmount, gbc_txtStoreAmount);
		txtStoreAmount.setColumns(10);

		JLabel lblWarehouseAmount = new JLabel("Lagerbeholdning");
		GridBagConstraints gbc_lblWarehouseAmount = new GridBagConstraints();
		gbc_lblWarehouseAmount.insets = new Insets(0, 0, 5, 0);
		gbc_lblWarehouseAmount.gridwidth = 2;
		gbc_lblWarehouseAmount.gridx = 0;
		gbc_lblWarehouseAmount.gridy = 11;
		panel_location.add(lblWarehouseAmount, gbc_lblWarehouseAmount);

		txtWarehouseAmount = new JTextField();
		GridBagConstraints gbc_txtWarehouseAmount = new GridBagConstraints();
		gbc_txtWarehouseAmount.insets = new Insets(0, 0, 5, 0);
		gbc_txtWarehouseAmount.gridwidth = 2;
		gbc_txtWarehouseAmount.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtWarehouseAmount.gridx = 0;
		gbc_txtWarehouseAmount.gridy = 12;
		panel_location.add(txtWarehouseAmount, gbc_txtWarehouseAmount);
		txtWarehouseAmount.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_2.setBackground(new Color(192, 192, 192));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 2;
		gbc_panel_2.gridheight = 5;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 3;
		gbc_panel_2.gridy = 7;
		contentPane.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 33, 0, 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		JLabel lblCategories = new JLabel("Kategorier");
		lblCategories.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_lblCategories = new GridBagConstraints();
		gbc_lblCategories.insets = new Insets(0, 0, 5, 0);
		gbc_lblCategories.gridwidth = 4;
		gbc_lblCategories.gridx = 0;
		gbc_lblCategories.gridy = 0;
		panel_2.add(lblCategories, gbc_lblCategories);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		panel_2.add(scrollPane, gbc_scrollPane);

		categoryList = new JList();
		categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(categoryList);

		btnCategoryAdd = new JButton("Tilføj");
		btnCategoryAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCategory();
			}
		});
		GridBagConstraints gbc_btnCategoryAdd = new GridBagConstraints();
		gbc_btnCategoryAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnCategoryAdd.gridx = 3;
		gbc_btnCategoryAdd.gridy = 2;
		panel_2.add(btnCategoryAdd, gbc_btnCategoryAdd);

		btnCategoryRemove = new JButton("Fjern");
		GridBagConstraints gbc_btnCategoryRemove = new GridBagConstraints();
		gbc_btnCategoryRemove.insets = new Insets(0, 0, 5, 0);
		gbc_btnCategoryRemove.gridx = 3;
		gbc_btnCategoryRemove.gridy = 3;
		panel_2.add(btnCategoryRemove, gbc_btnCategoryRemove);

		JButton btnSave = new JButton("Gem");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveProduct(editMode);
			}
		});
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 6;
		gbc_btnSave.gridy = 11;
		contentPane.add(btnSave, gbc_btnSave);

		JButton btnCancel = new JButton("Afbryd");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 7;
		gbc_btnCancel.gridy = 11;
		contentPane.add(btnCancel, gbc_btnCancel);

		/* Price textfields disabled */
		txtCostPrice.setEnabled(false);
		txtSalesPrice.setEnabled(false);
		txtSuggestedSalesPrice.setEnabled(false);
		this.product = product;
		this.editMode = editMode;
		init();
		editType(editMode);
	}

	public void init() {
		if (product == null) {
			DefaultListModel listModel = new DefaultListModel();
			categoryList.setModel(listModel);
		} else {
			DefaultListModel listModel = new DefaultListModel();
			for (String element : product.getCategory()) {
				listModel.addElement(element);
			}
			categoryList.setModel(listModel);
			txtProductName.setText(product.getName());
			txtProductID.setText(product.getProductID());
			txtProductDescription.setText(product.getDescription());
			txtBarcode.setText(product.getBarcode());
			txtCostPrice.setText(product.getCostPriceFormatted());
			txtSuggestedSalesPrice.setText(product.getSuggestedSalesPriceFormatted());
			txtSalesPrice.setText(product.getSalesPriceFormatted());
			txtStoreLocation.setText(product.getStorageLocation());
			txtStoreAmount.setText(Integer.toString(product.getStorageAmount()));
			txtWarehouseLocation.setText(product.getWarehouseLocation());
			txtWarehouseAmount.setText(Integer.toString(product.getWarehouseAmount()));
		}
	}

	public void editType(boolean editMode) {
		if (editMode) {
			txtWarehouseAmount.setEnabled(true);
			txtWarehouseLocation.setEnabled(true);
			txtStoreAmount.setEnabled(true);
			txtStoreLocation.setEnabled(true);
			txtCostPrice.setEnabled(false); // Not allowed to change price
			txtSuggestedSalesPrice.setEnabled(false); // Not allowed to change price
			txtSalesPrice.setEnabled(false); // Not allowed to change price
			txtProductName.setEnabled(true);
			txtBarcode.setEnabled(true);
			txtProductID.setEnabled(false); // Not allowed to edit productID
			txtProductDescription.setEnabled(true);
			btnCategoryAdd.setEnabled(true);
			btnCategoryRemove.setEnabled(true);
		} else {
			txtWarehouseAmount.setEnabled(false);
			txtWarehouseLocation.setEnabled(false);
			txtStoreAmount.setEnabled(false);
			txtStoreLocation.setEnabled(false);
			txtCostPrice.setEnabled(false);
			txtSuggestedSalesPrice.setEnabled(false);
			txtSalesPrice.setEnabled(false);
			txtProductName.setEnabled(false);
			txtBarcode.setEnabled(false);
			txtProductID.setEnabled(false);
			txtProductDescription.setEnabled(false);
			btnCategoryAdd.setEnabled(false);
			btnCategoryRemove.setEnabled(false);
		}
	}

	public void saveProduct(boolean editMode) {
		ProductController productController = new ProductController();
		String productName = txtProductName.getText();
		String productDescription = txtProductDescription.getText();
		String barcode = txtBarcode.getText();
		String productID = txtProductID.getText();
		String storeLocation = txtStoreLocation.getText();
		String warehouseLocation = txtWarehouseLocation.getText();
		int storeAmount = 0;
		int warehouseAmount = 0;
		storeAmount = convertToNumber(txtStoreAmount.getText().toString(), "Butiksbeholdning");
		warehouseAmount = convertToNumber(txtWarehouseAmount.getText().toString(), "Lagerbeholdning");

		if (!editMode) {
			closeWindow();
		} else if (productName.isBlank() || productDescription.isBlank()) {
			JOptionPane.showMessageDialog(null, "Produktnavn og beskrivelse skal være udfyldt", "Fejl!",
					JOptionPane.ERROR_MESSAGE);
		} else if (editMode && productController.findProduct(productID) != null) {
			product.setBarcode(barcode);
			product.setName(productName);
			product.setDescription(productDescription);
			product.setStorageLocation(storeLocation);
			product.setWarehouseLocation(warehouseLocation);
			product.setStorageAmount(storeAmount);
			product.setWarehouseAmount(warehouseAmount);
			JOptionPane.showMessageDialog(null, "Produktet er blevet opdateret", "Success!",
					JOptionPane.INFORMATION_MESSAGE);
			closeWindow();
		} else {
			if (productController.findProduct(productID) == null && productController.findProduct(barcode) == null) {
				Product newProduct = productController.createProduct(productName, barcode, productDescription, null,
						storeLocation, warehouseLocation, storeAmount, warehouseAmount);
				// TODO add categories to product instead of null
				if (productController.getAllProducts().contains(newProduct)) {
					JOptionPane.showMessageDialog(null, "Produktet er blevet tilføjet", "Success!",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "En fejl opstod og produktet er ikke blevet tilføjet", "Fejl!",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Produktet med det givne varenummer/stregkode findes allerede",
						"Fejl!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public int convertToNumber(String input, String location) {
		int amount = 0;
		try {
			amount = Integer.parseInt(input); // Store amount
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, location+" var ikke indtastet og er sat til 0.",
					"Information!", JOptionPane.INFORMATION_MESSAGE);
		}
		return amount;
	}

	public void addCategory() {
		DialogCategoryAdd dialogCategoryAdd = new DialogCategoryAdd();
		dialogCategoryAdd.setVisible(true);
	}

	public void closeWindow() {
		this.dispose();
		this.setVisible(false);
	}
	
	public void insertData(Product p) {
		txtWarehouseAmount.setText(String.valueOf(p.getWarehouseAmount()));
		txtStoreAmount.setText(String.valueOf(p.getStorageAmount()));;
		txtWarehouseLocation.setText(p.getWarehouseLocation());
		txtStoreLocation.setText(p.getStorageLocation());;
		txtCostPrice.setText(String.valueOf(p.getCostPrice()));;
		txtSuggestedSalesPrice.setText(String.valueOf(p.getSuggestedSalesPrice()));;
		txtSalesPrice.setText(String.valueOf(p.getSalesPrice()));;
		txtProductName.setText(p.getName());;
		txtBarcode.setText(p.getBarcode());;
		txtProductID.setText(p.getProductID());;
		
	}

}
