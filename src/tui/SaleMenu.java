package tui;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import controller.OrderController;
import controller.ProductController;
import model.*;

/**
 * Write a description of class LoanMenu here.
 *
 * @author Mogens Holm Iversen
 * @version 0.1.0 Initial draft version
 */

public class SaleMenu {
	// instance variables
	OrderController orderController = new OrderController();
	ProductController productController = new ProductController();

	/**
	 * Constructor for objects of class LoanMenu
	 */
	public SaleMenu() {
		// initialise instance variables

	}

	public void start() {
		saleMenu();
	}

	private void saleMenu() {
		boolean running = true;
		while (running) {
			int choice = writeSaleMenu();
			switch (choice) {
			case 1:
				startSale();
				break;
			case 2:
				removeSale();
				break;
			case 3:
				findSale();
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

	private int writeSaleMenu() {
		System.out.println("****** Ordre/Salgsmenu ******");
		System.out.println(" (1) Opret salg/ordre");
		System.out.println(" (2) Slet salg/ordre");
		System.out.println(" (3) Se salg/ordre");
		System.out.println(" (0) Tilbage");
		System.out.print("\n Vælg: ");
		int choice = UserInput.getIntegerFromUser();
		return choice;
	}

	private void startSale() {
		orderController.createOrder();
		scanProducts();
	}

	private void scanProducts() {
		boolean running = true;
		while (running) {
			String search = UserInput
					.inputScanner("Scan stregkode eller indtast varenummer: (Skriv 'færdig' for at afslutte)")
					.toLowerCase();
			switch (search) {
			case "færdig":
				running = false;
				if (orderController.getCurrentOrder().getOrderLines().size() <= 0) {
					System.out.println("Salget er annulleret, da den ikke indeholder nogle varer.");
				}
				completeSale();
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

	private void completeSale() {
		printInvoice(orderController.getCurrentOrder());
		orderController.addOrder();
	}

	private void printInvoice(Order o) {
		int size = o.getOrderLines().size();
		if (size != 0) {
			System.out.println("Faktura for ordre: " + o.getOrderNumber());
			System.out.println("Ordren indeholder: " + size + " antal forskellige produkter");
			System.out.println("Varer: " + "\n");
			for (OrderLine e : o.getOrderLines()) {
				String name = e.getProduct().getName();
				int quantity = e.getQuantity();
				double costPrice = e.getProduct().getCostPrice();
				double total = (e.getProduct().getCostPrice() * e.getQuantity());
				String id = e.getProduct().getProductID();
				System.out.println("----------------------------------------");
				System.out.printf("\nProdukt navn: %s \tProdukt antal: %d", name, quantity);
				System.out.println("\nProdukt individuel pris : " + costPrice + "\t Total: " + total);
				System.out.println("ProduktID: " + id + "\n");
			}
			System.out.println("Total pris: " + o.getTotalPrice());
		}
	}

	private void findSale() {
		System.out.println("****** Find ordre/salg ******");
		System.out.println(" (1) Find ordrer");
		System.out.println(" (2) Find salg");
		System.out.println(" (0) Tilbage");
		int choice = UserInput.getIntegerFromUser();
		switch (choice) {
		case 1:
			// TODO
			// search parameter is != SALE
			// print after
			break;
		case 2:
			// TODO
			// search parameter is = SALE
			// print after
			break;
		default:
			System.out.println("En uforklarlig fejl er sket med valg = " + choice);
			break;
		}
		String dateStart = UserInput.inputScanner(
				"Indtast start dato for søgningen (Format: dd/mm/yyy. Skriv 'i dag', for alle oprettet i dag)");
		ArrayList<Order> orders = new ArrayList<>();

		try {
			if (dateStart.toLowerCase().equals("i dag")) {
				DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				dateStart = LocalDate.now().format(dateTimeFormatter);
				String dateEnd = dateStart;
				orders = orderController.findOrdersWithinDate(dateStart, dateEnd);
			} else {
				String dateEnd = UserInput.inputScanner("Indtast slut dato for søgningen (Format: dd/mm/yyyy)");
				orders = orderController.findOrdersWithinDate(dateStart, dateEnd);
			}
		} catch (DateTimeParseException e) {
			System.out.println(
					"Fejl! En fejl opstod at konvertere datoen til det korrekte format. Husk at bruge det korrekte format.");
		}

		if (orders.isEmpty()) {
			System.out.println("Din søgning var tom.");
		} else {
			for (Order element : orders) {
				System.out.println("***** Orde/salgsinformation *****");
				System.out.println("Orde/salgsnummer: " + element.getOrderNumber());
				System.out.println("Orde/salgsdato: " + element.getDate());
				System.out.println("Afhentningsdato: " + element.getPickup());
				System.out.println("Total pris: " + element.getTotalPrice());
				System.out.println("Orde/salgsstatus: " + element.getStatus());
				System.out.println(" ");
			}
		}
	}

	private void removeSale() {
		String input = UserInput.inputScanner("Hvad er ordre nummeret for salget du ønsker at slette?");
		boolean toRemove = orderController.removeOrder(input);
		if (toRemove == false) {
			System.out.println("Salget blev ikke fundet");
		} else {
			System.out.println("Salget var successfuldt slettet!");
		}
	}
}
