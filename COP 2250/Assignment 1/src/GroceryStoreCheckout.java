/*
 * This is a program written by Romain ROUX
 * as a deliverable of Programming Assignment 1
 * for COP2250 - Fall 2021
 * Date: 09/16/2021
 */
import java.util.Scanner;

public class GroceryStoreCheckout {
	
    public static final double TAXR_PERCENTAGE = 6.75;//This is a constant value given by the assignment specification
    public static final double RICE_PRICE_PER_POUNDS = 1.25; //This is a constant value given by the assignment specification
    public static final double EGG_PRICE_PER_DOZEN = 14.75; //This is a constant value given by the assignment specification
    public static final double BREAD_PRICE_PER_LOAF = 2.75; //This is a constant value given by the assignment specification
    
    public static void main(String[] args) {
    	//Create scanner object
    	Scanner scnr = new Scanner(System.in);

    	//Print welcome messages
        System.out.println("\"Welcome to the checkout system of Awesome Grocery Store!\n");
        
        //Ask for user input for rice weight and read input
        System.out.println("Please enter the weight of rice in pounds: \"");
        double rice = scnr.nextDouble();

        //Ask for user input for eggs number and read input
        System.out.println("\"Now, enter the number of eggs: \"");
        double eggs = scnr.nextDouble();

        //Ask for user input for loafs of french bread and read input
        System.out.println("\"Now, enter the number of loafs of French bread: \"");
        double bread = scnr.nextDouble();

        //Declare subTotal | tax | total and calculate each of them with their formula
        double subtotal = rice * RICE_PRICE_PER_POUNDS + eggs * EGG_PRICE_PER_DOZEN / 12 + bread * BREAD_PRICE_PER_LOAF;
        double tax = subtotal * TAXR_PERCENTAGE / 100;
        double total = subtotal + tax;

        //Print the receipt
        System.out.println("\"Here is your receipt:");
        System.out.println("————————————–");
        
        //Use of printf to print 2 decimals
        System.out.printf("SUBTOTAL: $%.2f%n", subtotal);
        System.out.printf("TAX: $%.2f%n", tax );
        System.out.printf("TOTAL: $%.2f\"%n", total );

        //Close scanner object to avoid ressource leak
        scnr.close();
    }
}