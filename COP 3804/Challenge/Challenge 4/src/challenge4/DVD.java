/*
 * Challenge 4 : Comparable/Comparator Interfaces AND Exception Handling
 * PID: 6322237 Section: COP 3804 Due: 03/16/2022
 * Driver class: DVD class.
 * 
 * Summary: Read a file stock the data in arrayList, ask the user what he wanst to do: ask more record - search media - search artist.
 */

package challenge4;

import java.util.ArrayList;

// DVD class
public class DVD extends Media {
	private int movieYear;
	private ArrayList<String> myCoStars;

	public DVD(String artistName, String mediaName, int movieYear, ArrayList<String> myCoStars) {
		super(artistName, mediaName);
		this.movieYear = movieYear;
		this.myCoStars = new ArrayList<String>(myCoStars);
	}

	// getMovieYear
	public int getMovieYear() {
		return movieYear;
	}

	// setMovieYear
	public void setMovieYear(int movieYear) {
		this.movieYear = movieYear;
	}

	// getMyCoStars
	public ArrayList<String> getMyCoStars() {
		return myCoStars;
	}

	// setMyCoStars
	public void setMyCoStars(ArrayList<String> myCoStars) {
		this.myCoStars = new ArrayList<String>(myCoStars);
	}

	// print DVD with superclass
	@Override
	public String toString() {
		return "\n" + super.toString() + "DVD{" + "movieYear=" + movieYear + ", myCoStars=" + myCoStars + '}';
	}

}
