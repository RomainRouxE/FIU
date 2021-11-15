import java.util.Scanner;

/*
 * This is a program written by Romain ROUX
 * as a deliverable of Programming Assignment 4
 * for COP2250 - Fall 2021
 * Date: 11/09/2021
 */

public class GradeBook {

	public static final int STUDENTS_LIMIT = 100;

	// Addition all the grades and divide by number of students
	// Print the result
	public static double findAverage(int[] grades, int numberOfStudents) {
		double average = 0;

		for (int i = 0; i < numberOfStudents; i++) {
			average += grades[i];
		}
		System.out.println("Average = " + average / numberOfStudents);
		return average;

	}

	// Search for min grade and print it
	public static int findMinimum(int[] grades, int numberOfStudents) {
		int min = 100;

		for (int i = 0; i < numberOfStudents; i++) {
			if (grades[i] < min)
				min = grades[i];
		}
		System.out.println("Min grade = " + min);
		return min;

	}

	// Search for max grade and print it
	public static int findMaximum(int[] grades, int numberOfStudents) {
		int max = 0;

		for (int i = 0; i < numberOfStudents; i++) {
			if (grades[i] > max)
				max = grades[i];
		}
		System.out.println("Max grade = " + max);
		return max;

	}

	// Using pIDs array and input pid to do so instead of the pid only, which is
	// impossible
	// Search for the student PID in the pid array
	// If the pid is found break and return the position in array otherwise return
	// -1 for error
	public static int findGrade(int[] pIDs, int pID) {
		int position = -1;
		for (int i = 0; i < pIDs.length; i++) {
			if (pIDs[i] == pID) {
				position = i;
				break;
			}
		}
		return position;
	}

	// Using pIDs array and input pid to do so instead of the pid only, which is
	// impossible
	// Search for the student PID in the pid array
	// If the pid is found break and return the position in array otherwise return
	// -1 for error
	public static int findFullName(int[] pIDs, int pID) {
		int position = -1;
		for (int i = 0; i < pIDs.length; i++) {
			if (pIDs[i] == pID) {
				position = i;
				break;
			}
		}
		return position;
	}

	public static void main(String[] args) {
		String[] firstNames = new String[STUDENTS_LIMIT];
		String[] lastNames = new String[STUDENTS_LIMIT];
		int[] pIDs = new int[STUDENTS_LIMIT];
		int[] grades = new int[STUDENTS_LIMIT];

		Scanner scnr = new Scanner(System.in);

		// input used to get user input
		// i used to count student
		String input = "";
		int i;

		System.out.println("Welcome to my grade book!");
		System.out.println("Please enter the information of the first student using the following format:");
		System.out.println("\"firstName lastName PID grade\".");
		System.out.println("Press Enter when you are done.");

		// Ask user for input while user enter DONE
		// Get the whole line and split it by space separator and put everything in the
		// right array
		for (i = 0;; i++) {
			input = scnr.nextLine();
			String[] split = input.split("\\s+");
			if (split[0].equals("DONE"))
				break;

			firstNames[i] = split[0];
			lastNames[i] = split[1];
			pIDs[i] = Integer.parseInt(split[2]);
			grades[i] = Integer.parseInt(split[3]);

			System.out.println("Please enter the information of the next student using the same format.");
			System.out.println("If there is no more students, please enter the keyword \"DONE\".");
			System.out.println("Press Enter when you are done.");
		}

		// While user doesn t enter quit repeat the loop
		// Execute command wanted by user - if user enter wrong command print error
		// message
		// Print every output in each method
		for (String choice = ""; !choice.equals("quit");) {
			System.out.println("Please enter a new command");
			choice = scnr.next();
			if (choice.equals("average"))
				findAverage(grades, i);
			else if (choice.equals("min"))
				findMinimum(grades, i);
			else if (choice.equals("max"))
				findMaximum(grades, i);
			else if (choice.equals("grade")) {
				// pidSpot use to stock the pid position in pid array
				// If findGrade return -1 mean pid wasn t found and error
				// Else it return the pid number so we print the grade of that position which is
				// that student grade.
				int pid = scnr.nextInt();
				int pidSpot = findGrade(pIDs, pid);
				if (pidSpot == -1)
					System.out.println("PID entered wasn t correct");
				else
					System.out.println("PID : " + pid + " got a grade of : " + grades[pidSpot]);
			} else if (choice.equals("name")) {
				// pidSpot use to stock the pid position in pid array
				// If findGrade return -1 mean pid wasn t found and error
				// Else it return the pid number so we print the first name of that position
				// which is that student first name. Do the same for last name.
				int pid = scnr.nextInt();
				pid = findFullName(pIDs, pid);
				if (pid == -1)
					System.out.println("PID entered wasn t correct");
				else
					System.out.println("First name : " + firstNames[pid] + " Last name : " + lastNames[pid]);
			} else if (choice.equals("quit"))
				System.exit(0);
			else {
				System.out.println("Please enter a correct command :");
				System.out.println("average - min - max - grade [PID] - name [PID] - quit");
			}
		}
	}
}
