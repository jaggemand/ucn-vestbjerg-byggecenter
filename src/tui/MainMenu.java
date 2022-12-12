package tui;

import java.util.Scanner;

import controller.ProductController;

/**
 * Write a description of class MainMenu here.
 *
 * @author Mogens Holm Iversen
 * @version 0.1.0 Initial draft version
 */
public class MainMenu {
	// instance variables
	private OrderMenu orderMenu;
	private SaleMenu saleMenu;
	private ProductMenu productMenu;

	/**
	 * Constructor for objects of class MainMenu
	 */
	public static void main(String[] args) {
		// initialise instance variables
		MainMenu mm = new MainMenu();
		mm.start();

	}

	public void start() {
		orderMenu = new OrderMenu();
		saleMenu = new SaleMenu();
		productMenu = new ProductMenu();
		mainMenu();
	}

	private void mainMenu() {
		boolean running = true;
		while (running) {
			clearTerminal();
			int choice = writeMainMenu();
			switch (choice) {
			case 1:
				saleMenu.start();
				waitAndClearTerminal();
				break;
			case 2:
				orderMenu.start();
				waitAndClearTerminal();
				break;
			case 3:
				productMenu.start();
				break;
			case 9:
				generateTestData();
				System.out.println("Test data er blevet genereret");
				break;
			case 0:
				System.out.println("Tak for denne gang.");
				running = false;
				break;
			default:
				System.out.println("Der er sket en uforklarlig fejl, valg = " + choice);
				waitAndClearTerminal();
				break;
			}
		}
	}

	@SuppressWarnings("resource")
	private int writeMainMenu() {
		System.out.println("****** Hovedmenu ******");
		System.out.println(" (1) Salgsmenu");
		System.out.println(" (2) Ordre menu");
		System.out.println(" (3) Produkt menu");
		System.out.println(" (9) Generer testdata");// will generate testdata, delete in final version
		System.out.println(" (0) Afslut programmet");
		System.out.print("\n Vælg: ");

		int choice = UserInput.getIntegerFromUser();
		return choice;
	}

	@SuppressWarnings("resource")
	private void waitAndClearTerminal() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Tryk enter for at fortsætte");
		clearTerminal();

	}

	private void clearTerminal() {
		for (int i = 0; i < 22; i++) {
			System.out.println(" ");
		}
	}

	private void generateTestData() {
		ProductController productController = new ProductController();
		productController.createProduct("Søm", "", "En pakke søm", new String[] { "one", "two" }, "29:12", "42:13", 10,
				50);
		productController.createProduct("Grillrist", "", "En grillrist Ø30", new String[] { "Grill", "Have" }, "12:12",
				"11:30", 5, 3);
		productController.createProduct("Vasketøjskurv", "", "Vasketøjskurv i plastik", new String[] { "Bad" }, "15:12",
				"4:12", 6, 10);
		productController.createProduct("Lysepære", "", "E27 pære", new String[] { "lys", "elektronik" }, "29:12",
				"42:13", 4, 88);
		productController.createProduct("Hammer", "", "En gummi hammer", new String[] { "værktøj", "byggemarked" },
				"9:28", "11:67", 15, 24);
		productController.createProduct("Skovl", "", "En stor skovl", new String[] { "redskab", "have" }, "11:5",
				"98:17", 5, 19);
	}
}
