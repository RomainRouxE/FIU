/*
 * Challenge 8 : Adopt-a-Pet
 * PID: 6322237 Section: COP 3804 Due: 04/19/2022
 * Challenge8 class: driver.
 *
 * Summary: Sort array using different sorting algo and compare the sorting time
 */

package challenge8;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

// Challenge 8 class
public class Challenge8 {
	static PriorityQueue<Pet> AnimalShelter = new PriorityQueue<>();
	static Queue<Pet> Dog = new LinkedList<>();
	static Queue<Pet> Cat = new LinkedList<>();
	static Scanner scnr = new Scanner(System.in);

	// isBdayCorrect method: check if entered bday date is correct return true of
	// false depending
	public static boolean isBdayCorrect(int year, int month, int day) {
		if (year < 2000 || year > 2022) {
			System.out.println("The year wasn t correct, try again");
			return false;
		}
		if (month < 1 || month > 12) {
			System.out.println("The month wasn t correct, try again");
			return false;
		}
		if (day < 1 || day > 31) {
			System.out.println("The day wasn t correct, try again");
			return false;
		}
		return true;
	}

	// donateCat method: ask the user for cat info and add cat/pet in queue
	public static void donateCat() {
		String name, species;
		int dateOfBirth, year = -1, month = -1, day = -1;
		long chips = 0;
		boolean bdayCorrect = false;

		// try/catch to avoid string instead of int and if something wrong happen while
		// creating object
		try {
			System.out.println("Enter the cat name");
			name = scnr.next();
			System.out.println("Enter the cat species");
			species = scnr.next();
			System.out.println("Enter the cat birth date");
			// Repeat if bday is not correct
			while (!bdayCorrect) {
				year = scnr.nextInt();
				month = scnr.nextInt();
				day = scnr.nextInt();
				bdayCorrect = isBdayCorrect(year, month, day);
			}
			dateOfBirth = year * 10000 + month * 100 + day;
			chips = 10;
			Pet pet = new Pet(name, dateOfBirth, species, chips);
			Cat.add(pet);
			AnimalShelter.add(pet);
		} catch (Exception e) {
			System.out.println("Something went wrong try again");
		}
	}

	// donateDog method: ask the user for dog info and add dog/pet in queue
	public static void donateDog() {
		String name, species;
		int dateOfBirth, year = -1, month = -1, day = -1;
		long chips = 0;
		boolean bdayCorrect = false;

		// try/catch to avoid string instead of int and if something wrong happen while
		// creating object
		try {
			System.out.println("Enter the dog name");
			name = scnr.next();
			System.out.println("Enter the dog species");
			species = scnr.next();
			System.out.println("Enter the dog birth date");
			// Repeat if bday is not correct
			while (!bdayCorrect) {
				year = scnr.nextInt();
				month = scnr.nextInt();
				day = scnr.nextInt();
				bdayCorrect = isBdayCorrect(year, month, day);
			}
			dateOfBirth = year * 10000 + month * 100 + day;
			chips = 10;
			Pet pet = new Pet(name, dateOfBirth, species, chips);
			Dog.add(pet);
			AnimalShelter.add(pet);
		} catch (Exception e) {
			System.out.println("Something went wrong try again");
		}
	}

	// adoptCat method: compare the first cat to the first pet in pet queue. If not
	// equal put the elem of pet queue in tmp queue and compare again until it
	// match. Delete from cat and pet when it match. Copy the tmp queue into pet
	// queue at the end.
	public static void adoptCat() {
		boolean found = false;
		PriorityQueue<Pet> tmp = new PriorityQueue<>();

		if (Cat.isEmpty()) {
			System.out.println("No cat available atm");
			found = true;
		}

		while (!AnimalShelter.isEmpty() && found == false) {
			if (AnimalShelter.peek().equals(Cat.peek())) {
				System.out.println(Cat.remove());
				AnimalShelter.remove();
				tmp.addAll(AnimalShelter);
				AnimalShelter.clear();
				AnimalShelter.addAll(tmp);
				found = true;
			} else {
				tmp.add(AnimalShelter.peek());
				AnimalShelter.remove();
			}
			if (AnimalShelter.isEmpty()) {
				AnimalShelter.addAll(tmp);
				System.out.println("No cat available atm");
				found = true;
			}
		}
	}

