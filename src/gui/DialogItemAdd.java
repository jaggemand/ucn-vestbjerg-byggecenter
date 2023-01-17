package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ProductController;
import model.Product;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DialogItemAdd extends JDialog {
	private JTextField textFieldBarcode;
	private JTextField textFieldProductName;
	private JButton btnOK;
	private Product newProduct;
	private int amount;
	private ProductController pController;
	private JSpinner spinner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogItemAdd dialog = new DialogItemAdd();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogItemAdd() {
		
		pController = new ProductController();
		amount = 0;
		
		setResizable(false);
		setTitle("Tilføj produkt");
		setModal(true);
		setBounds(100, 100, 345, 163);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnOK = new JButton("Tilføj");
				btnOK.setEnabled(false);
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonAddPressed();
					}
				});
				btnOK.setActionCommand("OK");
				buttonPane.add(btnOK);
//				getRootPane().setDefaultButton(btnOK);
			}
			{
				JButton cancelButton = new JButton("Afbryd");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonCancelPressed();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{10, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblNewLabel = new JLabel("Stregkode");
				lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.gridx = 1;
				gbc_lblNewLabel.gridy = 1;
				panel.add(lblNewLabel, gbc_lblNewLabel);
			}
			{
				textFieldBarcode = new JTextField();
				textFieldBarcode.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						if(e.getKeyCode() == KeyEvent.VK_ENTER) {
							buttonSearchPressed();
						}
					}
				});
				GridBagConstraints gbc_textFieldBarcode = new GridBagConstraints();
				gbc_textFieldBarcode.insets = new Insets(0, 0, 5, 5);
				gbc_textFieldBarcode.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldBarcode.gridx = 2;
				gbc_textFieldBarcode.gridy = 1;
				panel.add(textFieldBarcode, gbc_textFieldBarcode);
				textFieldBarcode.setColumns(10);
			}
			{
				JButton btnSearch = new JButton("Søg");
				btnSearch.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonSearchPressed();
					}
				});
				GridBagConstraints gbc_btnSearch = new GridBagConstraints();
				gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
				gbc_btnSearch.gridx = 3;
				gbc_btnSearch.gridy = 1;
				panel.add(btnSearch, gbc_btnSearch);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Produktnavn");
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.gridx = 1;
				gbc_lblNewLabel_1.gridy = 2;
				panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			}
			{
				textFieldProductName = new JTextField();
				textFieldProductName.setText("Intet produkt");
				textFieldProductName.setEditable(false);
				GridBagConstraints gbc_textFieldProductName = new GridBagConstraints();
				gbc_textFieldProductName.insets = new Insets(0, 0, 5, 5);
				gbc_textFieldProductName.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldProductName.gridx = 2;
				gbc_textFieldProductName.gridy = 2;
				panel.add(textFieldProductName, gbc_textFieldProductName);
				textFieldProductName.setColumns(10);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Antal");
				lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
				GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
				gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_2.gridx = 1;
				gbc_lblNewLabel_2.gridy = 3;
				panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
			}
			{
				spinner = new JSpinner();
				spinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
				GridBagConstraints gbc_spinner = new GridBagConstraints();
				gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
				gbc_spinner.insets = new Insets(0, 0, 5, 5);
				gbc_spinner.gridx = 2;
				gbc_spinner.gridy = 3;
				panel.add(spinner, gbc_spinner);
			}
		}
	}
	private void buttonSearchPressed() {
		newProduct = pController.findProduct(textFieldBarcode.getText());
		if(newProduct != null) {
			btnOK.setEnabled(true);
			getRootPane().setDefaultButton(btnOK);
			textFieldProductName.setText(newProduct.getName());
		}
		else {
			btnOK.setEnabled(false);
			textFieldProductName.setText("Stregkode ugyldig");
		}
	}
	private void buttonAddPressed() {
		amount = (int) spinner.getModel().getValue();
		this.dispose();
	}
	private void buttonCancelPressed() {
		newProduct = null;
		this.dispose();
	}
	
	public Product getNewProduct() {
		return newProduct;
	}
	public int getAmount() {
		return amount;
	}

}
