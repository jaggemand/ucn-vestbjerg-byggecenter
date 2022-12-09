package tui;
import java.util.Scanner;

public class UserInput {
     
     public UserInput(){
          
     }
     
	 public static int getIntegerFromUser() {
		Scanner keyboard = new Scanner(System.in);
        while (!keyboard.hasNextInt()) {
            System.out.println("Input skal være et tal - prøv igen");
            keyboard.nextLine();
        }
        return keyboard.nextInt();
    }
    
    public static String inputScanner(String question) {
    	System.out.println( question+": ");
    	Scanner console = new Scanner(System.in);
    	return console.nextLine();
    }
    public static int inputScannerNumber(String question) {   
        Scanner console = new Scanner(System.in);
        int number = 0;
        System.out.println(question+ ": ");
        
        while (!console.hasNextInt()){
            System.out.println("Input var ikke et tal, prøv igen!");
            console.nextLine();
        }
        number = console.nextInt();
        return number;
    }
}
