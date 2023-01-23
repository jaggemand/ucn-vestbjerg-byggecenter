package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CustomerController;
import controller.OrderController;
import controller.ProductController;
import model.Customer.customerType;
import model.Customer;
import model.Order;
import model.OrderContainer;
import model.Product;
import model.Order.OrderStatus;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	private static MainMenu frame;
	private ProductController pController;
	private OrderController oController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainMenu();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public MainMenu() throws IOException {
		
		
		//-----TODO REMOVE BEFORE END OF PROJECT PERIOD---------------
		ProductController productController = new ProductController();
		Product prod1 = productController.createProduct("Søm", "", "En pakke søm", new String[] { "one", "two" },
				"29:12", "42:13", 10, 50);
		Order order1 = new Order(false);
		order1.addProduct(prod1, 1);
		order1.setDate(5); // subtracts date
		order1.setStatus(OrderStatus.DELIVERED);
		order1.setCustomer(new Customer("Erik", "Gåsevangen60", "Gåsevangen 60", "95734901", "eriksoerensen@gmail.com", 0.0, "7540", "", customerType.PRIVATE));
		OrderContainer.getInstance().addOrder(order1);
		//-------------------------------------------------------------------------------
		
		
		setTitle("Hoved menu");
		pController = new ProductController();
		oController = new OrderController();
		tempCustomers();
		pController.loadFile();
		
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //TODO JFrame.DO_NOTHING_ON_CLOSE
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					closeWindow();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		setBounds(500, 500, 550, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(140, 40));
		panel.setMinimumSize(new Dimension(140, 40));
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblHeader = new JLabel("Menu");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 26));
		GridBagConstraints gbc_lblHeader = new GridBagConstraints();
		gbc_lblHeader.fill = GridBagConstraints.VERTICAL;
		gbc_lblHeader.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeader.gridx = 2;
		gbc_lblHeader.gridy = 1;
		panel.add(lblHeader, gbc_lblHeader);
		
		JButton btnSale = new JButton("Ordre oversigt");
		btnSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderOverview orderOverview = new OrderOverview();
				orderOverview.setVisible(true);
			}
		});
		btnSale.setBounds(new Rectangle(0, 0, 15, 15));
		btnSale.setMaximumSize(new Dimension(140, 40));
		btnSale.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSale.setMinimumSize(new Dimension(140, 40));
		btnSale.setPreferredSize(new Dimension(100, 40));
		GridBagConstraints gbc_btnSale = new GridBagConstraints();
		gbc_btnSale.fill = GridBagConstraints.BOTH;
		gbc_btnSale.insets = new Insets(0, 0, 5, 5);
		gbc_btnSale.gridx = 2;
		gbc_btnSale.gridy = 3;
		panel.add(btnSale, gbc_btnSale);
		
		JButton btnCashRegister = new JButton("Kassesalg");
		btnCashRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CashRegister cashRegister = new CashRegister();
				cashRegister.setVisible(true);
			}
		});
		
		JButton btnCreateOrder = new JButton("Opret ordre");
		btnCreateOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalesOrder salesOrder = new SalesOrder(null, rootPaneCheckingEnabled, frame);
				salesOrder.setVisible(true);
			}
		});
		btnCreateOrder.setPreferredSize(new Dimension(65, 40));
		btnCreateOrder.setMinimumSize(new Dimension(140, 40));
		btnCreateOrder.setMaximumSize(new Dimension(140, 40));
		btnCreateOrder.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCreateOrder.setBounds(new Rectangle(0, 0, 15, 15));
		GridBagConstraints gbc_btnCreateOrder = new GridBagConstraints();
		gbc_btnCreateOrder.fill = GridBagConstraints.BOTH;
		gbc_btnCreateOrder.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreateOrder.gridx = 2;
		gbc_btnCreateOrder.gridy = 4;
		panel.add(btnCreateOrder, gbc_btnCreateOrder);
		btnCashRegister.setBounds(new Rectangle(0, 0, 15, 15));
		btnCashRegister.setMaximumSize(new Dimension(140, 40));
		btnCashRegister.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCashRegister.setMinimumSize(new Dimension(140, 40));
		btnCashRegister.setPreferredSize(new Dimension(65, 40));
		GridBagConstraints gbc_btnCashRegister = new GridBagConstraints();
		gbc_btnCashRegister.fill = GridBagConstraints.BOTH;
		gbc_btnCashRegister.insets = new Insets(0, 0, 5, 5);
		gbc_btnCashRegister.gridx = 2;
		gbc_btnCashRegister.gridy = 5;
		panel.add(btnCashRegister, gbc_btnCashRegister);
		
		JButton btnCustomer = new JButton("Kundeoversigt");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openCustomerOverview();
			}
		});
		btnCustomer.setPreferredSize(new Dimension(65, 40));
		btnCustomer.setMinimumSize(new Dimension(140, 40));
		btnCustomer.setMaximumSize(new Dimension(140, 40));
		btnCustomer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCustomer.setBounds(new Rectangle(0, 0, 15, 15));
		GridBagConstraints gbc_btnCustomer = new GridBagConstraints();
		gbc_btnCustomer.fill = GridBagConstraints.BOTH;
		gbc_btnCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_btnCustomer.gridx = 2;
		gbc_btnCustomer.gridy = 6;
		panel.add(btnCustomer, gbc_btnCustomer);
		
		JButton btnStock = new JButton("Produktoversigt");
		btnStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductOverview productOverview = new ProductOverview();
				productOverview.setVisible(true);
			}
		});
		btnStock.setPreferredSize(new Dimension(65, 40));
		btnStock.setMinimumSize(new Dimension(140, 40));
		btnStock.setMaximumSize(new Dimension(140, 40));
		btnStock.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnStock.setBounds(new Rectangle(0, 0, 15, 15));
		GridBagConstraints gbc_btnStock = new GridBagConstraints();
		gbc_btnStock.fill = GridBagConstraints.BOTH;
		gbc_btnStock.insets = new Insets(0, 0, 5, 5);
		gbc_btnStock.gridx = 2;
		gbc_btnStock.gridy = 7;
		panel.add(btnStock, gbc_btnStock);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnClose = new JButton("Afslut");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					closeWindow();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_1.add(btnClose);
	}
	private void closeWindow() throws IOException {
		int input = JOptionPane.showOptionDialog(this, "Er du sikkert på at du vil lukke programmet?", "Afslut program",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				new Object[] { "Ja", "Nej" }, JOptionPane.YES_OPTION);
		if(input == 0) {
			pController.saveFile();
			
			frame.dispose();
			System.exit(0);
		}
	}
	private void openCustomerOverview() {
		CustomerOverview customerOverview = new CustomerOverview();
		customerOverview.setVisible(true);
	}
	
	private void tempCustomers() {
		CustomerController cc = new CustomerController();

		cc.createCustomer("Jacob", "Hobrovej 450", "Godsbanen 19", "24245482", "jacob@mail.dk", 0.5, "9000", "Kyed Aps",
				customerType.PRIVATE);
		cc.createCustomer("Marcus", "Jyllandsgade 10", "Abekatvej 12", "20926381", "marcus@mail.dk", 0.1, "9000",
				"Marcus Aps", customerType.PRIVATE);
		cc.createCustomer("Mikkel", "Reberbahnsgade 2", "Brenning 15", "65748294", "mikkel@mail.dk", 0.9, "9000",
				"Mikkel Aps", customerType.BUSINESS);
		cc.createCustomer("Rasmus", "Hornevej 89", "Støvringvej 248", "25172085", "rasmus@mail.dk", 0.3, "9000",
				"Rasmus Aps", customerType.PRIVATE);
		cc.createCustomer("Nicolai", "Udsigten 90", "Idrætsvej 1", "62719283", "nicolai@mail.dk", 0.8, "9000",
				"Niolai Aps", customerType.BUSINESS);
	}
}