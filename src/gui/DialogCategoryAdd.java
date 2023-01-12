package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogCategoryAdd extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNewCategory;
	private JButton btnAddCategory;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogCategoryAdd dialog = new DialogCategoryAdd();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogCategoryAdd() {
		setTitle("Tilføj kategorier");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("Vælg fra liste");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 1;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			String[] items = { "", "Køkken", "Rør", "VVS", "Bolig", "Opbevaring", "Rengøring", "Sikkerhed", "Batterier",
					"Lamper", "Elektronik", "Pærer", "Fiskeri", "Camping", "Båd", "Bil", "Gulve", "Fliser", "Tæpper",
					"Grill", "Haveredskaber", "Havemøbler", "Maling", "Havemaskiner", "Værktøj", "Radiatorer",
					"Indeklima", "Byggeplader", "Konstruktion", "Lister", "Loft", "Vinduer", "Døre", "Arbejdstøj",
					"Søm", "Skruer", "Beslag", "Diverse" };
			comboBox = new JComboBox(items);
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 2;
			gbc_comboBox.gridy = 1;
			contentPanel.add(comboBox, gbc_comboBox);
		}
		{
			btnAddCategory = new JButton("Tilføj kategori");
			btnAddCategory.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addCategory();
				}
			});
			GridBagConstraints gbc_btnAddCategory = new GridBagConstraints();
			gbc_btnAddCategory.insets = new Insets(0, 0, 5, 5);
			gbc_btnAddCategory.gridx = 3;
			gbc_btnAddCategory.gridy = 1;
			contentPanel.add(btnAddCategory, gbc_btnAddCategory);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Ny kategori");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_1.gridx = 1;
			gbc_lblNewLabel_1.gridy = 2;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			txtNewCategory = new JTextField();
			GridBagConstraints gbc_txtNewCategory = new GridBagConstraints();
			gbc_txtNewCategory.insets = new Insets(0, 0, 5, 5);
			gbc_txtNewCategory.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtNewCategory.gridx = 2;
			gbc_txtNewCategory.gridy = 2;
			contentPanel.add(txtNewCategory, gbc_txtNewCategory);
			txtNewCategory.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnOk = new JButton("OK");
				btnOk.setActionCommand("OK");
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
			{
				JButton btnCancel = new JButton("Afbryd");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						closeWindow();
					}
				});
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
	}
	
	public void addCategory() {
		System.out.println(comboBox.getSelectedItem());
	}
	
	public void closeWindow() {
		this.dispose();
		this.setVisible(false);
	}

}
