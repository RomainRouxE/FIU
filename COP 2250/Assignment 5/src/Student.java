/*
 * This is a program written by Romain ROUX
 * as a deliverable of Programming Assignment 5
 * for COP2250 - Fall 2021
 * Date: 12/02/2021
 */

public class Student {
	private String firstName = "noName";
	private String lastName = "NoLastName";
	private int pID = 0;
	private int grade = 0;

	// Set students with given parameters
	public void setStudents(String givenFirstName, String givenLastName, int givenPID, int givenGrade) {
		firstName = givenFirstName;
		lastName = givenLastName;
		pID = givenPID;
		grade = givenGrade;
	}

	// return grade of student
	public int getGrade() {
		return grade;
	}

	// Return pid of student
	public int getPID() {
		return pID;
	}

	// return full name of student
	public String getFullName() {
		return firstName + " " + lastName;
	}

	// Set new grade for student
	public void setGrade(int newGrade) {
		grade = newGrade;
	}
}
