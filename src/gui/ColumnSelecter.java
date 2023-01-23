package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ColumnSelecter extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ArrayList<JCheckBox> checkBox;
	private ArrayList<Boolean> isVisible;
	private ArrayList<String> columns;
	private CustomTableColumnManager manager;

	/**
	 * Create the dialog.
	 * 
	 */
	public ColumnSelecter(CustomTableColumnManager manager) {
		this.manager = manager;
	}
	public void newWindow(ArrayList<Boolean> isVisible, ArrayList<String> columns) {
		checkBox = new ArrayList<>();
		setTitle("Aktive kolonner");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		for(int i = 0; i < columns.size();i++) {
			JCheckBox temp = new JCheckBox(columns.get(i));
			contentPanel.add(temp);
			checkBox.add(temp);
		}
		
		this.columns = columns;
		this.isVisible = isVisible;
		setModal(true);
		
		
		{
			
			int rowCount = 5;
			if(columns.size() > 5) {
				rowCount = columns.size()/2;
			}
			contentPanel.setLayout(new GridLayout(rowCount,4));
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnNewButton = new JButton("Vis alt");
				btnNewButton.setMaximumSize(new Dimension(20, 20));
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						selectAll();
					}
				});
				;
				btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
				buttonPane.add(btnNewButton);
			}
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
		pack();
		setCheckBoxes();
	}
	
		private void setCheckBoxes() {
			for(int i = 0; i < isVisible.size(); i++) {
					checkBox.get(i).setSelected(isVisible.get(i));
			}
			
		}
		
		private void selectAll() {
			for(int i = 0; i < isVisible.size(); i++) {
					checkBox.get(i).setSelected(true);
			}
		}
		
		private void setColumnFromcheckBoxes() {
			for(int i = 0; i < isVisible.size(); i++) {
				if(checkBox.get(i).isSelected() != isVisible.get(i)) {
					if (checkBox.get(i).isSelected()) {
						manager.showColumn(columns.get(i));
					} else {
						manager.hideColumn(columns.get(i));
					}
				}
			}
			this.dispose();
		}
		
		private void closeWindow() {
			this.dispose();
		}
}