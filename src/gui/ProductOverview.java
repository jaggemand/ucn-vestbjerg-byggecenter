package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.ProductController;
import model.Product;
import model.ProductContainer;

public class ProductOverview extends JFrame {

	private JPanel contentPane;
	private DefaultTable table;
	private String firstElement;
	private JLabel lblRowCounter;
	private JPopupMenu popUp;
	private String[] columns;
	private boolean[] activeColumns;

	private JTextField txtProductName;
	private JTextField txtMaxPrice;
	private JTextField txtMinPrice;
	private JComboBox<String> jcbCategories;
	private JCheckBox jrbStoreLocation;
	private JCheckBox jrbWarehouseLocation;
	private ProductController pc;
	/**
	 * Create the frame.
	 */

	public ProductOverview() {
		setTitle("Produktoversigt");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 923, 644);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		setTable();
		setPopUpMenu();
		setButtons();
		// Initialize window
		initializeWindow();
	}

	private void closeWindow() {
		this.dispose();
		this.setVisible(false);
	}

	private void initializeWindow() {
		// Fill categories into the combobox
		// ArrayList<String> categories = new
		// ArrayList<>(ProductContainer.getInstance().getCategories());
		pc = new ProductController();

		ArrayList<Product> products = pc.getAllProducts();
		ArrayList<String> existingCategories = new ArrayList<>();
		for (int i = 0; i < products.size(); i++) {
			String[] productCategories = products.get(i).getCategory();
			for (int x = 0; x < productCategories.length; x++) {
				if (!existingCategories.contains(productCategories[x].toLowerCase())) {
					existingCategories.add(productCategories[x].toLowerCase());
				}
			}
		}
		/*
		 * 
		 * for(String e : categories) { if(existingCategories.contains(e.toLowerCase()))
		 * { jcbCategories.addItem(e); } }
		 */
		Collections.sort(existingCategories);
		for (String e : existingCategories) {
			if(e.length() >= 1) {
				jcbCategories.addItem(e.substring(0,1).toUpperCase() + e.substring(1));
			}
		}
	}
	//TODO Remove function? Greyout are currently in use for delete, details and edit
	private void showProduct(boolean edit) {
		int index = table.findElement();

		if (index == -1) {
			GUIPopUpMessages.informationMessage("Intet produkt valgt", "Fejl");
		} else {
			ProductController productController = new ProductController();
			Product product = productController.findProduct(table.getValueAt(index, 0).toString());

			ProductInformation productInformation = new ProductInformation(product, edit);
			productInformation.setVisible(true);
		}
	}

	private void search() {

		ArrayList<Product> products = ProductContainer.getInstance().getProducts();
		ArrayList<Product> productResult = new ArrayList<>();
		double minPrice = -1;
		double maxPrice = -1;

		if (txtMinPrice.getText().length() != 0) {
			try {
				minPrice = Double.parseDouble(txtMinPrice.getText());

			} catch (NumberFormatException e) {
				GUIPopUpMessages.informationMessage("Input skal være et tal", "Fejl: Minimums pris");
				return;
			}
		}

		if (txtMaxPrice.getText().length() != 0) {
			try {
				maxPrice = Double.parseDouble(txtMaxPrice.getText());
			} catch (NumberFormatException e) {
				GUIPopUpMessages.informationMessage("Input skal være et tal", "Fejl: Maximums pris");
				return;
			}
		}

		if (minPrice > maxPrice) {
			double temp = minPrice;
			minPrice = maxPrice;
			maxPrice = temp;
			txtMinPrice.setText(String.valueOf(minPrice));
			txtMaxPrice.setText(String.valueOf(maxPrice));
		}
		
		Iterator<Product> it = products.iterator();
		
		while (it.hasNext()) {
			Product p = it.next();

			List<String> categories = Arrays.asList(p.getCategory());

			if (p.getName().toLowerCase().contains(txtProductName.getText().toLowerCase())
					|| txtProductName.getText().length() == 0) {
				if (p.getSalesPrice() >= minPrice || minPrice == -1) {
					if (p.getSalesPrice() <= maxPrice || maxPrice == -1) {
						if (categories.contains(jcbCategories.getSelectedItem())
								|| jcbCategories.getSelectedItem().equals(firstElement)) {
							if (jrbWarehouseLocation.isSelected() == true && p.getWarehouseAmount() > 0) {
								productResult.add(p);
							} else if (jrbStoreLocation.isSelected() == true && p.getStorageAmount() > 0) {
								productResult.add(p);
							} else if (jrbWarehouseLocation.isSelected() == false
									&& jrbStoreLocation.isSelected() == false && p.getWarehouseAmount() <= 0
									&& p.getStorageAmount() <= 0) {
								productResult.add(p);
							}
						}
					}
				}
			}
		}
		table.setNewData(convertToStringArray(productResult));
		rowCounter();
	}
	
	private String[][] convertToStringArray(ArrayList<Product> dataArrayList) {

		int size = dataArrayList.size();
		String[][] data = new String[size][12];
		for (int i = 0; i < size; i++) {
			Product current = dataArrayList.get(i);
			data[i][0] = current.getProductID();
			data[i][1] = current.getName();
			data[i][2] = Integer.toString(current.getStorageAmount());
			data[i][3] = current.getStorageLocation();
			data[i][4] = Integer.toString(current.getWarehouseAmount());
			data[i][5] = current.getWarehouseLocation();
			data[i][6] = String.valueOf(current.getSalesPrice());
			data[i][7] = current.getBarcode();
			data[i][8] = current.getDescription();
			data[i][9] = String.join(", ", current.getCategory());
			data[i][10] = String.valueOf(current.getCostPrice());
			data[i][11] = String.valueOf(current.getSuggestedSalesPrice());
		}
		return data;
	}

	private void rowCounter() {
		lblRowCounter.setText("Antal: " + table.getRowCount());
	}

	private void showPopUp(MouseEvent e) {
		if (e.isPopupTrigger()) {
			popUp.show(e.getComponent(), e.getX(), e.getY());
		}
	}

	private void popUpMenuAction(boolean header, ActionEvent e) {
		String s = e.getActionCommand();

		if (s.equals("Vis detaljer")) {
			showProduct(false);
		} else if (s.equals("Rediger produkt")) {
			showProduct(true);
		} else if (s.equals("Slet produkt")) {
			deleteData();
			rowCounter();
		}
	}

	private void deleteData() {
		int[] columnsToShow = new int[]{0, 1};
		ArrayList<String> dataToDelete = table.deleteData("ProduktID", columnsToShow);
		if(dataToDelete.size() != 0) {
			ProductController pC = new ProductController();
			for(int i = dataToDelete.size() - 1; i >= 0; i--) {
				pC.removeProduct(dataToDelete.get(i));
			}
		}
	}
	
	private void setTable() {
		activeColumns = new boolean[] { true, true, true, true, true, true, false, false, false, false, false, false };
		columns = new String[] { "ProduktID", "Navn", "Butiksbeholdning", "Butikslokation", "Lagerbeholdning",
				"Lagerlokation", "Salgspris", "Stregkode", "Beskrivelse", "Kategori", "Kostpris",
				"Vejledende salgspris" };
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		ArrayList<Product> dataArrayList = ProductContainer.getInstance().getProducts();
		String[][] data = convertToStringArray(dataArrayList);

		table = new DefaultTable(data, columns, activeColumns);

		scrollPane.setViewportView(table);
	}

	private void setPopUpMenu() {
		popUp = new JPopupMenu();
		JMenuItem details = new JMenuItem("Vis detaljer");
		JMenuItem edit = new JMenuItem("Rediger produkt");
		JMenuItem delete = new JMenuItem("Slet produkt");

		popUp.add(details);
		popUp.add(edit);
		popUp.add(delete);

		ActionListener alDetails = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				popUpMenuAction(false, e);
			}
		};

		details.addActionListener(alDetails);
		edit.addActionListener(alDetails);
		delete.addActionListener(alDetails);
		MouseAdapter ma = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == 3) {
					showPopUp(e);
				}
			}
		};
		table.addMouseListener(ma);
	}
	private void resetFilter() {
		txtProductName.setText(null);
		txtMaxPrice.setText(null);
		txtMinPrice.setText(null);
		jcbCategories.setSelectedIndex(0);
		jrbStoreLocation.setSelected(true);
		jrbWarehouseLocation.setSelected(true);
		rowCounter();
	}

	private void setButtons() {
		JPanel panel = new JPanel();
		panel.setLayout(getLayout());
		contentPane.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 56, 30, 97, 0, 0, 217, 0, 0, 0, 0, 76, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 23, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblFilter = new JLabel("Filtre");
		lblFilter.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblFilter = new GridBagConstraints();
		gbc_lblFilter.anchor = GridBagConstraints.WEST;
		gbc_lblFilter.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilter.gridx = 1;
		gbc_lblFilter.gridy = 0;
		panel.add(lblFilter, gbc_lblFilter);

		JLabel lblCategories = new JLabel("Kategorier");
		lblCategories.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblCategories = new GridBagConstraints();
		gbc_lblCategories.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCategories.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategories.gridx = 1;
		gbc_lblCategories.gridy = 1;
		panel.add(lblCategories, gbc_lblCategories);

		jrbStoreLocation = new JCheckBox("Butik");
		jrbStoreLocation.setSelected(true);
		GridBagConstraints gbc_jrbStoreLocation = new GridBagConstraints();
		gbc_jrbStoreLocation.anchor = GridBagConstraints.NORTHWEST;
		gbc_jrbStoreLocation.insets = new Insets(0, 0, 5, 5);
		gbc_jrbStoreLocation.gridx = 2;
		gbc_jrbStoreLocation.gridy = 1;
		panel.add(jrbStoreLocation, gbc_jrbStoreLocation);

		JLabel lblMinPrice = new JLabel("Min Pris");
		lblMinPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblMinPrice = new GridBagConstraints();
		gbc_lblMinPrice.fill = GridBagConstraints.BOTH;
		gbc_lblMinPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinPrice.gridx = 3;
		gbc_lblMinPrice.gridy = 1;
		panel.add(lblMinPrice, gbc_lblMinPrice);

		JLabel lblMaxPrice = new JLabel("Max Pris");
		lblMaxPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblMaxPrice = new GridBagConstraints();
		gbc_lblMaxPrice.fill = GridBagConstraints.VERTICAL;
		gbc_lblMaxPrice.anchor = GridBagConstraints.WEST;
		gbc_lblMaxPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxPrice.gridx = 4;
		gbc_lblMaxPrice.gridy = 1;
		panel.add(lblMaxPrice, gbc_lblMaxPrice);
		
		JLabel lblProductName = new JLabel("Produkt Navn");
		lblProductName.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblProductName = new GridBagConstraints();
		gbc_lblProductName.anchor = GridBagConstraints.WEST;
		gbc_lblProductName.insets = new Insets(0, 0, 5, 5);
		gbc_lblProductName.gridx = 6;
		gbc_lblProductName.gridy = 1;
		panel.add(lblProductName, gbc_lblProductName);
		
		jcbCategories = new JComboBox();
		GridBagConstraints gbc_jcbCategories = new GridBagConstraints();
		gbc_jcbCategories.anchor = GridBagConstraints.NORTHWEST;
		gbc_jcbCategories.insets = new Insets(0, 0, 5, 5);
		gbc_jcbCategories.gridx = 1;
		gbc_jcbCategories.gridy = 2;
		panel.add(jcbCategories, gbc_jcbCategories);
		firstElement = "Vælg kategori";
		jcbCategories.addItem(firstElement);
		
		jrbWarehouseLocation = new JCheckBox("Lager");
		jrbWarehouseLocation.setSelected(true);
		GridBagConstraints gbc_jrbWarehouseLocation = new GridBagConstraints();
		gbc_jrbWarehouseLocation.anchor = GridBagConstraints.NORTHWEST;
		gbc_jrbWarehouseLocation.insets = new Insets(0, 0, 5, 5);
		gbc_jrbWarehouseLocation.gridx = 2;
		gbc_jrbWarehouseLocation.gridy = 2;
		panel.add(jrbWarehouseLocation, gbc_jrbWarehouseLocation);
		
		txtMinPrice = new JTextField();
		txtMinPrice.setColumns(10);
		GridBagConstraints gbc_txtMinPrice = new GridBagConstraints();
		gbc_txtMinPrice.insets = new Insets(0, 0, 5, 5);
		gbc_txtMinPrice.anchor = GridBagConstraints.WEST;
		gbc_txtMinPrice.gridx = 3;
		gbc_txtMinPrice.gridy = 2;
		panel.add(txtMinPrice, gbc_txtMinPrice);
		
		txtMaxPrice = new JTextField();
		txtMaxPrice.setColumns(10);
		GridBagConstraints gbc_txtMaxPrice = new GridBagConstraints();
		gbc_txtMaxPrice.anchor = GridBagConstraints.WEST;
		gbc_txtMaxPrice.insets = new Insets(0, 0, 5, 5);
		gbc_txtMaxPrice.gridx = 4;
		gbc_txtMaxPrice.gridy = 2;
		panel.add(txtMaxPrice, gbc_txtMaxPrice);
		
		txtProductName = new JTextField();
		txtProductName.setColumns(10);
		GridBagConstraints gbc_txtProductName = new GridBagConstraints();
		gbc_txtProductName.insets = new Insets(0, 0, 5, 5);
		gbc_txtProductName.anchor = GridBagConstraints.WEST;
		gbc_txtProductName.gridx = 6;
		gbc_txtProductName.gridy = 2;
		panel.add(txtProductName, gbc_txtProductName);

		JButton btnSearch = new JButton("Søg");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
				
			}
		});
		
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.fill = GridBagConstraints.BOTH;
		gbc_btnSearch.gridx = 7;
		gbc_btnSearch.gridy = 2;
		panel.add(btnSearch, gbc_btnSearch);

		JButton btnUpdate = new JButton("Nulstil");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[][] data = convertToStringArray(ProductContainer.getInstance().getProducts());
				table.setNewData(data);
				resetFilter();
			}
		});
		
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.insets = new Insets(0, 0, 5, 5);
		gbc_btnUpdate.fill = GridBagConstraints.BOTH;
		gbc_btnUpdate.gridx = 8;
		gbc_btnUpdate.gridy = 2;
		panel.add(btnUpdate, gbc_btnUpdate);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.EAST);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JButton btnAdd = new JButton("Tilføj");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductInformation pi = new ProductInformation(null, true);
				pi.setVisible(true);
				int size = pc.getAllProducts().size();
				pi.addWindowListener(new WindowAdapter() {
		            @Override
		            public void windowClosed(WindowEvent e) {
		            	int newSize = pc.getAllProducts().size();
		            	if(size == newSize) return;
		            	int result = GUIPopUpMessages.customBox("Vil du opdatere produktoversigten?", "Opdater Produktoversigt",
		            			new Object[] {"Ja", "Nej"}, JOptionPane.QUESTION_MESSAGE);
		            	if(result == 0) {
		            		search();
		            	}
		                System.out.println("B has closed");
		            }

		        });
				
			}
		});
		
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 1;
		panel_1.add(btnAdd, gbc_btnAdd);

		JButton btnEdit = new JButton("Rediger");
		btnEdit.setToolTipText("Du skal vælge et produkt først");
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showProduct(true);
			}
		});
		
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEdit.insets = new Insets(0, 0, 5, 0);
		gbc_btnEdit.gridx = 0;
		gbc_btnEdit.gridy = 2;
		panel_1.add(btnEdit, gbc_btnEdit);

		JButton btnDetails = new JButton("Detaljer");
		btnDetails.setToolTipText("Du skal vælge et produkt først");
		btnDetails.setEnabled(false);
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showProduct(false);
			}
		});

		GridBagConstraints gbc_btnDetails = new GridBagConstraints();
		gbc_btnDetails.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDetails.insets = new Insets(0, 0, 5, 0);
		gbc_btnDetails.gridx = 0;
		gbc_btnDetails.gridy = 3;
		panel_1.add(btnDetails, gbc_btnDetails);

		JButton btnDelete = new JButton("Slet");
		btnDelete.setToolTipText("Du skal vælge et produkt først");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteData();
				rowCounter();
			}
		});

		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.gridx = 0;
		gbc_btnDelete.gridy = 4;
		panel_1.add(btnDelete, gbc_btnDelete);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] {700, 30, 63, 59, 0};
		gbl_panel_2.rowHeights = new int[] { 21, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		JButton btnClose = new JButton("Afslut");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});

		lblRowCounter = new JLabel("Antal: " + table.getRowCount());
		GridBagConstraints gbc_lblRowCounter = new GridBagConstraints();
		gbc_lblRowCounter.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRowCounter.insets = new Insets(0, 0, 0, 5);
		gbc_lblRowCounter.gridx = 0;
		gbc_lblRowCounter.gridy = 0;
		panel_2.add(lblRowCounter, gbc_lblRowCounter);

		GridBagConstraints gbc_btnClose = new GridBagConstraints();
		gbc_btnClose.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnClose.anchor = GridBagConstraints.NORTH;
		gbc_btnClose.gridx = 3;
		gbc_btnClose.gridy = 0;
		panel_2.add(btnClose, gbc_btnClose);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int[] rows = table.getSelectedRows();
				if(rows.length != 0) {
					btnDelete.setToolTipText(null);
					btnDelete.setEnabled(true);
					btnEdit.setToolTipText(null);
					btnEdit.setEnabled(true);
					btnDetails.setToolTipText(null);
					btnDetails.setEnabled(true);
				} else {
					btnDelete.setToolTipText("Du skal vælge et produkt først");
					btnDelete.setEnabled(false);
					btnEdit.setToolTipText("Du skal vælge et produkt først");
					btnEdit.setEnabled(false);
					btnDetails.setToolTipText("Du skal vælge et produkt først");
					btnDetails.setEnabled(false);
				}
			}
		});
	}
}