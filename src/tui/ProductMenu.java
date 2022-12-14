package tui;

import java.util.Arrays;

import controller.ProductController;
import model.Product;

/**
 * It's a menu for the product class, where you can create, delete, update and
 * find products
 *
 * @author Mogens Holm Iversen
 * @version 0.1.0 Initial draft version
 */
public class ProductMenu {
	// instance variables
	ProductController productController = new ProductController();

	/**
	 * Constructor for objects of class LoanMenu
	 */
	public ProductMenu() {
		// initialise instance variables
	}

	/**
	 * Calls the function `productMenu()` which displays the product menu
	 */
	public void start() {
		productMenu();
	}

	/**
	 * The primary menu for Product, to create, read, update and delete
	 *
	 */
	private void productMenu() {
		boolean running = true;
		UserInput.clearTerminal();
		while (running) {
			int choice = writeProductMenu();
			switch (choice) {
				case 1:
					createProduct();
					UserInput.waitAndClearTerminal();
					break;
				case 2:
					deleteProduct();
					UserInput.waitAndClearTerminal();
					break;
				case 3:
					updateProduct();
					UserInput.waitAndClearTerminal();
					break;
				case 4:
					findProduct();
					UserInput.waitAndClearTerminal();
					break;
				case 5:
					changePrices();
					UserInput.waitAndClearTerminal();
					break;
				case 6:
					printAllProducts();
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
	 * It prints a menu to the console and returns the user's choice
	 * 
	 * @return The method returns the choice of the user.
	 */
	private int writeProductMenu() {
		UserInput.clearTerminal();
		System.out.println("****** Produktmenu ******");
		System.out.println(" (1) Opret produkt");
		System.out.println(" (2) Slet produkt");
		System.out.println(" (3) Ændre produkt");
		System.out.println(" (4) Find produkt");
		System.out.println(" (5) Ændre priser");
		System.out.println(" (6) Vis alle produkter");
		System.out.println(" (0) Tilbage");
		System.out.print("\n Vælg: ");
		int choice = UserInput.getIntegerFromUser();
		return choice;
	}

	/**
	 * The user is asked to provide data about a product, and the data is then used
	 * to create a product in
	 * the database
	 */
	private void createProduct() {
		// The terminal will be cleared
		UserInput.clearTerminal();
		// Creates String-objects that will be filled by the user, via the terminal
		String name = UserInput.inputScanner("Indtast navnet på det nye produkt");
		String description = UserInput.inputScanner("Indtast beskrivelsen på det nye produkt");

		// A String-array is created and the user is asked to provide a comma-separated
		// string, and using the split-method
		// in the String class an array is created, containing the categories
		String[] category = UserInput
				.inputScanner(
						"Indtast kategori(er) for det nye produkt(separeret med komma, eksempel: 'Have,Køkken,Søm')")
				.split(",");

		// The user is asked to provide an aisle and placement of a product in the store
		// the aisle and placement is concatenated with a semicolon as separator
		String storageLocation1 = UserInput
				.inputScanner("Indtast gang nummeret på butikslokationen for det nye produkt");
		String storageLocation2 = UserInput.inputScanner(
				"Indtast hylde nummeret på butikslokationen for det nye produkt");
		String storageLocation = storageLocation1 + ":" + storageLocation2;

		// The user is asked to provide an aisle and placement of a product in the
		// warehouse
		// the aisle and placement is concatenated with a semicolon as separator
		String warehouseLocation1 = UserInput
				.inputScanner("Indtast gang nummeret på lageret for det nye produkt");
		String warehouseLocation2 = UserInput
				.inputScanner("Indtast hylde nummeret på lageret for det nye produkt");
		String warehouseLocation = warehouseLocation1 + ":" + warehouseLocation2;

		// The user is asked for the amount of the product they have. both in the store
		// and in the warehouse.
		int storageAmount = UserInput
				.inputScannerNumber("Indtast butiksbeholdningen for det nye produktt");
		int warehouseAmount = UserInput
				.inputScannerNumber("Indtast lagerbeholdningen for det nye produkt");

		// The productcontroller is tasked with the creation of the product, using the
		// data gathered above
		productController.createProduct(name, "", description, category, storageLocation, warehouseLocation,
				storageAmount, warehouseAmount);

		// A message is displayed to the user via the terminal
		System.out.println("Produktet er oprettet, og tilføjet til databasen!");
	}

	/**
	 * The user is asked to provide a barcode or the product ID. The
	 * productcontroller is tasked to find a
	 * product by using the provided String-object. Checks if the product is not
	 * found. If true: a message will be shown in the terminal. If false: the product invoice will be shown in
	 * the terminal
	 */
	private void findProduct() {
		// The terminal will be cleared
		UserInput.clearTerminal();

		// The user is asked to provide a barcode or the product ID
		String search = UserInput
				.inputScanner("Indtast produkt ID eller stregkode for produktet du ønsker at finde");

		// The productcontroller is tasked to find a product by using the provided
		// String-object
		Product p = productController.findProduct(search);

		// Checks if the product is not found
		// if true: a message will be shown in the terminal
		// if false: the product will be shown in the terminal
		if (p == null) {
			System.out.println("Fejl! Produktet eksisterer ikke med det givne produkt ID eller stregkode");
		} else {
			printProductInformation(p);
		}
	}

	/**
	 * It prints out all the information about a product
	 * 
	 * @param p Product to print information about
	 */
	private void printProductInformation(Product p) {
		System.out.println("----------------" + p.getName() + "----------------");
		System.out.println("Produktet's ID: " + p.getProductID());
		System.out.println("Produktet's pris: " + p.getSalesPriceFormatted() + "\t Indkøbspris: "
				+ p.getCostPriceFormatted() + "\t Vejledende pris: " + p.getSuggestedSalesPriceFormatted());
		System.out.println("Beskrivelse: " + p.getDescription());
		System.out.println("Kategorier for produktet: " + Arrays.toString(p.getCategory()));
		System.out
				.println("Butikslokation: " + p.getStorageLocation() + "\t Lagerlokation: " + p.getWarehouseLocation());
		System.out
				.println("Butiksbeholdning: " + p.getStorageAmount() + "\t Lagerbeholdning: " + p.getWarehouseAmount());
		System.out.println("Stregkode: " + p.getBarcode());
		System.out.println("----------------" + p.getName() + "----------------");
		System.out.println();
	}

	/**
	 * The user is asked to provide a barcode or the product ID. The
	 * productcontroller is tasked to remove a product using the provided String-object.
	 * This will return a boolean true if the product is removed
	 */
	private void deleteProduct() {
		// The terminal will be cleared
		UserInput.clearTerminal();
		boolean success = false;

		// The user is asked to provide a barcode or the product ID
		String search = UserInput
				.inputScanner("Indtast produkt ID eller stregkode for produktet du ønsker at slette");

		// The productcontroller is tasked to remove a product using the provided
		// String-object
		// This will return a boolean true, it the product is removed.
		success = productController.removeProduct(search);

		// This will show a message in the terminal about the success or failure
		if (success) {
			System.out.println("Success! Produktet er blevet fjernet");
		} else {
			System.out.println("Fejl! Produktet eksisterer ikke med det givne produkt ID eller stregkode");
		}
	}

	/**
	 * The user is asked to provide a barcode or product ID, the productcontroller
	 * is tasked to find a
	 * product by using the provided String-object, if the product is found, the
	 * change price menu will be
	 * shown in the terminal, the user is asked to provide an integer to select
	 * which menu section they
	 * want to navigate to, a switch statement that uses an integer to switch
	 * navigation path, if the
	 * integer given does not match any of the cases, an error message will be
	 * shown, product is not found
	 */
	private void changePrices() {
		// The terminal will be cleared
		UserInput.clearTerminal();

		// The user is asked to provide a barcode or the product ID
		String search = UserInput
				.inputScanner("Indtast produkt ID eller stregkode for produktet du ønsker at ændre priserne på");

		// The productcontroller is tasked to find a product by using the provided
		// String-object
		Product productToChange = productController.findProduct(search);

		// If the product is found. the change price menu will be shown in the terminal
		if (productToChange != null) {
			// The menu is shown in the terminal
			System.out.println();
			System.out.println("****** Prisændring for produkt ******");
			System.out.println(" (1) Ændre salgsprisen");
			System.out.println(" (2) Ændre indkøbsprisen");
			System.out.println(" (3) Ændre vejledende pris");
			System.out.println(" (0) Tilbage");

			// The user is asked to provide an integer to select which menu section they
			// want to navigate to
			int choice = UserInput.getIntegerFromUser();

			// A switch statement that uses an integer to switch navigation path
			switch (choice) {
				case 1:
					// Change salesprice is selected
					double newSalesPrice = refactorGetDoubleFromUser(
							"Indtast den nye salgspris",
							"Fejl! En fejl opstod, prøv med punktum i stedet for komma som separator.",
							"Success! Salgsprisen er blevet ændret.");
					productController.changeSalesPrice(newSalesPrice, productToChange);
					break;
				case 2:
					// Change costprice is selected
					double costPrice = refactorGetDoubleFromUser(
							"Indtast den nye indkøbspris",
							"Fejl! En fejl opstod, prøv med punktum i stedet for komma som separator.",
							"Success! Indkøbsprisen er blevet ændret.");
					productController.changeCostPrice(costPrice, productToChange);
					break;
				case 3:
					// Change suggestedprice is selected
					double suggestedPrice = refactorGetDoubleFromUser(
							"Indtast den nye anbefalede pris",
							"Fejl! En fejl opstod, prøv med punktum i stedet for komma som separator.",
							"Success! Den anbefalede pris er blevet ændret.");
					productController.changeSuggestedPrice(suggestedPrice, productToChange);
					break;
				default:
					// If the integer given does not match any of the cases, an error message will
					// be shown
					System.out.println("En uforklarlig fejl er sket med valg = " + choice);
					break;
			}
		} else {
			// Product is not found
			System.out.println("Fejl! Produktet eksisterer ikke med det givne produkt ID eller stregkode");
		}
	}

	/**
	 * This function will ask the user for a double, and if the user doesn't input a
	 * double, it will
	 * return 0.0
	 * 
	 * @param ask     The question that will be asked to the user
	 * @param error   The error message that will be shown in the terminal if the
	 *                user inputs something that is not a double
	 * @param success The message that will be shown when the user has entered a
	 *                valid double
	 * @return The method returns a double.
	 */
	private double refactorGetDoubleFromUser(String ask, String error, String success) {
		// The user is asked for a new price
		String salesPrice = UserInput.inputScanner(ask);
		double newPrice = 0.0;
		// This try-catchblock will test if the input is a double
		try {
			newPrice = Double.parseDouble(salesPrice);
			// The success message will be shown in the terminal

		} catch (Exception e) {
			// The error message will be shown in the terminal
			System.out.println(error);
			return 0.0;
		}
		System.out.println(success);
		return newPrice;
	}

	/**
	 * The user is asked to provide a barcode or the product ID, the
	 * productcontroller is tasked to find a product 
	 * by using the provided String-object, the user is then asked to choose
	 * which attribute of
	 * the product they want to change, the productcontroller is tasked to change
	 * the attribute of the
	 * product, and finally the product is printed out with the new attribute
	 */
	private void updateProduct() {
		// The terminal will be cleared
		UserInput.clearTerminal();

		// The user is asked to provide a barcode or the product ID
		String search = UserInput.inputScanner("Indtast produkt ID eller stregkode for produktet du ønsker at slette");

		// The productcontroller is tasked to find a product by using the provided
		// String-object
		Product productToChange = productController.findProduct(search);
		if (productToChange != null) {
			System.out.println();
			System.out.println("****** Produkt ændring ******");
			System.out.println(" (1) Ændre navn");
			System.out.println(" (2) Ændre stregkode");
			System.out.println(" (3) Ændre beskrivelse");
			System.out.println(" (4) Ændre kategori");
			System.out.println(" (5) Ændre butikslokation");
			System.out.println(" (6) Ændre lagerlokation");
			System.out.println(" (7) Ændre butiksbeholdning");
			System.out.println(" (8) Ændre lagerbeholdning");
			System.out.println(" (0) Tilbage");
			int choice = UserInput.getIntegerFromUser();
			switch (choice) {
				case 1:
					String name = UserInput.inputScanner("Indtast det nye navn på produktet");
					productController.changeProductName(name, productToChange);
					break;
				case 2:
					boolean success = false;
					String barcode = UserInput
							.inputScanner("Indtast den nye stregkode på produktet (format: bc-2000-?)");
					success = productController.changeProductBarcode(barcode, productToChange);
					if (!success) {
						System.out.println("Fejl! Stregkoden eksisterer allerede");
					}
					break;
				case 3:
					String description = UserInput.inputScanner("Indtast den nye beskrivelse på produktet");
					productController.changeProductDescription(description, productToChange);
					break;
				case 4:
					String[] category = UserInput
							.inputScanner(
									"Indtast kategori(er) for produktet(separeret med komma, eksempel: 'Have,Køkken,Søm')")
							.split(",");
					productController.changeProductCategory(category, productToChange);
					break;
				case 5:
					String storageLocation1 = UserInput
							.inputScanner("Indtast det nye gang nummer i butikslokationen for produktet");
					String storageLocation2 = UserInput
							.inputScanner("Indtast det nye hylde nummer i butikslokationen for produktet");
					String storageLocation = storageLocation1 + ":" + storageLocation2;
					productController.changeProductStorageLocation(storageLocation, productToChange);
					break;
				case 6:
					String warehouseLocation1 = UserInput
							.inputScanner("Indtast det nye gang nummer på lageret for produktet");
					String warehouseLocation2 = UserInput
							.inputScanner("Indtast det nye hylde nummer på lageret for produktet");
					String warehouseLocation = warehouseLocation1 + ":" + warehouseLocation2;
					productController.changeProductWarehouseLocation(warehouseLocation, productToChange);
					break;
				case 7:
					int storageAmount = UserInput.inputScannerNumber("Indtast den nye butiksbeholdning for produktet");
					productController.changeProductStorageAmount(storageAmount, productToChange);
					break;
				case 8:
					int warehouseAmount = UserInput.inputScannerNumber("Indtast den nye lagerbeholdning for produktet");
					productController.changeProductWarehouseAmount(warehouseAmount, productToChange);
					break;
				case 0:
					start();
					break;
				default:
					System.out.println("En uforklarlig fejl er sket med valg = " + choice);
					break;
			}
			if (choice < 8 && choice > 1) {
				printProductInformation(productToChange);
			}
		} else {
			System.out.println("Fejl! Produktet eksisterer ikke med det givne produkt ID eller stregkode");
		}
	}

	/**
	 * This function prints all the products in the database
	 */
	private void printAllProducts() {
		UserInput.clearTerminal();
		productController.getAllProducts().forEach((e -> printProductInformation(e)));
	}
}