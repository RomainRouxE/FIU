/*
 * Challenge 2 : Inheritance & Polymorphism in Holiday Celebrations
 * PID: 6322237 Section: COP 3804 Due: 02/09/2022
 * Driver class: driver.
 * 
 * Summary: Read a file stock de data in arrayList and display them.
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/* Driver class: driver */
public class driver {
	/* createTradition method: read the file and fil the arrayList with them. */
	public static ArrayList<Celebratable> createTraditions() throws IOException {
		// Open the file:
		File aFile = new File("holidays.txt");
		Scanner inFile = new Scanner(aFile); // Opens the file

		// Create an empty arrayList of Student objects:
		ArrayList<Celebratable> myTradition = new ArrayList<Celebratable>();

		// Define variables to hold data from file:
		GrandparentsTradition aGrandParent;
		ParentsTradition aParent;
		ChildrenTradition aChild;

		String typeRecord, holidayName, holidaySeason, timeOfDayCelebration, mainDishName, locationOfGathering;
		String mealSource, traditionalDessert, traditionalDrink, holidayGame, holidayStorybook, holidayMovie;
		int numberOfSideDishes, numberInvited;
		double costOfMeal, costPerDrink, costPerDessert, costOfMovie;

		// Read file until end
		while (inFile.hasNext()) {
			typeRecord = inFile.next();
			holidayName = inFile.next();
			holidaySeason = inFile.next();
			timeOfDayCelebration = inFile.next();
			mainDishName = inFile.next();
			numberOfSideDishes = inFile.nextInt();
			locationOfGathering = inFile.next();
			costOfMeal = inFile.nextDouble();
			mealSource = inFile.next();

			// if first letter equal g add array to aGrandparent
			if (typeRecord.equals("g")) {
				aGrandParent = new GrandparentsTradition(holidayName, holidaySeason, timeOfDayCelebration, mainDishName,
						numberOfSideDishes, locationOfGathering, costOfMeal, mealSource);
				myTradition.add(aGrandParent);

			} else if (typeRecord.equals("p") || typeRecord.equals("c")) {
				numberInvited = inFile.nextInt();
				traditionalDessert = inFile.next();
				traditionalDrink = inFile.next();
				costPerDessert = inFile.nextDouble();
				costPerDrink = inFile.nextDouble();

				// if first letter equal p add array to aGrandparent
				if (typeRecord.equals("p")) {
					aParent = new ParentsTradition(holidayName, holidaySeason, timeOfDayCelebration, mainDishName,
							numberOfSideDishes, locationOfGathering, costOfMeal, mealSource, numberInvited,
							traditionalDessert, traditionalDrink, costPerDessert, costPerDrink);
					myTradition.add(aParent);

				}
				// if first letter equal c add array to aGrandparent
				else {
					holidayGame = inFile.next();
					holidayStorybook = inFile.next();
					holidayMovie = inFile.next();
					costOfMovie = inFile.nextDouble();

					aChild = new ChildrenTradition(holidayName, holidaySeason, timeOfDayCelebration, mainDishName,
							numberOfSideDishes, locationOfGathering, costOfMeal, mealSource, numberInvited,
							traditionalDessert, traditionalDrink, costPerDessert, costPerDrink, holidayGame,
							holidayStorybook, holidayMovie, costOfMovie);
					myTradition.add(aChild);
				}
			}
		}
		// close file and return the arrayList
		inFile.close();
		return myTradition;
	}

	/*
	 * celebrateHolidays method: print the data by calling celebrate and tabulate
	 * cost. Print an empty line between each block for clearance
	 */
	public static void celebrateHolidays(ArrayList<Celebratable> myFamilyTraditions) {
		for (int i = 0; i < myFamilyTraditions.size(); i++) {
			System.out.print(myFamilyTraditions.get(i).celebrate());
			System.out.println(myFamilyTraditions.get(i).tabulateCosts());
			System.out.println("");
		}
	}

	/* Main method: create the arrayList and call the 2 other methods */
	public static void main(String[] args) throws IOException {
		ArrayList<Celebratable> myFamilyTraditions = createTraditions();
		celebrateHolidays(myFamilyTraditions);
	}

}
