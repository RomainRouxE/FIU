import java.util.ArrayList;
import java.util.Scanner;

public class GradeBook {
	// Boolean checking for user pid and grade input are correct. If correct return
	// true else return false and print error message
	public static boolean checkInput(int pid, int grade) {
		String stringPID = String.valueOf(pid);
		if (stringPID.length() != 7 || stringPID.charAt(0) == '0') {
			System.out.println("PID must be a 7 digit value and not starting with a 0.\n");
			return false;
		}
		if (grade < 0 || grade > 100) {
			System.out.println("Grade need to be between 0 and 100.\n");
			return false;
		}
		return true;

	}

	// Method to get average grade. Addition every grade on the student and return
	// it divided by the number of grades.
	public static double getAverageRating(ArrayList<Student> list) {
		int ratingsSum = 0;

		for (int i = 0; i < list.size(); ++i) {
			ratingsSum += list.get(i).getGrade();
		}
		return ((double) ratingsSum / list.size());
	}

	// Method to get minimun grade. Look every grade in the student and compare to
	// the min. Return min.
	public static int findMinimum(ArrayList<Student> list) {
		int min = 100;

		for (int i = 0; i < list.size(); ++i) {
			if (list.get(i).getGrade() < min)
				min = list.get(i).getGrade();
		}
		return min;
	}

	// Method to get maximum grade. Look every grade in the student and compare to
	// the max. Return max.
	public static int findMaximum(ArrayList<Student> list) {
		int max = 0;

		for (int i = 0; i < list.size(); ++i) {
			if (list.get(i).getGrade() > max)
				max = list.get(i).getGrade();
		}
		return max;
	}

	// Method to get grade for a given pID. Compare the given pID to the pid
	// student. if equal return the pid student grade
	// Return -1 if pid wasn t found.
	public static int findGrade(ArrayList<Student> list, int givenPID) {
		for (int i = 0; i < list.size(); ++i) {
			if (givenPID == list.get(i).getPID()) {
				return list.get(i).getGrade();
			}
		}

		return -1;
	}

	// Method to get grade for a given pID. Compare the given pID to the pid
	// student. If equal return the pid student first name and last name.
	// Return -1 if pid wasn t found
	public static String findFullName(ArrayList<Student> list, int givenPID) {
		for (int i = 0; i < list.size(); ++i) {
			if (givenPID == list.get(i).getPID()) {
				return list.get(i).getFullName();
			}
		}

		return "-1";
	}

	// Method to get grade for a given pID. Compare the given pID to the pid
	// student. If equal set the student grade to new grade and return full name.
	// Return -1 if pid wasn t found or if grade wasn t between 0 and 100.
	public static String changeGrade(ArrayList<Student> list, int givenPID, int newGrade) {
		if (newGrade <= 100 && newGrade >= 0) {
			for (int i = 0; i < list.size(); ++i) {
				if (givenPID == list.get(i).getPID()) {
					list.get(i).setGrade(newGrade);
					return list.get(i).getFullName();
				}
			}
		}

		return "-1";
	}

	public static void main(String[] args) {
		// Declaration of arraylist and other varible used.
		ArrayList<Student> list = new ArrayList<Student>();
		Scanner scnr = new Scanner(System.in);
		Student currStudent;
		String firstName = "";
		String lastName;
		int pid;
		int grade;

		// Print welcome message
		System.out.println("Welcome to my grade book version 2!");
		System.out.println("Please enter the information of the first student using the following format:");
		System.out.println("\"firstName lastName PID grade\".");
		System.out.println("Press Enter when you are done.");

		// While user enter DONE get new student. Manage int type error with try/catch
		// and then with checkinput method. If true then set students else print error
		// message
		while (!firstName.equals("DONE")) {
			try {
				firstName = scnr.next();
				if (firstName.equals("DONE"))
					break;
				lastName = scnr.next();
				pid = scnr.nextInt();
				grade = scnr.nextInt();
				if (checkInput(pid, grade)) {
					currStudent = new Student();
					currStudent.setStudents(firstName, lastName, pid, grade);
					list.add(currStudent);
				}
			} catch (Exception e) {
				System.out.println("You entered something wrong. Please re-enter.\n");
			}
			System.out.println("Please enter the information of the next student using the same format.");
			System.out.println("If there is no more students, please enter the keyword \"DONE\".");
			System.out.println("Press Enter when you are done.");
		}

		// While user enter quit get new commands and call the right method. Error
		// handling on every command which need 2nd arguments with try/catch. If no
		// error print the result.
		String choice = "";
		while (!choice.equals("quit")) {
			System.out.println("Please enter a new command.\n");

			choice = scnr.next();
			if (choice.equals("average"))
				System.out.println("Average grade: " + getAverageRating(list));
			else if (choice.equals("min"))
				System.out.println("Minimun grade : " + findMinimum(list));
			else if (choice.equals("max"))
				System.out.println("Maximum grade : " + findMaximum(list));
			else if (choice.equals("grade")) {
				try {
					pid = scnr.nextInt();
					int gradePID = findGrade(list, pid);
					if (gradePID == -1)
						System.out.println("The pID you entered doesn't exist please try again.\n");
					else
						System.out.println("Student " + pid + " got a grade of : " + gradePID);
				} catch (Exception e) {
					System.out.println("You entered something wrong. Please re-enter.\n");
				}
			} else if (choice.equals("name")) {
				try {
					pid = scnr.nextInt();
					String namePID = findFullName(list, pid);
					if (namePID.equals("-1"))
						System.out.println("The pID you entered doesn't exist please try again.\n");
					else
						System.out.println("Student " + pid + " is named : " + namePID);
				} catch (Exception e) {
					System.out.println("You entered something wrong. Please re-enter.\n");
				}
			} else if (choice.equals("change")) {
				try {
					pid = scnr.nextInt();
					grade = scnr.nextInt();
					String namePID = changeGrade(list, pid, grade);
					if (namePID.equals("-1"))
						System.out.println("The pID or the grade you entered isn t correct please try again.\n");
					else
						System.out.println(
								"Student " + pid + " is named : " + namePID + " and got a new grade of " + grade);
				} catch (Exception e) {
					System.out.println("You entered something wrong. Please re-enter.\n");
				}
			}

			else if (choice.equals("quit"))
				System.exit(0);
			else {
				System.out.println("Please enter a correct command :");
				System.out.println("average - min - max - grade [PID] - name [PID] - quit\n");
			}
		}
		scnr.close();
	}
}
