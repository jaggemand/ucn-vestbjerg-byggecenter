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

    public void start() {
        productMenu();
    }

    private void productMenu() {
        boolean running = true;
        while (running) {
            int choice = writeProductMenu();
            switch (choice) {
                case 1:
                  createProduct();
                  break;
                case 2:
                  deleteProduct();
                  break;
                case 3:
                  updateProduct();
                  break;
                case 4:
                  findProduct();
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
    
    private int writeProductMenu() {
        System.out.println("****** Produktmenu ******");
        System.out.println(" (1) Opret produkt");
        System.out.println(" (2) Slet produkt");
        System.out.println(" (3) Ændre produkt");
        System.out.println(" (4) Find produkt");
        System.out.println(" (0) Tilbage");
        System.out.print("\n Vælg: ");
        int choice = UserInput.getIntegerFromUser();
        return choice;
    }
    
    private void createProduct() {
    	String name = UserInput.inputScanner("Indtast navnet på det nye produkt");
    	String description = UserInput.inputScanner("Indtast beskrivelsen på det nye produkt");
    	String[] category = UserInput.inputScanner("Indtast kategori(er) for det nye produkt(separeret med komma, eksempel: 'Have,Køkken,Søm')").split(",");
    	String storageLocation1 = UserInput.inputScanner("Indtast gang nummeret i butikslokationen for det nye produkt");
    	String storageLocation2 = UserInput.inputScanner("Indtast hylde String description = inputScanner(\"Indtast beskrivelsen på det nye produkt\");nummeret i butikslokationen for det nye produkt");
    	String storageLocation = storageLocation1+":"+storageLocation2;
    	String warehouseLocation1 = UserInput.inputScanner("Indtast gang nummeret på lageret for det nye produkt");
    	String warehouseLocation2 = UserInput.inputScanner("Indtast hylde nummeret på lageret for det nye produkt");
    	String warehouseLocation = warehouseLocation1+":"+warehouseLocation2;
    	int storageAmount = UserInput.inputScannerNumber("Indtast butiksbeholdningen for det nye produktt");
    	int warehouseAmount = UserInput.inputScannerNumber("Indtast lagerbeholdningen for det nye produkt");
    	productController.createProduct(name, "", description, category, storageLocation, warehouseLocation, storageAmount, warehouseAmount);
    	System.out.println("Produktet er oprettet, og tilføjet til databasen!");
    }
    
    private void findProduct() {
        String search = UserInput.inputScanner("Indtast produkt ID eller stregkode for produktet du ønsker at finde");
        Product p = productController.findProduct(search);
        if(p == null){
            System.out.println("Fejl! Produktet blev ikke fundet!");
        }else{
            printProductInformation(p);
        }
    }
    
    private void printProductInformation(Product p){
        System.out.println("----------------" + p.getName() + "----------------");
        System.out.println("Produktet's ID: " + p.getProductID());
        System.out.println("Produktet's pris: " + p.getSalesPrice() + "\t Indkøbspris: "
        + p.getCostPrice() + "\t Vejledende pris: " + p.getSuggestedSalesPrice());
        System.out.println("Beskrivelse: " + p.getDescription());
        System.out.println("Kategorier for produktet: " + Arrays.toString(p.getCategory()));
        System.out.println("Butikslokation: " + p.getStorageLocation() + "\t Lagerlokation: " + p.getWarehouseLocation());
        System.out.println("Butiksbeholdning: " + p.getStorageAmount() + "\t Lagerbeholdning: " + p.getWarehouseAmount());
        System.out.println("Stregkode: " + p.getBarcode());
        System.out.println("----------------" + p.getName() + "----------------");
    }
    
    private void deleteProduct(){
    	boolean success = false;
    	String search = UserInput.inputScanner("Indtast produkt ID eller stregkode for produktet du ønsker at slette");
    	success = productController.removeProduct(search);
    	if(success) {
    		System.out.println("Success! Produktet er blevet fjernet");
    	} else {
    		System.out.println("Fejl! Produktet eksisterer ikke med det givne produkt ID eller stregkode");
    	}
    }
    
    private void updateProduct(){
    	String search = UserInput.inputScanner("Indtast produkt ID eller stregkode for produktet du ønsker at slette");
    	Product productToChange = productController.findProduct(search);
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
			if(!success) {
				System.out.println("Fejl! Stregkoden eksisterer allerede");
			}
			break;
		case 3:
			String description = UserInput.inputScanner("Indtast den nye beskrivelse på produktet");
			productController.changeProductDescription(description, productToChange);
			break;
		case 4:
			String[] category = UserInput.inputScanner("Indtast kategori(er) for produktet(separeret med komma, eksempel: 'Have,Køkken,Søm')").split(",");
			productController.changeProductCategory(category, productToChange);
	    	break;
		case 5:
			String storageLocation1 = UserInput.inputScanner("Indtast det nye gang nummer i butikslokationen for produktet");
	    	String storageLocation2 = UserInput.inputScanner("Indtast det nye hylde nummer i butikslokationen for produktet");
			String storageLocation = storageLocation1+":"+storageLocation2;
	    	productController.changeProductStorageLocation(storageLocation, productToChange);
			break;
		case 6:
			String warehouseLocation1 = UserInput.inputScanner("Indtast det nye gang nummer på lageret for produktet");
	    	String warehouseLocation2 = UserInput.inputScanner("Indtast det nye hylde nummer på lageret for produktet");
	    	String warehouseLocation = warehouseLocation1+":"+warehouseLocation2;
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
     if(choice < 8 && choice > 1) {
    	 printProductInformation(productToChange);
     }
    }
}