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
	private boolean[] isVisible;
	private ProductOverview frame;

	/**
	 * Create the dialog.
	 * 
	 */
	public ColumnSelecter(boolean[] isVisible, String[] columns, JFrame frame) {
		checkBox = new ArrayList<>();
		this.frame = (ProductOverview) frame;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		for(int i = 1; i < columns.length;i++) {
			JCheckBox temp = new JCheckBox(columns[i]);
			contentPanel.add(temp);
			checkBox.add(temp);
		}
		
		
		this.isVisible = isVisible;
		setModal(true);
		
		
		{
			JButton btnNewButton = new JButton("Vis alt");
			btnNewButton.setMaximumSize(new Dimension(20, 20));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selectAll();
				}
			});
			contentPanel.setLayout(new GridLayout(7,4));
			btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
			contentPanel.add(btnNewButton);
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
			for(int i = 0; i < isVisible.length-1; i++) {
				if (isVisible[i+1]) {
					checkBox.get(i).setSelected(true);
				}
			}
			
		}
		
		private void selectAll() {
			for(int i = 0; i < isVisible.length-1; i++) {
					checkBox.get(i).setSelected(true);
			}
		}
		
		private void setColumnFromcheckBoxes() {
			for(int i = 0; i < isVisible.length-1; i++) {
				if (checkBox.get(i).isSelected()) {
					isVisible[i+1] = true;
				} else {
					isVisible[i+1] = false;
				}
			}
			frame.setColumns(isVisible);
			this.dispose();
		}
		
		private void closeWindow() {
			this.dispose();
		}
}