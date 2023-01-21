package tui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import controller.OrderController;
import controller.ProductController;
import model.*;

/**
 * It's a menu for the sale/order part of the program
 *
 * @author Mogens Holm Iversen
 * @version 2.0.0 version
 */
public class SaleMenu {
	// instance variables
	OrderController orderController = new OrderController();
	ProductController productController = new ProductController();

	// A constructor.
	public SaleMenu() {
		// initialise instance variables
	}

	/**
	 * This function is called when the program starts. It calls the saleMenu()
	 * function.
	 */
	public void start() {
		saleMenu();
	}

	/**
	 * It prints a menu to the console and returns the user's choice
	 * 
	 * @return The method returns the choice of the user.
	 */
	private int writeSaleMenu() {
		UserInput.clearTerminal();
		System.out.println("****** Ordre/Salgsmenu ******");
		System.out.println(" (1) Opret salg/ordre");
		System.out.println(" (2) Slet salg/ordre");
		System.out.println(" (3) Se salg/ordre");
		System.out.println(" (0) Tilbage");
		System.out.print("\n Vælg: ");
		int choice = UserInput.getIntegerFromUser();
		return choice;
	}

	/**
	 * It's a menu for the sale/order part of the program
	 */
	private void saleMenu() {
		boolean running = true;
		UserInput.clearTerminal();
		while (running) {
			int choice = writeSaleMenu();
			switch (choice) {
				case 1:
					startSale();
					UserInput.waitAndClearTerminal();
					break;
				case 2:
					removeSale();
					UserInput.waitAndClearTerminal();
					break;
				case 3:
					findSale();
					UserInput.waitAndClearTerminal();
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

	/**
	 * It creates a sale/order and then scans products
	 */
	private void startSale() {
		UserInput.clearTerminal();
		boolean running = true;
		while (running) {
			System.out.println("****** Ordre eller salg ******");
			System.out.println(" (1) Opret salg");
			System.out.println(" (2) Opret ordre");
			System.out.println(" (0) Tilbage");
			System.out.print("\n Vælg: ");
			int choice = UserInput.getIntegerFromUser();
			boolean saleStatus = false;
			switch (choice) {
				case 1:
					saleStatus = true;
					orderController.createOrder(saleStatus);
					scanProducts();
					break;
				case 2:
					saleStatus = false;
					orderController.createOrder(saleStatus);
					scanProducts();
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

	/**
	 * The function scans products and adds them to the current order
	 */
	private void scanProducts() {
		UserInput.clearTerminal();
		boolean running = true;
		while (running) {
			String search = UserInput
					.inputScanner("Scan stregkode eller indtast varenummer: (Skriv 'færdig' for at afslutte)")
					.toLowerCase();
			switch (search) {
				case "færdig":
					running = false;
					if (orderController.getCurrentOrder().getOrderLines().size() <= 0) {
						System.out.println("Salget/ordren er annulleret, da den ikke indeholder nogle varer.");
					} else {
						completeSale();
					}
					break;
				default:
					Product foundProduct = productController.findProduct(search);
					if (foundProduct != null) {
						System.out.println("Indtast antal af produktet: ");
						int quantity = UserInput.getIntegerFromUser();
						orderController.addProduct(search, quantity);
					} else {
						System.out.println("Fejl! Produktet kan ikke findes i systemet");
					}
					break;
			}
		}
	}

	/**
	 * This function prints the invoice for the current order and then adds the
	 * order to the list of
	 * orders.
	 */
	private void completeSale() {
		printInvoice(orderController.getCurrentOrder());
		orderController.addOrder("0");
	}

	/**
	 * This function prints out an invoice for a given order
	 * 
	 * @param o The Order to print the invoice of
	 */
	private void printInvoice(Order o) {
		UserInput.clearTerminal();
		int size = o.getOrderLines().size();
		if (size != 0) {
			System.out.println("Faktura for ordre: " + o.getOrderNumber());
			System.out.println("Ordren indeholder: " + size + " antal forskellige produkter");
			System.out.println("Varer: " + "\n");
			for (OrderLine e : o.getOrderLines()) {
				String name = e.getProduct().getName();
				int quantity = e.getQuantity();
				double salePrice = e.getProduct().getSalesPrice();
				double total = (e.getProduct().getCostPrice() * e.getQuantity());
				String id = e.getProduct().getProductID();
				System.out.println("----------------------------------------");
				System.out.printf("\nProdukt navn: %s \tProdukt antal: %d", name, quantity);
				System.out.println("\nProdukt individuel pris : " + salePrice + "\t Total: " + total);
				System.out.println("ProduktID: " + id + "\n");
			}
			System.out.println("Total pris: " + o.getTotalPrice());
		}
	}

	/**
	 * It's a menu that lets the user choose between finding a sale or an order
	 */
	private void findSale() {
		UserInput.clearTerminal();
		boolean running = true;
		while (running) {
			System.out.println("****** Find ordre/salg ******");
			System.out.println(" (1) Find salg");
			System.out.println(" (2) Find ordrer");
			System.out.println(" (0) Tilbage");
			int choice = UserInput.getIntegerFromUser();
			boolean saleStatus = false;
			switch (choice) {
				case 1:
					saleStatus = true;
					callFindSale(saleStatus);
					break;
				case 2:
					saleStatus = false;
					callFindSale(saleStatus);
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

	/**
	 * It takes a boolean as an argument, and then asks the user for a start and end
	 * date, and then it
	 * searches for orders within that date range
	 * 
	 * @param saleStatus A boolean to decide if it is a sale or an order
	 */
	private void callFindSale(boolean saleStatus) {
		UserInput.clearTerminal();
		String dateStart = UserInput.inputScanner(
				"Indtast start dato for søgningen (Format: dd/mm/yyy. Skriv 'i dag', for alle oprettet i dag)");
		ArrayList<Order> orders = new ArrayList<>();

		try {
			if (dateStart.toLowerCase().equals("i dag")) {
				DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				dateStart = LocalDate.now().format(dateTimeFormatter);
				String dateEnd = dateStart;
				orders = orderController.findOrdersWithinDate(dateStart, dateEnd, saleStatus);
			} else {
				String dateEnd = UserInput.inputScanner("Indtast slut dato for søgningen (Format: dd/mm/yyyy)");
				orders = orderController.findOrdersWithinDate(dateStart, dateEnd, saleStatus);
			}
		} catch (DateTimeParseException e) {
			UserInput.clearTerminal();
			System.out.println(
					"Fejl! En fejl opstod at konvertere datoen til det korrekte format. Husk at bruge det korrekte format.");
			System.out.println(e.getMessage());
		}
		UserInput.clearTerminal();
		if (orders.isEmpty()) {
			System.out.println("Din søgning var tom.");
		} else {
			for (Order element : orders) {
				System.out.println("***** Ordre/salgsinformation *****");
				System.out.println("Ordre/salgsnummer: " + element.getOrderNumber());
				System.out.println("Ordre/salgsdato: " + element.getDate());
				System.out.println("Afhentningsdato: " + element.getPickup());
				System.out.println("Total pris: " + String.format("%.2f", element.getTotalPrice()) + " DKK");
				System.out.println("Ordre/salgsstatus: " + element.getStatusAsString());
				System.out.println(" ");
			}
		}
	}

	/**
	 * The function removes an order from the database
	 */
	private void removeSale() {
		UserInput.clearTerminal();
		String input = UserInput.inputScanner("Hvad er ordre nummeret for salget/ordren du ønsker at slette?");
		boolean toRemove = orderController.removeOrder(input);
		if (toRemove == false) {
			System.out.println("Salget/ordren blev ikke fundet");
		} else {
			System.out.println("Salget/ordren var successfuldt slettet!");
		}
	}
}
