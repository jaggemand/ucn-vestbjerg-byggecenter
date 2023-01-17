package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ColumnSelecter extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ArrayList<JCheckBox> checkBox;
	private boolean[] isVisible;
	private ProductOverview frame;

	/**
	 * Create the dialog.
	 */
	public ColumnSelecter(boolean[] isVisible, ProductOverview frame) {
		
		checkBox = new ArrayList<>();
		this.isVisible = isVisible;
		this.frame = frame;
		setModal(true);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JCheckBox chckbxName = new JCheckBox("Navn");
			checkBox.add(chckbxName);
			chckbxName.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_chckbxName = new GridBagConstraints();
			gbc_chckbxName.anchor = GridBagConstraints.WEST;
			gbc_chckbxName.insets = new Insets(0, 0, 5, 5);
			gbc_chckbxName.gridx = 1;
			gbc_chckbxName.gridy = 0;
			contentPanel.add(chckbxName, gbc_chckbxName);
		}
		{
			JCheckBox chckbxButiksBeholdning = new JCheckBox("Butiks beholdning");
			checkBox.add(chckbxButiksBeholdning);
			chckbxButiksBeholdning.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_chckbxButiksBeholdning = new GridBagConstraints();
			gbc_chckbxButiksBeholdning.anchor = GridBagConstraints.WEST;
			gbc_chckbxButiksBeholdning.insets = new Insets(0, 0, 5, 5);
			gbc_chckbxButiksBeholdning.gridx = 1;
			gbc_chckbxButiksBeholdning.gridy = 1;
			contentPanel.add(chckbxButiksBeholdning, gbc_chckbxButiksBeholdning);
		}
		{
			JCheckBox chckbxButiksLokation = new JCheckBox("Butiks lokation");
			checkBox.add(chckbxButiksLokation);
			chckbxButiksLokation.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_chckbxButiksLokation = new GridBagConstraints();
			gbc_chckbxButiksLokation.anchor = GridBagConstraints.WEST;
			gbc_chckbxButiksLokation.insets = new Insets(0, 0, 5, 5);
			gbc_chckbxButiksLokation.gridx = 1;
			gbc_chckbxButiksLokation.gridy = 2;
			contentPanel.add(chckbxButiksLokation, gbc_chckbxButiksLokation);
		}
		{
			JCheckBox chckbxLagerBeholdning = new JCheckBox("Lager beholdning");
			checkBox.add(chckbxLagerBeholdning);
			chckbxLagerBeholdning.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_chckbxLagerBeholdning = new GridBagConstraints();
			gbc_chckbxLagerBeholdning.anchor = GridBagConstraints.WEST;
			gbc_chckbxLagerBeholdning.insets = new Insets(0, 0, 5, 5);
			gbc_chckbxLagerBeholdning.gridx = 1;
			gbc_chckbxLagerBeholdning.gridy = 3;
			contentPanel.add(chckbxLagerBeholdning, gbc_chckbxLagerBeholdning);
		}
		{
			JCheckBox chckbxLagerLokation = new JCheckBox("Lager lokation");
			checkBox.add(chckbxLagerLokation);
			chckbxLagerLokation.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_chckbxLagerLokation = new GridBagConstraints();
			gbc_chckbxLagerLokation.anchor = GridBagConstraints.WEST;
			gbc_chckbxLagerLokation.insets = new Insets(0, 0, 5, 5);
			gbc_chckbxLagerLokation.gridx = 1;
			gbc_chckbxLagerLokation.gridy = 4;
			contentPanel.add(chckbxLagerLokation, gbc_chckbxLagerLokation);
		}
		{
			JCheckBox chckbxSalgspris = new JCheckBox("Salgspris");
			checkBox.add(chckbxSalgspris);
			chckbxSalgspris.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_chckbxSalgspris = new GridBagConstraints();
			gbc_chckbxSalgspris.anchor = GridBagConstraints.WEST;
			gbc_chckbxSalgspris.insets = new Insets(0, 0, 0, 5);
			gbc_chckbxSalgspris.gridx = 1;
			gbc_chckbxSalgspris.gridy = 5;
			contentPanel.add(chckbxSalgspris, gbc_chckbxSalgspris);
		}
		{
			JCheckBox chckbxStregkode = new JCheckBox("Stregkode");
			checkBox.add(chckbxStregkode);
			chckbxStregkode.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_chckbxStregkode = new GridBagConstraints();
			gbc_chckbxStregkode.anchor = GridBagConstraints.WEST;
			gbc_chckbxStregkode.insets = new Insets(0, 0, 5, 0);
			gbc_chckbxStregkode.gridx = 2;
			gbc_chckbxStregkode.gridy = 0;
			contentPanel.add(chckbxStregkode, gbc_chckbxStregkode);
		}
	
		{
			JCheckBox chckbxBeskrivelse = new JCheckBox("Beskrivelse");
			checkBox.add(chckbxBeskrivelse);
			chckbxBeskrivelse.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_chckbxBeskrivelse = new GridBagConstraints();
			gbc_chckbxBeskrivelse.anchor = GridBagConstraints.WEST;
			gbc_chckbxBeskrivelse.insets = new Insets(0, 0, 5, 0);
			gbc_chckbxBeskrivelse.gridx = 2;
			gbc_chckbxBeskrivelse.gridy = 1;
			contentPanel.add(chckbxBeskrivelse, gbc_chckbxBeskrivelse);
		}
		
		{
			JCheckBox chckbxKategori = new JCheckBox("Kategori");
			checkBox.add(chckbxKategori);
			chckbxKategori.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_chckbxKategori = new GridBagConstraints();
			gbc_chckbxKategori.anchor = GridBagConstraints.WEST;
			gbc_chckbxKategori.insets = new Insets(0, 0, 5, 0);
			gbc_chckbxKategori.gridx = 2;
			gbc_chckbxKategori.gridy = 2;
			contentPanel.add(chckbxKategori, gbc_chckbxKategori);
		}
	
		{
			JCheckBox chckbxKostpris = new JCheckBox("Kostpris");
			checkBox.add(chckbxKostpris);
			chckbxKostpris.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_chckbxKostpris = new GridBagConstraints();
			gbc_chckbxKostpris.anchor = GridBagConstraints.WEST;
			gbc_chckbxKostpris.insets = new Insets(0, 0, 5, 0);
			gbc_chckbxKostpris.gridx = 2;
			gbc_chckbxKostpris.gridy = 3;
			contentPanel.add(chckbxKostpris, gbc_chckbxKostpris);
		}
		
		{
			JCheckBox chckbxVejledendePris = new JCheckBox("Vejledende pris");
			checkBox.add(chckbxVejledendePris);
			chckbxVejledendePris.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_chckbxVejledendePris = new GridBagConstraints();
			gbc_chckbxVejledendePris.anchor = GridBagConstraints.WEST;
			gbc_chckbxVejledendePris.insets = new Insets(0, 0, 5, 0);
			gbc_chckbxVejledendePris.gridx = 2;
			gbc_chckbxVejledendePris.gridy = 4;
			contentPanel.add(chckbxVejledendePris, gbc_chckbxVejledendePris);
		}
		
		{
			JButton btnNewButton = new JButton("Vis alt");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selectAll();
				}
			});
			btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.gridx = 2;
			gbc_btnNewButton.gridy = 5;
			contentPanel.add(btnNewButton, gbc_btnNewButton);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setColumnFromcheckBoxes();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						closeWindow();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setCheckBoxes();
	}
	
		private void setCheckBoxes() {
			for(int i = 0; i < checkBox.size(); i++) {
				if (isVisible[i + 1]) {
					checkBox.get(i).setSelected(true);
				}
			}
			
		}
		
		private void selectAll() {
			for(int i = 0; i < checkBox.size(); i++) {
					checkBox.get(i).setSelected(true);
			}
		}
		
		private void setColumnFromcheckBoxes() {
			for(int i = 0; i < checkBox.size(); i++) {
				if (checkBox.get(i).isSelected()) {
					isVisible[i + 1] = true;
				} else {
					isVisible[i + 1] = false;
				}
			}
			frame.setColumns(isVisible);
			this.dispose();
		}
		
		private void closeWindow() {
			this.dispose();
		}
}