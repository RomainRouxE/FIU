/*
 * This is a program written by Romain ROUX
 * as a deliverable of Programming Assignment 2
 * for COP2250 - Fall 2021
 * Date: 10/02/2021
 */

import java.util.Scanner;

public class SevenSegmentDisplay {

	public static void main(String[] args) {
		// Create scanner object
		Scanner scnr = new Scanner(System.in);

		// Declaration of the variable used to stock the 3 user inputs
		int hor = 0;
		int ver = 0;
		int digit = 0;

		// Print Welcome message and ask for length of horizontal segments. Repeat if
		// the input isn't between 3 and 40
		System.out.println("Welcome to my 7-segment display\n");
		while (hor < 3 || hor > 40) {
			System.out.println("Please enter the length of horizontal segments (from 3 to 40):");
			hor = scnr.nextInt();
			if (hor < 3 || hor > 40)
				System.out.println("Length is not acceptable. Please enter length between 3 and 40");
		}

		// Ask for length of vertical segments. Repeat if the input isn't between 3 and
		// 40 or if the input if twice bigger/smaller than horizontal value
		while (ver < 3 || ver > 40 || ver < hor / 2 || ver > hor * 2) {
			System.out.println("Now, enter the length of vertical segments (from 3 to 40):");
			ver = scnr.nextInt();
			if (ver < 3 || ver > 40 || ver < hor / 2 || ver > hor * 2)
				System.out.println("Length is not acceptable. Please enter length between 3 and 40");
		}

		/*
		 * While digit != 99 repeat the loop. No while(true) to avoid compilation
		 * warning/error. Ask for digit number. Repeat if the input isn't between 3 and
		 * 40 else print the seven-segment representation of that digit
		 */
		while (digit != 99) {
			System.out.println("Enter a digit:");
			digit = scnr.nextInt();
			if (digit < 0 || digit > 9)
				System.out.println("The digit is not acceptable. Please enter a digit between 0 and 9");
			else {

				// Declaration of 2 int variable. i used to print the right number of line
				// vertically. j used to print the middle line and the count how many time we
				// print the vertical segments.
				int i;
				int j;

				// Declaration of 2 String variable to stock space and star, that way able to
				// use the .repeat() function
				String space = " ";
				String ast = "*";

				/*
				 * Declaration of 5 string to print every segments. 2 horizontal segments :
				 * top/empty. 3 vertical segments : both / left / right top : concatenate the 2
				 * spaces at start with the number of "*" the user wanted from ver variable
				 * empty : empty line with right number of spaces from ver variable + 2 at start
				 * + 2 at the end
				 */
				String top = "  " + ast.repeat(hor) + "  ";
				String empty = space.repeat(hor + 4);
				/*
				 * both : concatenate the 2 * at start / end with the number of spaces the user
				 * wanted from ver variable left : concatenate the 2 * at start with the number
				 * of spaces the user wanted from ver variable and with the 2 spaces at the end
				 * both : concatenate the 2 * at end with the number of spaces the user wanted
				 * from ver variable and with the 2 spaces at the start
				 */
				String both = "**" + space.repeat(hor) + "**";
				String left = "**" + space.repeat(hor + 2);
				String right = space.repeat(hor + 2) + "**";

				/*
				 * If / else if to determine which number to print depending on user input.
				 * 
				 * Every number follow the same logic Print twice first horizontal segment,
				 * either top or empty Print vertical segment the number of time the user wanted
				 * (ver variable) : either both / left / right Print twice the 2nd horizontal
				 * segment using j: either top / empty Print the 2nd vertical segment the number
				 * of time the user wanted (ver variable) : either both / left / right Print
				 * twice the 3rd horizontal segment : either top / empty
				 */
				if (digit == 0) {
					System.out.println(top);
					System.out.println(top);
					for (j = 0; j < 2; j++) {
						for (i = 0; i < ver; i++)
							System.out.println(both);
						if (j == 0) {
							System.out.println(empty);
							System.out.println(empty);
						}
					}
					System.out.println(top);
					System.out.println(top);
				} else if (digit == 1) {
					System.out.println(empty);
					System.out.println(empty);
					for (j = 0; j < 2; j++) {
						for (i = 0; i < ver; i++)
							System.out.println(right);
						if (j == 0) {
							System.out.println(empty);
							System.out.println(empty);
						}
					}
					System.out.println(empty);
					System.out.println(empty);
				} else if (digit == 2) {
					System.out.println(top);
					System.out.println(top);
					for (j = 0; j < 2; j++) {
						for (i = 0; i < ver; i++) {
							if (j == 0)
								System.out.println(right);
							else
								System.out.println(left);
						}
						if (j == 0) {
							System.out.println(top);
							System.out.println(top);
						}
					}
					System.out.println(top);
					System.out.println(top);
				} else if (digit == 3) {
					System.out.println(top);
					System.out.println(top);
					for (j = 0; j < 2; j++) {
						for (i = 0; i < ver; i++)
							System.out.println(right);
						if (j == 0) {
							System.out.println(top);
							System.out.println(top);
						}
					}
					System.out.println(top);
					System.out.println(top);
				} else if (digit == 4) {
					System.out.println(empty);
					System.out.println(empty);
					for (j = 0; j < 2; j++) {
						for (i = 0; i < ver; i++) {
							if (j == 0)
								System.out.println(both);
							else
								System.out.println(left);
						}
						if (j == 0) {
							System.out.println(top);
							System.out.println(top);
						}
					}
					System.out.println(empty);
					System.out.println(empty);
				} else if (digit == 5) {
					System.out.println(top);
					System.out.println(top);
					for (j = 0; j < 2; j++) {
						for (i = 0; i < ver; i++) {
							if (j == 0)
								System.out.println(left);
							else
								System.out.println(right);
						}
						if (j == 0) {
							System.out.println(top);
							System.out.println(top);
						}
					}
					System.out.println(top);
					System.out.println(top);
				} else if (digit == 6) {
					System.out.println(top);
					System.out.println(top);
					for (j = 0; j < 2; j++) {
						for (i = 0; i < ver; i++) {
							if (j == 0)
								System.out.println(left);
							else
								System.out.println(both);
						}
						if (j == 0) {
							System.out.println(top);
							System.out.println(top);
						}
					}
					System.out.println(top);
					System.out.println(top);
				} else if (digit == 7) {
					System.out.println(top);
					System.out.println(top);
					for (j = 0; j < 2; j++) {
						for (i = 0; i < ver; i++)
							System.out.println(right);
						if (j == 0) {
							System.out.println(empty);
							System.out.println(empty);
						}
					}
					System.out.println(empty);
					System.out.println(empty);
				} else if (digit == 8) {
					System.out.println(top);
					System.out.println(top);
					for (j = 0; j < 2; j++) {
						for (i = 0; i < ver; i++)
							System.out.println(both);
						if (j == 0) {
							System.out.println(top);
							System.out.println(top);
						}
					}
					System.out.println(top);
					System.out.println(top);
				} else if (digit == 9) {
					System.out.println(top);
					System.out.println(top);
					for (j = 0; j < 2; j++) {
						for (i = 0; i < ver; i++) {
							if (j == 0)
								System.out.println(both);
							else
								System.out.println(right);
						}
						if (j == 0) {
							System.out.println(top);
							System.out.println(top);
						}
					}
					System.out.println(top);
					System.out.println(top);
				}
			}
		}
		// Close scnr object to avoid ressource leak
		scnr.close();
	}
}
