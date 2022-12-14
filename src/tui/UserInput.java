package tui;

import java.util.Scanner;

/**
 * It contains methods that makes it easier to get input from the user
 */
public class UserInput {

    /**
     * It reads an integer from the keyboard, and if the user enters something that
     * is not an integer,
     * it prints an error message and asks the user to try again
     * 
     * @return The method returns the next integer that the user inputs.
     */
    @SuppressWarnings("resource")
    public static int getIntegerFromUser() {
        Scanner keyboard = new Scanner(System.in);
        while (!keyboard.hasNextInt()) {
            System.out.println("Input skal være et tal - prøv igen");
            keyboard.nextLine();
        }
        return keyboard.nextInt();
    }

    /**
     * It takes a string as an argument, prints it to the console, and returns the
     * user's input
     * 
     * @param question The question you want to ask the user.
     * @return The input from the user.
     */
    @SuppressWarnings("resource")
    public static String inputScanner(String question) {
        System.out.println(question + ": ");
        Scanner console = new Scanner(System.in);
        return console.nextLine();
    }

    /**
     * It takes a string as input, and returns an integer
     * 
     * @param question The question you want to ask the user.
     * @return The method returns the number that the user has inputted.
     */
    @SuppressWarnings("resource")
    public static int inputScannerNumber(String question) {
        Scanner console = new Scanner(System.in);
        int number = 0;
        System.out.println(question + ": ");

        while (!console.hasNextInt()) {
            System.out.println("Input var ikke et tal, prøv igen!");
            console.nextLine();
        }
        number = console.nextInt();
        return number;
    }

    /**
     * It waits for the user to press enter, and then clears the terminal
     */
    @SuppressWarnings("resource")
    public static void waitAndClearTerminal() {
        System.out.println("Tryk enter for at fortsætte");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        clearTerminal();
    }

    /**
     * It prints out 22 blank lines
     */
    public static void clearTerminal() {
        for (int i = 0; i < 22; i++) {
            System.out.println(" ");
        }
    }
}
