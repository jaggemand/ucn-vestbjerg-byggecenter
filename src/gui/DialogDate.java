package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;

public class DialogDate extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JSpinner spnDateFrom;
	private JSpinner spnDateTo;
	
	private Date dateFrom;
	private Date dateTo;
	
	private boolean okPressed = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogDate dialog = new DialogDate(null, new Date(), new Date());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogDate(JFrame frame, Date presetDateFrom, Date presetDateTo) {
		super(frame);
		this.dateFrom = presetDateFrom;
		this.dateTo = presetDateTo;
		setModal(true);
		setTitle("Vælg periode");
		setBounds(100, 100, 196, 171);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				spnDateTo = new JSpinner();
				Date today = new Date();
				{
					JLabel lblNewLabel_2 = new JLabel("Vælg periode");
					GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
					gbc_lblNewLabel_2.gridwidth = 3;
					gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
					gbc_lblNewLabel_2.gridx = 1;
					gbc_lblNewLabel_2.gridy = 1;
					panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
				}
				{
					JLabel lblNewLabel_1 = new JLabel("Dato fra:");
					GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
					gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
					gbc_lblNewLabel_1.gridx = 1;
					gbc_lblNewLabel_1.gridy = 2;
					panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
				}
				{
					JLabel lblNewLabel = new JLabel("Dato til:");
					GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
					gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
					gbc_lblNewLabel.gridx = 3;
					gbc_lblNewLabel.gridy = 2;
					panel.add(lblNewLabel, gbc_lblNewLabel);
				}
				{
					spnDateFrom = new JSpinner();
					if(this.dateFrom != null) {
						spnDateFrom.setModel(new SpinnerDateModel(this.dateFrom, null, null, Calendar.MILLISECOND));
					}
					else {
						spnDateFrom.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.MILLISECOND));
					}
					GridBagConstraints gbc_spnDateFrom = new GridBagConstraints();
					gbc_spnDateFrom.fill = GridBagConstraints.HORIZONTAL;
					gbc_spnDateFrom.insets = new Insets(0, 0, 0, 5);
					gbc_spnDateFrom.gridx = 1;
					gbc_spnDateFrom.gridy = 3;
					panel.add(spnDateFrom, gbc_spnDateFrom);
				}
				if(this.dateTo != null) {
					spnDateTo.setModel(new SpinnerDateModel(this.dateTo, null, null, Calendar.MILLISECOND));
				}
				else {
					spnDateTo.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.MILLISECOND));
				}
				//Just here for safe keeping in case a massively fuck this shit up :)
//				spnDateTo.setModel(new SpinnerDateModel(new Date(1673823600000L), null, null, Calendar.MILLISECOND));
				JSpinner.DateEditor de_spnDateTo = new JSpinner.DateEditor(spnDateTo, "dd-MM-yyyy");
				spnDateTo.setEditor(de_spnDateTo);
				GridBagConstraints gbc_spnDateTo = new GridBagConstraints();
				gbc_spnDateTo.insets = new Insets(0, 0, 0, 5);
				gbc_spnDateTo.gridx = 3;
				gbc_spnDateTo.gridy = 3;
				panel.add(spnDateTo, gbc_spnDateTo);
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
	
	private void buttonCancelPressed() {
		okPressed = false;
		this.dispose();
	}
	
	private void buttonOKPressed() {
		Date dummyFrom = (Date) spnDateFrom.getValue();
		Date dummyTo = (Date) spnDateTo.getValue();
		if(dummyFrom.compareTo(dummyTo) > 0) {
			//dateFrom occurs after dateTo
			dateFrom = dummyTo;
			dateTo = dummyFrom;
		}
		else {
			dateFrom = dummyFrom;
			dateTo = dummyTo;
		}
		this.dispose();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spnDateFrom, "dd-MM-yyyy");
		spnDateFrom.setEditor(editor);
		JSpinner.DateEditor editor2 = new JSpinner.DateEditor(spnDateTo, "dd-MM-yyyy");
		spnDateTo.setEditor(editor2);
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}
	
	public boolean isOkPressed() {
		return okPressed;
	}
	
	public void setDateFrom(Date date) {
		dateFrom = new Date();
		dateFrom = date;
	}
	
	public void setDateTo(Date date) {
		dateTo = new Date();
		dateTo = date;
	}

}
