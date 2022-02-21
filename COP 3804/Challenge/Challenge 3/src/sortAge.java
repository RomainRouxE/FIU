/*
 * Challenge 3 : Comparables with Pets
 * PID: 6322237 Section: COP 3804 Due: 02/21/2022
 * Driver class: sortVaccine class.
 * 
 * Summary: Read a file stock the data in arrayList, sort data and display it.
 */

import java.util.Comparator;

//Sort depending of Age from yongest to older.
//Compare Age.
//Return 1, -1 or 0
public class sortAge implements Comparator<Pet> {
	@Override
	public int compare(Pet n1, Pet n2) {
		if (n1.getAge().compareTo(n2.getAge()) > 0)
			return 1;
		else if (n1.getAge().compareTo(n2.getAge()) < 0)
			return -1;
		else
			return 0;
	}
}