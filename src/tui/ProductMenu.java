package tui;
import java.util.Arrays;

import controller.ProductController;
import model.Product;

/**
 * Write a description of class LoanMenu here.
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
	
	//TODO - Documentation

	public void start() {
		productMenu();
	}
	
	//TODO - Documentation

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
	
	//TODO - Documentation

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
	
	//TODO - Documentation

	private void createProduct() {
		//The terminal will be cleared
		UserInput.clearTerminal();
		//Creates String-objects that will be filled by the user, via the terminal
		String name = UserInput.inputScanner("Indtast navnet på det nye produkt");
		String description = UserInput.inputScanner("Indtast beskrivelsen på det nye produkt");
		
		//A String-array is created and the user is asked to provide a comma-separated string, and using the split-method
		//in the String class an array is created, containing the categories
		String[] category = UserInput
				.inputScanner("Indtast kategori(er) for det nye produkt(separeret med komma, eksempel: 'Have,Køkken,Søm')")
				.split(",");
				
		//The user is asked to provide an aisle and placement of a product in the store
		// the aisle and placement is concatenated with a semicolon as separator
		String storageLocation1 = UserInput
				.inputScanner("Indtast gang nummeret på butikslokationen for det nye produkt");
		String storageLocation2 = UserInput.inputScanner(
				"Indtast hylde nummeret på butikslokationen for det nye produkt");
		String storageLocation = storageLocation1 + ":" + storageLocation2;
		
		//The user is asked to provide an aisle and placement of a product in the warehouse
		// the aisle and placement is concatenated with a semicolon as separator
		String warehouseLocation1 = UserInput
				.inputScanner("Indtast gang nummeret på lageret for det nye produkt");
		String warehouseLocation2 = UserInput
				.inputScanner("Indtast hylde nummeret på lageret for det nye produkt");
		String warehouseLocation = warehouseLocation1 + ":" + warehouseLocation2;
		
		//The user is asked for the amount of the product they have. both in the store and in the warehouse.
		int storageAmount = UserInput
				.inputScannerNumber("Indtast butiksbeholdningen for det nye produktt");
		int warehouseAmount = UserInput
				.inputScannerNumber("Indtast lagerbeholdningen for det nye produkt");
				
		//The productcontroller is tasked with the creation of the product, using the data gathered above
		productController.createProduct(name, "", description, category, storageLocation, warehouseLocation,
				storageAmount, warehouseAmount);
				
		//A message is displayed to the user via the terminal
		System.out.println("Produktet er oprettet, og tilføjet til databasen!");
	}
	
	//TODO - Documentation

	private void findProduct() {
		//The terminal will be cleared
		UserInput.clearTerminal();
		
		//The user is asked to provide a barcode or the product ID
		String search = UserInput
				.inputScanner("Indtast produkt ID eller stregkode for produktet du ønsker at finde");
		
		//The productcontroller is tasked to find a product by using the provided String-object
		Product p = productController.findProduct(search);
		
		//Checks if the product is not found 
		//if true: a message will be shown in the terminal
		//if false: the product will be shown in the terminal
		if (p == null) {
			System.out.println("Fejl! Produktet eksisterer ikke med det givne produkt ID eller stregkode");
		} else {
			printProductInformation(p);
		}
	}
	
	//TODO - Documentation
	
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
	
	//TODO - Documentation

	private void deleteProduct() {
		//The terminal will be cleared
		UserInput.clearTerminal();
		boolean success = false;
		String search = UserInput.inputScanner("Indtast produkt ID eller stregkode for produktet du ønsker at slette");
		success = productController.removeProduct(search);
		if (success) {
			System.out.println("Success! Produktet er blevet fjernet");
		} else {
			System.out.println("Fejl! Produktet eksisterer ikke med det givne produkt ID eller stregkode");
		}
	}

	//TODO - Documentation

	private void changePrices() {
		UserInput.clearTerminal();
		String search = UserInput
				.inputScanner("Indtast produkt ID eller stregkode for produktet du ønsker at ændre priserne på");
		Product productToChange = productController.findProduct(search);
		if (productToChange != null) {
			System.out.println();
			System.out.println("****** Prisændring for produkt ******");
			System.out.println(" (1) Ændre salgsprisen");
			System.out.println(" (2) Ændre indkøbsprisen");
			System.out.println(" (3) Ændre vejledende pris");
			System.out.println(" (0) Tilbage");
			int choice = UserInput.getIntegerFromUser();
			switch (choice) {
			case 1:
				String salesPrice = UserInput.inputScanner("Indtast den nye salgspris");
				double newSalesPrice = 0;
				try {
					newSalesPrice = Double.parseDouble(salesPrice);
				} catch (Exception e) {
					System.out.println("Fejl! En fejl opstod, prøv med punktum i stedet for komma som separator.");
					return;
				}
				productController.changeSalesPrice(newSalesPrice, productToChange);
				System.out.println("Success! Salgsprisen er blevet ændret.");
				break;
			case 2:
				String costPrice = UserInput.inputScanner("Indtast den nye indkøbspris");
				double newCostPrice = 0;
				try {
					newCostPrice = Double.parseDouble(costPrice);
				} catch (Exception e) {
					System.out.println("Fejl! En fejl opstod, prøv med punktum i stedet for komma som separator.");
					return;
				}
				productController.changeCostPrice(newCostPrice, productToChange);
				System.out.println("Success! Indkøbsprisen er blevet ændret.");
				break;
			case 3:
				String suggestedPrice = UserInput.inputScanner("Indtast den nye anbefalede pris");
				double newSuggestedPrice = 0;
				try {
					newSuggestedPrice = Double.parseDouble(suggestedPrice);
				} catch (Exception e) {
					System.out.println("Fejl! En fejl opstod, prøv med punktum i stedet for komma som separator.");
					return;
				}
				productController.changeSuggestedPrice(newSuggestedPrice, productToChange);
				System.out.println("Success! Den anbefalede pris er blevet ændret.");
				break;
			default:
				System.out.println("En uforklarlig fejl er sket med valg = " + choice);
				break;
			}
		} else {
			System.out.println("Fejl! Produktet eksisterer ikke med det givne produkt ID eller stregkode");
		}
	}

	private void updateProduct() {
		UserInput.clearTerminal();
		String search = UserInput.inputScanner("Indtast produkt ID eller stregkode for produktet du ønsker at slette");
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
				String barcode = UserInput.inputScanner("Indtast den nye stregkode på produktet (format: bc-2000-?)");
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

	private void printAllProducts() {
		UserInput.clearTerminal();
		productController.getAllProducts().forEach((e -> printProductInformation(e)));
	}
}