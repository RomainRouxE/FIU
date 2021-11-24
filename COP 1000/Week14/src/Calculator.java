
/**
 * Calculator program.
 * PID : 6322237
 * Subject : Java program. Get random number and random operator, ask for user result.
 */

// import random / scanner package
import java.util.Random;
import java.util.Scanner;

public class Calculator {
	// 2 global variables
	public static int numProblems = 0;
	public static int numCorrect = 0;

	// main method : we get 2 random number and a random number for the operation.
	// call the calculate method with the 2 numbers and the operator as parameters.
	// repeat 10 times.
	// call the summarize method.
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			Random newRandom = new Random();
			int ran1 = newRandom.nextInt(10) + 1;
			int ran2 = newRandom.nextInt(10) + 1;
			int operation = newRandom.nextInt(4) + 1;
			char operator;

			if (operation == 1)
				operator = '+';
			else if (operation == 2)
				operator = '-';
			else if (operation == 3)
				operator = 'x';
			else
				operator = '/';

			calculate(ran1, ran2, operator);
		}
		summarize();
	}

	// calculate function : we doing the calcul here and ask the user input.
	// If user correct increase numCorrect by 1.
	// Increase numProblems by 1.
	// Display if user was correct or not.
	// Use double for answer so can get not round division
	public static void calculate(int num1, int num2, char operator) {
		Scanner scnr = new Scanner(System.in);
		double correctAnswer;
		double userAnswer;

		if (operator == '+')
			correctAnswer = num1 + num2;
		else if (operator == '-')
			correctAnswer = num1 - num2;
		else if (operator == 'x')
			correctAnswer = num1 * num2;
		else
			correctAnswer = (double) num1 / (double) num2;

		System.out.println("What is " + num1 + operator + num2 + " ?");
		userAnswer = scnr.nextDouble();

		if (correctAnswer == userAnswer) {
			numCorrect++;
			System.out.println("Correct !");
		} else
			System.out.println("Wrong, it was " + correctAnswer);

		numProblems++;

	}

	// summarize method : get pourcent depanding of correct answer.
	// Print result
	public static void summarize() {
		System.out.println("You got " + numCorrect + " correct answer out of " + numProblems + " question.");
		int pourcent = numCorrect * 100 / numProblems;

		if (pourcent > 90)
			System.out.println("You got " + pourcent + "%, You did well");
		else if (pourcent > 70)
			System.out.println("You got " + pourcent + "%,  average");
		else if (pourcent > 60)
			System.out.println("You got " + pourcent + "%,  poorfly");
		else
			System.out.println("You got " + pourcent + "%,  failed");
	}
}