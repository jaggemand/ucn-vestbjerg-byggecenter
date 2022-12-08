package tui;
import java.util.Scanner;

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
                case 0:
                  running = false;
                  break;
                default:
                  System.out.println("En uforklarlig fejl er sket med choice = " + choice);
                  break;
            }
        }
    }
    
    private int writeSaleMenu() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("****** Salgsmenu ******");
        System.out.println(" (1) Opret salg");
        System.out.println(" (2) Slet salg");
        System.out.println(" (3) Se salg");
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
    
    private void startSale() {
    	createNewOrder();
    	scanProducts();
    }
    @SuppressWarnings("resource") private void scanProducts() {
        boolean running = true;
        while (running) {
            Scanner keyboard = new Scanner(System.in);
        	System.out.println("Scan stregkode eller indtast varenummer: ");
            String search = keyboard.nextLine().toLowerCase();
            switch (search) {
                case "done":
                  running = false;
                  if (orderController.getCurrentOrder().getOrderLines().size() <= 0) {
                	  System.out.println("Salget er annulleret, da den ikke indeholder nogle varer.");
                  }
                  completeSale();
                  break;
                default:
             	  System.out.println("Indtast antal af produktet: ");
                  int quantity = keyboard.nextInt();
                  Product foundProduct = productController.findProduct(search);
                  if (foundProduct != null) {
                	  orderController.addProduct(search, quantity);
                  } else {
                	  System.out.println("Fejl! Produktet kan ikke findes i systemet");
                  }
                  break;
            }
        }
    }
    
    private void createNewOrder() {
    	orderController.createOrder();
    }
    
    private boolean addProductToOrder(String search, int quantity) {
        boolean success = false;
    	success = orderController.addProduct(search, quantity);
    	return success;
    }
    
    private void completeSale() {
        printInvoice(orderController.getCurrentOrder());
    	orderController.addOrder();
    }
    
    private void printInvoice(Order o){
        int size =  o.getOrderLines().size();
        if(size != 0){
        System.out.println("Faktura for ordre: " + o.getOrderNumber());
        System.out.println("Ordren indeholder: " + size + " antal produkter");
        System.out.println("Varer: " + "\n");
        for(OrderLine e : o.getOrderLines()){
            String name = e.getProduct().getName();
            int quantity = e.getQuantity();
            double costPrice = e.getProduct().getCostPrice();
            double total = (e.getProduct().getCostPrice()*e.getQuantity());
            String id = e.getProduct().getProductID();
            System.out.println("----------------------------------------");
            System.out.println("Produkt navn : " + name + "\tProdukt antal: " + quantity);
            System.out.printf("Produkt navn: %s \tProdukt antal: %d",name,quantity);
            System.out.println("Produkt individuel pris : " + costPrice + 
            " Total Pris for Produkt: " + total);
            System.out.println("ProduktID: " + id + "\n");
        }
        System.out.println("Total pris: " + o.getTotalPrice());
        }
    }
}

