package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.CustomerController;
import model.Customer;

public class DialogCustomerAdd extends JDialog {
	
	//WindowBuilder Elements
	private JTextField textFieldPhone;
	private JTextField textFieldCustomerName;
	private JButton btnOK;
	
	//Customer Field and Controller
	private Customer newCustomer;
	private CustomerController cController;

	/**
	 * Constructor, initializes the Dialog
	 * @param frame The Frame that called the constructor
	 */
	public DialogCustomerAdd(JFrame frame) {
		super(frame);
		cController = new CustomerController();
		newCustomer = null;
		
		setResizable(false);
		setTitle("Tilføj kunde");
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
				JLabel lblNewLabel = new JLabel("Telefonnummer");
				lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.gridx = 1;
				gbc_lblNewLabel.gridy = 1;
				panel.add(lblNewLabel, gbc_lblNewLabel);
			}
			{
				textFieldPhone = new JTextField();
				textFieldPhone.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						if(e.getKeyCode() == KeyEvent.VK_ENTER) {
							buttonSearchPressed();
						}
					}
				});
				GridBagConstraints gbc_textFieldPhone = new GridBagConstraints();
				gbc_textFieldPhone.insets = new Insets(0, 0, 5, 5);
				gbc_textFieldPhone.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldPhone.gridx = 2;
				gbc_textFieldPhone.gridy = 1;
				panel.add(textFieldPhone, gbc_textFieldPhone);
				textFieldPhone.setColumns(10);
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
				JLabel lblNewLabel_1 = new JLabel("Navn");
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.gridx = 1;
				gbc_lblNewLabel_1.gridy = 2;
				panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			}
			{
				textFieldCustomerName = new JTextField();
				textFieldCustomerName.setText("Ingen kunde");
				textFieldCustomerName.setEditable(false);
				GridBagConstraints gbc_textFieldCustomerName = new GridBagConstraints();
				gbc_textFieldCustomerName.insets = new Insets(0, 0, 5, 5);
				gbc_textFieldCustomerName.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldCustomerName.gridx = 2;
				gbc_textFieldCustomerName.gridy = 2;
				panel.add(textFieldCustomerName, gbc_textFieldCustomerName);
				textFieldCustomerName.setColumns(10);
			}
		}
	}
	
	/**
	 * The Button Search was Pressed, checks if customer exists
	 */
	private void buttonSearchPressed() {
		String phone = textFieldPhone.getText();
		newCustomer = cController.findCustomerByInformation(phone);
		
		if(newCustomer != null) {
			//Customer found
			btnOK.setEnabled(true);
			textFieldCustomerName.setText(newCustomer.getName());
		}
		else {
			//Customer not found
			btnOK.setEnabled(false);
			textFieldCustomerName.setText("Kunde ikke fundet");
		}
	}
	
	/**
	 * Button Add was pressed and the Window is disposed
	 */
	private void buttonAddPressed() {
		this.dispose();
	}
	
	/**
	 * The Button Cancel was pressed and the customer is set to null
	 * Dialog is disposed
	 */
	private void buttonCancelPressed() {
		newCustomer = null;
		this.dispose();
	}

	/**
	 * Get the customer that was found
	 * @return The customer that was found
	 */
	public Customer getNewCustomer() {
		return newCustomer;
	}
}