	// adoptDog method: compare the first dog to the first pet in pet queue. If not
	// equal put the elem of pet queue in tmp queue and compare again until it
	// match. Delete from cat and pet when it match. Copy the tmp queue into pet
	// queue at the end.
	public static void adoptDog() {
		boolean found = false;
		PriorityQueue<Pet> tmp = new PriorityQueue<>();

		if (Dog.isEmpty()) {
			System.out.println("No dog available atm");
			found = true;
		}

		while (!AnimalShelter.isEmpty() && found == false) {
			if (AnimalShelter.peek().equals(Dog.peek())) {
				System.out.println(Dog.remove());
				AnimalShelter.remove();
				tmp.addAll(AnimalShelter);
				AnimalShelter.clear();
				AnimalShelter.addAll(tmp);
				found = true;
			} else {
				tmp.add(AnimalShelter.peek());
				AnimalShelter.remove();
			}
			if (AnimalShelter.isEmpty()) {
				AnimalShelter.addAll(tmp);
				found = true;
			}
		}
	}

	// adoptAnimal method: compare the first pet to the first cat in cat queue. If
	// not equal put the elem of pet queue in tmp queue and compare again until it
	// match. Delete from cat and pet when it match. Copy the tmp queue into pet
	// queue at the end.
	// Repeat same process for dog if it wasn t a cat.
	public static void adoptAnimal() {
		boolean found = false, isCat = false;
		Queue<Pet> tmp = new LinkedList<>();

		if (AnimalShelter.isEmpty())
			System.out.println("No pet available atm");
		else {
			while (!Cat.isEmpty() && found == false) {
				if (Cat.peek().equals(AnimalShelter.peek())) {
					System.out.println(AnimalShelter.remove());
					Cat.remove();
					tmp.addAll(Cat);
					Cat.clear();
					Cat.addAll(tmp);
					found = true;
					isCat = true;
				} else {
					tmp.add(Cat.peek());
					Cat.remove();
				}
				if (Cat.isEmpty()) {
					Cat.addAll(tmp);
					found = true;
				}
			}
			tmp.clear();
			found = false;
			while (!Dog.isEmpty() && found == false && isCat == false) {
				if (Dog.peek().equals(AnimalShelter.peek())) {
					System.out.println(AnimalShelter.remove());
					Dog.remove();
					tmp.addAll(Dog);
					Dog.clear();
					Dog.addAll(tmp);
					found = true;
				} else {
					tmp.add(Dog.peek());
					Dog.remove();
				}
				if (Dog.isEmpty()) {
					Dog.addAll(tmp);
					found = true;
				}
			}
		}
	}

	// showPet method: print all 3 queue
	public static void showPet() {
		System.out.println("Cat queue:");
		System.out.println(Cat);
		System.out.println("Dog queue:");
		System.out.println(Dog);
		System.out.println("Priority queue:");
		System.out.println(AnimalShelter);
	}

	// main method: display menu until user exit
	// Use of string for input so we don t have to do try/catch for type error
	// call function depending of the number entered
	public static void main(String[] args) {
		String choice = "";

		while (!choice.equals("7")) {
			System.out.println();
			System.out.println("0. Add New Microchips");
			System.out.println("1. Donate a Cat");
			System.out.println("2. Donate a Dog");
			System.out.println("3. Adopt a Cat");
			System.out.println("4. Adopt a Dog");
			System.out.println("5. Adopt Oldest Pet");
			System.out.println("6. Show Pets");
			System.out.println("7. Exit");
			choice = scnr.next();

			if (choice.equals("1"))
				donateCat();
			else if (choice.equals("2"))
				donateDog();
			else if (choice.equals("3"))
				adoptCat();
			else if (choice.equals("4"))
				adoptDog();
			else if (choice.equals("5"))
				adoptAnimal();
			else if (choice.equals("6"))
				showPet();
			else if (choice.equals("7")) {
				System.out.println("Thank you for visiting the Animal Shelter. Please support us and come again");
				System.exit(0);
			}
		}
		scnr.close();
	}
}
