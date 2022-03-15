/*
 * Challenge 4 : Comparable/Comparator Interfaces AND Exception Handling
 * PID: 6322237 Section: COP 3804 Due: 03/16/2022
 * Driver class: CD class.
 * 
 * Summary: Read a file stock the data in arrayList, ask the user what he wanst to do: ask more record - search media - search artist.
 */

package challenge4;

import java.util.ArrayList;

// CD class
public class CD extends Media {
	private ArrayList<String> mySongs;

	public CD(String artistName, String mediaName, ArrayList<String> mySongs) {
		super(artistName, mediaName);
		this.mySongs = new ArrayList<String>(mySongs);

	}

	// getMySongs
	public ArrayList<String> getMySongs() {
		return mySongs;
	}

	// setMySongs
	public void setMySongs(ArrayList<String> mySongs) {
		this.mySongs = new ArrayList<String>(mySongs);
	}

	// print CD with superclass
	@Override
	public String toString() {
		return "\n" + super.toString() + "CD{" + "mySongs=" + mySongs + '}';
	}
}
