package tui;

import controller.ProductController;
import model.Order;
import model.OrderContainer;
import model.Product;

/**
 * It's the main menu of the program
 *
 * @author Mogens Holm Iversen
 * @version 2.0ja.0 version
 */
public class MainMenu {
	// instance variables
	private boolean debug = true;
	private SaleMenu saleMenu;
	private ProductMenu productMenu;
	private ProductController pController = new ProductController();

	/**
	 * The main function is the entry point of the program. It creates an instance
	 * of the MainMenu class
	 * and calls the start function
	 */
	public static void main(String[] args) {
		// initialise instance variables
		MainMenu mm = new MainMenu();
		mm.start();
	}

	/**
	 * The start function creates a new SaleMenu and ProductMenu object, then calls
	 * the mainMenu function
	 */
	public void start() {
		pController.loadFile();
		saleMenu = new SaleMenu();
		productMenu = new ProductMenu();
		mainMenu();
	}

	/**
	 * The function is a while loop that runs until the user enters 0. The user is
	 * presented with a menu
	 * and depending on the choice, the user is sent to another menu
	 */
	private void mainMenu() {
		boolean running = true;
		while (running) {
			UserInput.clearTerminal();
			int choice = writeMainMenu();
			switch (choice) {
				case 1:
					saleMenu.start();
					UserInput.waitAndClearTerminal();
					break;
				case 2:
					productMenu.start();
					UserInput.waitAndClearTerminal();
					break;
				case 9:
					if (debug) {
						generateTestData();
						System.out.println("Test data er blevet genereret");
					}
					UserInput.waitAndClearTerminal();
					break;
				case 0:
					pController.saveFile();
					System.out.println("Tak for denne gang.");
					running = false;
					break;
				default:
					System.out.println("Der er sket en uforklarlig fejl, valg = " + choice);
					UserInput.waitAndClearTerminal();
					break;
			}
		}
	}

	/**
	 * It prints a menu to the console and returns the user's choice
	 * 
	 * @return The method returns the choice the user made.
	 */
	private int writeMainMenu() {
		System.out.println("****** Hovedmenu ******");
		System.out.println(" (1) Salgsmenu/ordre");
		System.out.println(" (2) Produkt menu");
		if (debug) {
			System.out.println(" (9) Generer testdata"); // will generate testdata, delete in final version
		}
		System.out.println(" (0) Afslut programmet");
		System.out.print("\n Vælg: ");

		int choice = UserInput.getIntegerFromUser();
		return choice;
	}

	/**
	 * It creates a bunch of products and orders and adds them to the containers
	 */
	private void generateTestData() {
		ProductController productController = new ProductController();
		Product prod1 = productController.createProduct("Søm", "", "En pakke søm", new String[] { "one", "two" },
				"29:12", "42:13", 10, 50);
		Product prod2 = productController.createProduct("Grillrist", "", "En grillrist Ø30",
				new String[] { "Grill", "Have" }, "12:12", "11:30", 5, 3);
		Product prod3 = productController.createProduct("Vasketøjskurv", "", "Vasketøjskurv i plastik",
				new String[] { "Bad" }, "15:12", "4:12", 6, 10);
		Product prod4 = productController.createProduct("Lysepære", "", "E27 pære",
				new String[] { "lys", "elektronik" }, "29:12", "42:13", 4, 88);
		Product prod5 = productController.createProduct("Hammer", "", "En gummi hammer",
				new String[] { "værktøj", "byggemarked" }, "9:28", "11:67", 15, 24);
		Product prod6 = productController.createProduct("Skovl", "", "En stor skovl",
				new String[] { "redskab", "have" }, "11:5", "98:17", 5, 19);
		Order order1 = new Order(false);
		order1.addProduct(prod1, 1);
		order1.setDate(5); // subtracts date
		OrderContainer.getInstance().addOrder(order1);

		Order order2 = new Order(false);
		order2.addProduct(prod2, 3);
		order2.setDate(3); // subtracts days
		OrderContainer.getInstance().addOrder(order2);

		Order order3 = new Order(true);
		order3.addProduct(prod3, 1);
		OrderContainer.getInstance().addOrder(order3);

		Order order4 = new Order(true);
		order4.addProduct(prod4, 2);
		OrderContainer.getInstance().addOrder(order4);

		Order order5 = new Order(false);
		order5.addProduct(prod5, 1);
		OrderContainer.getInstance().addOrder(order5);

		Order order6 = new Order(true);
		order6.addProduct(prod6, 8);
		OrderContainer.getInstance().addOrder(order6);
		debug = false;
	}
}