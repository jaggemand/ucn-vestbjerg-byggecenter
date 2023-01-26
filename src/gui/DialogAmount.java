package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.OrderLine;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DialogAmount extends JDialog {

	//Windowbuilder elements
	private final JPanel contentPanel = new JPanel();
	private JSpinner spinner;
	private JTextField txtProductName;
	
	//Data and elements
	private OrderLine orderLine;
	private String newAmountString;
	private int newAmount;

	/**
	 * Initializes the Dialog
	 * @param frame The Frame that called the constructor
	 * @param orderLine The specific orderline
	 */
	public DialogAmount(JFrame frame, OrderLine orderLine) {
		super(frame);
		this.orderLine = orderLine;
		newAmount = 0;
		setTitle("Vælg total antal");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 234, 147);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 75, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblNewLabel_1 = new JLabel("Produktnavn:");
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.gridx = 1;
				gbc_lblNewLabel_1.gridy = 1;
				panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			}
			{
				txtProductName = new JTextField(orderLine.getProduct().getName());
				txtProductName.setEditable(false);
				GridBagConstraints gbc_txtProductName = new GridBagConstraints();
				gbc_txtProductName.insets = new Insets(0, 0, 5, 5);
				gbc_txtProductName.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtProductName.gridx = 2;
				gbc_txtProductName.gridy = 1;
				panel.add(txtProductName, gbc_txtProductName);
				txtProductName.setColumns(10);
			}
			{
				JLabel lblNewLabel = new JLabel("Antal:");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.gridx = 1;
				gbc_lblNewLabel.gridy = 2;
				panel.add(lblNewLabel, gbc_lblNewLabel);
			}
			{
				spinner = new JSpinner();
				spinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
				GridBagConstraints gbc_spinner = new GridBagConstraints();
				gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
				gbc_spinner.insets = new Insets(0, 0, 5, 5);
				gbc_spinner.gridx = 2;
				gbc_spinner.gridy = 2;
				panel.add(spinner, gbc_spinner);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonOKPressed();
					}

					
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
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
		initialize();
	}
	
	/**
	 * Sets the Spinner model
	 */
	private void initialize() {
		spinner.setModel(new SpinnerNumberModel(orderLine.getQuantity(), Integer.valueOf(1), null, Integer.valueOf(1)));
	}

	/**
	 * The Button OK was pressed
	 * Checks if Spinner input was a number
	 */
	private void buttonOKPressed() {
		try {
			newAmountString = spinner.getValue() + "";
			
			newAmount = Integer.parseInt(newAmountString);
			
			orderLine.setQuantity(newAmount);
			this.dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),"Antal skal være et helt tal (positivt)");
		}
		
		
	}
	
	/**
	 * The button cancel was pressed, disposes of Dialog
	 */
	private void buttonCancelPressed() {
		newAmount = -1;
		this.dispose();
	}
	
	/**
	 * Returns the amount in Spinner
	 * @return The User input amount
	 */
	public int getNewAmount() {
		return newAmount;
	}

}
