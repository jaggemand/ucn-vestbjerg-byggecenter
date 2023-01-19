package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CustomerController;
import model.Customer.customerType;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.JProgressBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class CustomerInformationDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtDeliveryAddress;
	private JTextField txtPostalCode;
	private JTextField txtPhoneNumber;
	private JTextField txtEmail;
	private JTextField txtPaymentAddress;
	private JTextField txtCredit;
	private JTextField txtCompanyName;
	private JCheckBox chckBoxBusiness;
	private JCheckBox chckBoxPrivate;
	private JCheckBox chckBoxChangeAddress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CustomerInformationDialog dialog = new CustomerInformationDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CustomerInformationDialog() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Kundeinformation");
		setBounds(100, 100, 650, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 15, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("Navn");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("Kredit");
			GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
			gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_6.gridx = 3;
			gbc_lblNewLabel_6.gridy = 0;
			contentPanel.add(lblNewLabel_6, gbc_lblNewLabel_6);
		}
		{
			txtName = new JTextField();
			GridBagConstraints gbc_txtName = new GridBagConstraints();
			gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtName.insets = new Insets(0, 0, 5, 5);
			gbc_txtName.gridx = 1;
			gbc_txtName.gridy = 1;
			contentPanel.add(txtName, gbc_txtName);
			txtName.setColumns(10);
		}
		{
			txtCredit = new JTextField();
			GridBagConstraints gbc_txtCredit = new GridBagConstraints();
			gbc_txtCredit.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCredit.insets = new Insets(0, 0, 5, 5);
			gbc_txtCredit.gridx = 3;
			gbc_txtCredit.gridy = 1;
			contentPanel.add(txtCredit, gbc_txtCredit);
			txtCredit.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Leveringsadresse");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 1;
			gbc_lblNewLabel_1.gridy = 2;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_7 = new JLabel("Firma navn");
			GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
			gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_7.gridx = 3;
			gbc_lblNewLabel_7.gridy = 2;
			contentPanel.add(lblNewLabel_7, gbc_lblNewLabel_7);
		}
		{
			txtDeliveryAddress = new JTextField();
			GridBagConstraints gbc_txtDeliveryAddress = new GridBagConstraints();
			gbc_txtDeliveryAddress.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtDeliveryAddress.insets = new Insets(0, 0, 5, 5);
			gbc_txtDeliveryAddress.gridx = 1;
			gbc_txtDeliveryAddress.gridy = 3;
			contentPanel.add(txtDeliveryAddress, gbc_txtDeliveryAddress);
			txtDeliveryAddress.setColumns(10);
		}
		{
			txtCompanyName = new JTextField();
			GridBagConstraints gbc_txtCompanyName = new GridBagConstraints();
			gbc_txtCompanyName.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCompanyName.insets = new Insets(0, 0, 5, 5);
			gbc_txtCompanyName.gridx = 3;
			gbc_txtCompanyName.gridy = 3;
			contentPanel.add(txtCompanyName, gbc_txtCompanyName);
			txtCompanyName.setColumns(10);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Faktureringsadresse");
			GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
			gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_5.gridx = 1;
			gbc_lblNewLabel_5.gridy = 4;
			contentPanel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		}
		{
			txtPaymentAddress = new JTextField();
			GridBagConstraints gbc_txtPaymentAddress = new GridBagConstraints();
			gbc_txtPaymentAddress.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtPaymentAddress.insets = new Insets(0, 0, 5, 5);
			gbc_txtPaymentAddress.gridx = 1;
			gbc_txtPaymentAddress.gridy = 5;
			contentPanel.add(txtPaymentAddress, gbc_txtPaymentAddress);
			txtPaymentAddress.setColumns(10);
		}
		{
			chckBoxChangeAddress = new JCheckBox("Ændring af adresse?");
			chckBoxChangeAddress.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean status = chckBoxChangeAddress.isSelected() ? true : false;
					txtPaymentAddress.setEnabled(status);
				}
			});
			GridBagConstraints gbc_chckBoxChangeAddress = new GridBagConstraints();
			gbc_chckBoxChangeAddress.anchor = GridBagConstraints.WEST;
			gbc_chckBoxChangeAddress.insets = new Insets(0, 0, 5, 5);
			gbc_chckBoxChangeAddress.gridx = 3;
			gbc_chckBoxChangeAddress.gridy = 5;
			contentPanel.add(chckBoxChangeAddress, gbc_chckBoxChangeAddress);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Postnummer");
			GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
			gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_4.gridx = 1;
			gbc_lblNewLabel_4.gridy = 6;
			contentPanel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		}
		{
			txtPostalCode = new JTextField();
			GridBagConstraints gbc_txtPostalCode = new GridBagConstraints();
			gbc_txtPostalCode.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtPostalCode.insets = new Insets(0, 0, 5, 5);
			gbc_txtPostalCode.gridx = 1;
			gbc_txtPostalCode.gridy = 7;
			contentPanel.add(txtPostalCode, gbc_txtPostalCode);
			txtPostalCode.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Telefonnummer");
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_2.gridx = 1;
			gbc_lblNewLabel_2.gridy = 8;
			contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		}
		{
			txtPhoneNumber = new JTextField();
			GridBagConstraints gbc_txtPhoneNumber = new GridBagConstraints();
			gbc_txtPhoneNumber.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtPhoneNumber.insets = new Insets(0, 0, 5, 5);
			gbc_txtPhoneNumber.gridx = 1;
			gbc_txtPhoneNumber.gridy = 9;
			contentPanel.add(txtPhoneNumber, gbc_txtPhoneNumber);
			txtPhoneNumber.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Email");
			GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
			gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_3.gridx = 1;
			gbc_lblNewLabel_3.gridy = 10;
			contentPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		}
		{
			txtEmail = new JTextField();
			GridBagConstraints gbc_txtEmail = new GridBagConstraints();
			gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
			gbc_txtEmail.gridx = 1;
			gbc_txtEmail.gridy = 11;
			contentPanel.add(txtEmail, gbc_txtEmail);
			txtEmail.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[] { 22, 42, 0, 0, 89, 63, 0, 0 };
			gbl_buttonPane.rowHeights = new int[] { 21, 0, 0 };
			gbl_buttonPane.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			gbl_buttonPane.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
			buttonPane.setLayout(gbl_buttonPane);
			{
				JLabel lblNewLabel_10 = new JLabel("");
				GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
				gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_10.gridx = 0;
				gbc_lblNewLabel_10.gridy = 0;
				buttonPane.add(lblNewLabel_10, gbc_lblNewLabel_10);
			}
			{
				JLabel lblNewLabel_8 = new JLabel("Status:");
				GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
				gbc_lblNewLabel_8.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_8.gridx = 1;
				gbc_lblNewLabel_8.gridy = 0;
				buttonPane.add(lblNewLabel_8, gbc_lblNewLabel_8);
			}
			{
				JLabel lblStatusText = new JLabel("OK");
				lblStatusText.setForeground(new Color(0, 179, 45));
				GridBagConstraints gbc_lblStatusText = new GridBagConstraints();
				gbc_lblStatusText.anchor = GridBagConstraints.WEST;
				gbc_lblStatusText.insets = new Insets(0, 0, 5, 5);
				gbc_lblStatusText.gridx = 2;
				gbc_lblStatusText.gridy = 0;
				buttonPane.add(lblStatusText, gbc_lblStatusText);
			}
			{
				JButton btnOK = new JButton("Opret Kunde");
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addCustomer();
					}
				});
				btnOK.setActionCommand("OK");
				GridBagConstraints gbc_btnOK = new GridBagConstraints();
				gbc_btnOK.anchor = GridBagConstraints.NORTHWEST;
				gbc_btnOK.insets = new Insets(0, 0, 5, 5);
				gbc_btnOK.gridx = 4;
				gbc_btnOK.gridy = 0;
				buttonPane.add(btnOK, gbc_btnOK);
				getRootPane().setDefaultButton(btnOK);
			}
			{
				JButton btnCancel = new JButton("Afbryd");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						closeWindow();
					}
				});
				btnCancel.setActionCommand("Cancel");
				GridBagConstraints gbc_btnCancel = new GridBagConstraints();
				gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
				gbc_btnCancel.anchor = GridBagConstraints.NORTHWEST;
				gbc_btnCancel.gridx = 5;
				gbc_btnCancel.gridy = 0;
				buttonPane.add(btnCancel, gbc_btnCancel);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.WEST);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] { 101, 0 };
			gbl_panel.rowHeights = new int[] { 0, 0, 0, 0 };
			gbl_panel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
			gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
			panel.setLayout(gbl_panel);
			{
				JLabel lblFilter = new JLabel("Kunde type");
				lblFilter.setFont(new Font("Tahoma", Font.BOLD, 14));
				GridBagConstraints gbc_lblFilter = new GridBagConstraints();
				gbc_lblFilter.insets = new Insets(0, 0, 5, 0);
				gbc_lblFilter.anchor = GridBagConstraints.WEST;
				gbc_lblFilter.gridx = 0;
				gbc_lblFilter.gridy = 0;
				panel.add(lblFilter, gbc_lblFilter);
			}
			{
				chckBoxPrivate = new JCheckBox("Privat kunde");
				chckBoxPrivate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						handleSelectionChange(true);
					}
				});
				GridBagConstraints gbc_chckBoxPrivate = new GridBagConstraints();
				gbc_chckBoxPrivate.insets = new Insets(0, 0, 5, 0);
				gbc_chckBoxPrivate.gridx = 0;
				gbc_chckBoxPrivate.gridy = 1;
				panel.add(chckBoxPrivate, gbc_chckBoxPrivate);
			}
			{
				chckBoxBusiness = new JCheckBox("Erhverskunde");
				chckBoxBusiness.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						handleSelectionChange(false);
					}
				});
				GridBagConstraints gbc_chckBoxBusiness = new GridBagConstraints();
				gbc_chckBoxBusiness.gridx = 0;
				gbc_chckBoxBusiness.gridy = 2;
				panel.add(chckBoxBusiness, gbc_chckBoxBusiness);
			}
		}
		init();
	}

	private void init() {
		txtName.setEnabled(false);
		txtDeliveryAddress.setEnabled(false);
		txtPaymentAddress.setEnabled(false);
		txtPostalCode.setEnabled(false);
		txtPhoneNumber.setEnabled(false);
		txtEmail.setEnabled(false);
		txtCredit.setEnabled(false);
		txtCompanyName.setEnabled(false);
	}

	private void addCustomer() {
		CustomerController customerController = new CustomerController();
		boolean success = false;
		String name = txtName.getText();
		String deliveryAddress = txtDeliveryAddress.getText();
		String paymentAddress = txtPaymentAddress.getText();
		String postalCode = txtPostalCode.getText();
		String phone = txtPhoneNumber.getText();
		String email = txtEmail.getText();
		String companyName = txtCompanyName.getText();
		customerType accountType = chckBoxPrivate.isSelected() ? customerType.PRIVATE : customerType.BUSINESS;
		Double credit = 0.0;
		try {
			credit = Double.parseDouble(txtCredit.getText());
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (!chckBoxChangeAddress.isSelected() && paymentAddress.isBlank()) {
			paymentAddress = deliveryAddress;
		}

		if (chckBoxPrivate.isSelected() && !name.isBlank() && !deliveryAddress.isBlank() && !postalCode.isBlank()
				&& !phone.isBlank() && !email.isBlank()) {
			success = true;
		} else if (chckBoxBusiness.isSelected() && !deliveryAddress.isBlank() && !postalCode.isBlank()
				&& !phone.isBlank() && !email.isBlank() && !credit.isNaN() && !companyName.isBlank()) {
			success = true;
		}
		if (success) {
			customerController.createCustomer(name, deliveryAddress, paymentAddress, phone, email, credit, postalCode,
					companyName, accountType);
			GUIPopUpMessages.informationMessage("Kunden er blevet oprettet", "Succes!");
			closeWindow();
		} else {
			GUIPopUpMessages.warningMessage("Kunden er ikke blevet oprettet", "Fejl!");
		}
	}

	private void handleSelectionChange(boolean customerType) {
		if (customerType) {
			chckBoxBusiness.setSelected(false);
			chckBoxPrivate.setSelected(true);

			txtName.setEnabled(true);
			txtCredit.setEnabled(false);
			txtCompanyName.setEnabled(false);
		} else {
			chckBoxPrivate.setSelected(false);
			chckBoxBusiness.setSelected(true);

			txtName.setEnabled(false);
			txtCredit.setEnabled(true);
			txtCompanyName.setEnabled(true);
		}
		txtPostalCode.setEnabled(true);
		txtDeliveryAddress.setEnabled(true);
		txtPaymentAddress.setEnabled(false);
		txtPhoneNumber.setEnabled(true);
		txtEmail.setEnabled(true);
	}

	private void closeWindow() {
		this.dispose();
		this.setVisible(false);
	}

}
