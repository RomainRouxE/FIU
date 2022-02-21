/*
 * Challenge 3 : Comparables with Pets
 * PID: 6322237 Section: COP 3804 Due: 02/21/2022
 * Driver class: sortVaccine class.
 * 
 * Summary: Read a file stock the data in arrayList, sort data and display it.
 */

import java.util.Comparator;

// Sort depending of year month day from most recent to oldest.
// Compare year, then if needed month and if needed day.
// Return 1, -1 or 0
public class sortVaccine implements Comparator<Pet> {
	@Override
	public int compare(Pet n1, Pet n2) {
		if (n1.getYear().compareTo(n2.getYear()) < 0)
			return 1;
		else if (n1.getYear().compareTo(n2.getYear()) > 0)
			return -1;
		else if (n1.getMonth().compareTo(n2.getMonth()) < 0)
			return 1;
		else if (n1.getMonth().compareTo(n2.getMonth()) > 0)
			return -1;
		else if (n1.getDay().compareTo(n2.getDay()) < 0)
			return 1;
		else if (n1.getDay().compareTo(n2.getDay()) > 0)
			return -1;
		else
			return 0;
	}
}