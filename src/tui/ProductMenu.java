package tui;
import java.util.Scanner;



/**
 * Write a description of class LoanMenu here.
 *
 * @author Mogens Holm Iversen
 * @version 0.1.0 Initial draft version 
 */
public class ProductMenu {
    // instance variables
    

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
                  System.out.println(" Dette er ikke implementeret endnu!");
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
    
    private int writeProductMenu() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("****** Produktmenu ******");
        System.out.println(" (1) Opret produkt");
        System.out.println(" (2) Slet produkt");
        System.out.println(" (3) Ændre produkt");
        System.out.println(" (4) Opfyld produkt");
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
    
    private void createProduct() {
    	
    }
}