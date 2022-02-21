/*
 * Challenge 3 : Comparables with Pets
 * PID: 6322237 Section: COP 3804 Due: 02/21/2022
 * Driver class: driver.
 * 
 * Summary: Read a file stock the data in arrayList, sort data and display it.
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*Driver class*/
public class driver {
	public static ArrayList<Pet> createPet() throws IOException {
		// Open the file:
		File aFile = new File("pet.txt");
		Scanner inFile = new Scanner(aFile); // Opens the file

		// Create an empty arrayList of pet objects:
		ArrayList<Pet> pet = new ArrayList<Pet>();

		for (int j = 0; j < 10; ++j) {
			pet.add(new Pet(inFile.next(), inFile.next(), inFile.nextInt(), inFile.nextInt(), inFile.nextInt(),
					inFile.nextInt(), inFile.next(), inFile.next(), inFile.next()));
		}
		inFile.close();
		return pet;
	}

	// sortAge method: call the sortAge method
	public static ArrayList<Pet> sortAge(ArrayList<Pet> pet) {
		Collections.sort(pet, new sortAge());

		return pet;
	}

	// printYoungOld method: print the youngest and oldest pet by calling the
	// PrintYoungOld method in pet class
	public static void printYoungOld(ArrayList<Pet> pet) {
		System.out.println("Youngest pet is: " + pet.get(0).PrintYoungOld());
		System.out.println("Oldest pet is: " + pet.get(pet.size() - 1).PrintYoungOld());
		System.out.println();
	}

	// sortVaccine method: call the sortAge sortVaccine
	public static ArrayList<Pet> sortVaccine(ArrayList<Pet> pet) {
		Collections.sort(pet, new sortVaccine());

		return pet;
	}

	// printVaccine method: print the youngest and oldest pet by calling the
	// toString method in pet class
	public static void printVaccine(ArrayList<Pet> pet) {
		for (int i = 0; i < pet.size(); i++) {
			System.out.println(pet.get(i));
		}
	}

	// main method: call every other method on driver class
	public static void main(String[] args) throws IOException {
		ArrayList<Pet> pet = createPet();
		sortAge(pet);
		printYoungOld(pet);
		sortVaccine(pet);
		printVaccine(pet);
	}
}
