/*
 * Author    : Romain ROUX
 * Course    : CGS 3767. 
 * Professor : Michael Robinson 
 * Program # : pgm1 
 * { A brief description of the program }
 * 
 * Due Date  : 01/03/2022 
 * 
 * 
 * Certification: 
 * I hereby certify that this work is my own and none of it is the work of any other person. 
 * 
 * ..........{ Romain ROUX }..........
*/

public class rouxRubuntuPgm1 {
	public static void processUbuntu(String name, double number, String word) {
		System.out.printf("%s%n", name);
		System.out.printf("%.2f%n", number);
		System.out.printf("%S%n", word);
		System.out.println("3 advantages of VM:");
		System.out.println("Run multiple operating system\n");
		System.out.println("Security\n");
		System.out.println("Easy recovery\n");
		System.out.println("Major VM problem: its slow\n");
	}

	public static void main(String args[]) {
		System.out.printf("Editor used: vi%n");
		System.out.printf("Command to save the program: :w%n");
		System.out.printf("Command to exit vi without saving: :q!%n");

		processUbuntu("Roux", 2021.99, "Fall");
	}
}
