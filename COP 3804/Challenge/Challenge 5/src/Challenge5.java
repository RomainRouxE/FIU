/*
 * Challenge 5 : Recursion
 * PID: 6322237 Section: COP 3804 Due: 03/28/2022
 * Driver class: driver.
 *
 * Summary: Get a string and change every 'y' to 'x' recursively
 */

import java.util.Scanner;

// Challenge5 class: driver class
public class Challenge5 {
	// changeXY method: get the first char of the string, return the char normally
	// or y if char was y. Call itself while string length > 0

	public static String changeXY(String str) {
		char x = 'x';
		char y = 'y';
		char letter;

		if (str.length() < 1) {
			return str;
		} else {
			if (x == str.charAt(0)) {
				letter = y;
			} else {
				letter = str.charAt(0);
			}
			return letter + changeXY(str.substring(1));
		}
	}

	// main method: get the user input and print the result from changeXY
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		System.out.println("Enter word to change:");
		String str = scnr.next();
		System.out.println(str + " change to: " + changeXY(str));
		scnr.close();
	}
}