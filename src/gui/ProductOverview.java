package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import model.Product;
import model.ProductContainer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductOverview extends JFrame {

	private JPanel contentPane;
	private static ProductOverview frame;
	private JTable table;
	private JTextField txtNavn;
	private JTextField txtPris;
	private JTextField textField_2;

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
		setBounds(100, 100, 850, 600);
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
		for(int i = 0; i < size;i++) {
			Product current = dataArrayList.get(i);
			data[i][0] = current.getProductID();
			data[i][1] = current.getName();
			data[i][2] = Integer.toString(current.getStorageAmount());
			data[i][3] = current.getStorageLocation();
			data[i][4] = Integer.toString(current.getWarehouseAmount());
			data[i][5] = current.getWarehouseLocation();
		}
		DefaultTableModel tabelmodel = new DefaultTableModel(data, columns);
		table = new JTable(tabelmodel) {
		    public Component prepareRenderer(
		            TableCellRenderer renderer, int row, int column)
		        {
		            Component c = super.prepareRenderer(renderer, row, column);
		            if (!isRowSelected(row))
		                c.setBackground(row % 2 == 0 ? getBackground() : Color.LIGHT_GRAY);

		            return c;
		        }
		};
		table.setDefaultEditor(Object.class, null);
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
		
		JLabel lblHeader = new JLabel("Filtre");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_lblHeader = new GridBagConstraints();
		gbc_lblHeader.anchor = GridBagConstraints.WEST;
		gbc_lblHeader.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeader.gridx = 1;
		gbc_lblHeader.gridy = 0;
		panel.add(lblHeader, gbc_lblHeader);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Butik");
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.anchor = GridBagConstraints.NORTHWEST;
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox.gridx = 2;
		gbc_chckbxNewCheckBox.gridy = 1;
		panel.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		
		JLabel lblNewLabel_2 = new JLabel("Min Pris");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 1;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Max Pris");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 4;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Produkt Navn");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 6;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Søg");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.gridx = 11;
		gbc_btnNewButton_1.gridy = 1;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.anchor = GridBagConstraints.NORTHWEST;
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 2;
		panel.add(comboBox, gbc_comboBox);
		comboBox.addItem("Kategorier");
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Lager");
		GridBagConstraints gbc_chckbxNewCheckBox_1 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_chckbxNewCheckBox_1.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxNewCheckBox_1.gridx = 2;
		gbc_chckbxNewCheckBox_1.gridy = 2;
		panel.add(chckbxNewCheckBox_1, gbc_chckbxNewCheckBox_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 0, 5);
		gbc_textField_2.anchor = GridBagConstraints.WEST;
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 2;
		panel.add(textField_2, gbc_textField_2);
		
		txtPris = new JTextField();
		txtPris.setColumns(10);
		GridBagConstraints gbc_txtPris = new GridBagConstraints();
		gbc_txtPris.anchor = GridBagConstraints.WEST;
		gbc_txtPris.insets = new Insets(0, 0, 0, 5);
		gbc_txtPris.gridx = 4;
		gbc_txtPris.gridy = 2;
		panel.add(txtPris, gbc_txtPris);
		
		txtNavn = new JTextField();
		txtNavn.setColumns(10);
		GridBagConstraints gbc_txtNavn = new GridBagConstraints();
		gbc_txtNavn.insets = new Insets(0, 0, 0, 5);
		gbc_txtNavn.anchor = GridBagConstraints.WEST;
		gbc_txtNavn.gridx = 6;
		gbc_txtNavn.gridy = 2;
		panel.add(txtNavn, gbc_txtNavn);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.EAST);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JButton btnNewButton_2 = new JButton("Tilføj");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 1;
		panel_1.add(btnNewButton_2, gbc_btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Rediger");
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_3.gridx = 0;
		gbc_btnNewButton_3.gridy = 2;
		panel_1.add(btnNewButton_3, gbc_btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Detaljer");
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_4.gridx = 0;
		gbc_btnNewButton_4.gridy = 3;
		panel_1.add(btnNewButton_4, gbc_btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Slet");
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_5.gridx = 0;
		gbc_btnNewButton_5.gridy = 4;
		panel_1.add(btnNewButton_5, gbc_btnNewButton_5);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnNewButton = new JButton("Afslut");
		panel_2.add(btnNewButton);
	}

}
