package tui;

import java.util.Scanner;

import controller.OrderController;
import controller.ProductController;

/**
 * Write a description of class LoanMenu here.
 *
 * @author Mogens Holm Iversen
 * @version 0.1.0 Initial draft version
 */
public class OrderMenu {
	// instance variables
	OrderController orderController = new OrderController();
	ProductController productController = new ProductController();

	/**
	 * Constructor for objects of class LoanMenu
	 */
	public OrderMenu() {
		// initialise instance variables

	}

	public void start() {
		orderMenu();
	}

	private void orderMenu() {
		boolean running = true;
		while (running) {
			int choice = writeOrderMenu();
			switch (choice) {
			case 1:
				System.out.println(" Dette er ikke implementeret endnu!");
				break;
			case 0:
				running = false;
				break;
			default:
				System.out.println("En uforklarlig fejl er sket med valg = " + choice);
				break;
			}
		}
	}

	private int writeOrderMenu() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("****** Ordre menu ******");
		System.out.println(" (1) Opret ordre");
		System.out.println(" (2) Slet ordre");
		System.out.println(" (3) Se ordre");
		System.out.println(" (4) Pluk ordre");
		System.out.println(" (0) Tilbage");
		System.out.print("\n Vælg: ");
		int choice = getIntegerFromUser(keyboard);
		return choice;
	}

	private int getIntegerFromUser(Scanner keyboard) {
		while (!keyboard.hasNextInt()) {
			System.out.println("Input skal være et tal - prøv igen");
			keyboard.nextLine();
		}
		return keyboard.nextInt();
	}

	private void createNewOrder() {

	}

	private boolean addProductToOrder(String search, int quantity) {
		boolean success = false;
		success = orderController.addProduct(search, quantity);
		return success;
	}

	private void completeSale() {

	}

	private void printInvoice() {

	}

}
