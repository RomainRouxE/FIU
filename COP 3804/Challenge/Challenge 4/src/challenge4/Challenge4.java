/*
 * Challenge 4 : Comparable/Comparator Interfaces AND Exception Handling
 * PID: 6322237 Section: COP 3804 Due: 03/16/2022
 * Driver class: driver.
 * 
 * Summary: Read a file stock the data in arrayList, ask the user what he wanst to do: ask more record - search media - search artist.
 */

package challenge4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

// Driver class
public class Challenge4 {
	ArrayList<Media> myMedia = new ArrayList<Media>();

	// readFileCreate method: read file and create record
	public void readFileCreate() {
		String fileName;
		Scanner keyboard = new Scanner(System.in);
		boolean fileNotFound, invalidRecord;
		Scanner inFile = null;
		File aFile;
		String record;

		do {
			try {
				System.out.println("What is the name of the file?");
				fileName = keyboard.nextLine();
				aFile = new File(fileName);
				inFile = new Scanner(aFile); // opens the file
				fileNotFound = false;
			} catch (FileNotFoundException e) {
				fileNotFound = true;
			}

		} while (fileNotFound == true);

		// Loop to read each record from file and create objects to put in Media
		// arrayList:
		invalidRecord = false;

		do {
			try {
				while (inFile.hasNext()) {
					record = inFile.nextLine();
					createObject(record);
					invalidRecord = false;
				}

			} catch (NumberFormatException e) {
				invalidRecord = true;
				// inFile.nextLine();

			}
		} while (invalidRecord == true);

		inFile.close();
	}

	// createObject method
	public void createObject(String aRecord) {
		String myArray[] = aRecord.split(" ");
		int year;
		ArrayList<String> coStars;
		ArrayList<String> songs;
		String artistName;
		String mediaName;
		DVD myDVD;
		CD myCD;

		// case line start with D for DVD
		if (myArray[0].equalsIgnoreCase("D")) {
			artistName = myArray[1];
			mediaName = myArray[2];
			year = Integer.parseInt(myArray[3]);
			coStars = new ArrayList<String>();
			for (int i = 4; i < myArray.length; i++) {
				coStars.add(myArray[i]);
			}
			myDVD = new DVD(artistName, mediaName, year, coStars);
			myMedia.add(myDVD);
			// Case line start with C for CD
		} else if (myArray[0].equalsIgnoreCase("C")) {
			artistName = myArray[1];
			mediaName = myArray[2];
			songs = new ArrayList<String>();
			for (int i = 3; i < myArray.length; i++) {
				songs.add(myArray[i]);
			}
			myCD = new CD(artistName, mediaName, songs);
			myMedia.add(myCD);
		}
	}

	// sortByMediaPrint method: sort by media and print it
	public void sortByMediaPrint() {
		Collections.sort(myMedia);
		System.out.println("List of Records by Media: ");
		System.out.println(myMedia);
		// Create a loop that accesses each object in the myMedia
	}

	// sortByArtistPrint method: sort by artist name and print it.
	public void sortByArtistPrint() {
		Collections.sort(myMedia, new ComparatorByArtist());
		System.out.println("\n*******************************************");
		System.out.println("List of Records by Artist: ");
		System.out.println(myMedia);
	}

	// showMenu method: display menu and ask user while he s entering wrong int
	public int showMenu() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println();
		System.out.println("1. Search by Media");
		System.out.println("2. Search by Artist");
		System.out.println("3. Create new record");
		System.out.println("4. Exit");
		boolean menu = false;
		int menuOption = 1;
		do {
			try {
				menuOption = keyboard.nextInt();
				menu = true;
			} catch (InputMismatchException e) {
				System.out.println("You must enter 1 - 2 - 3 or 4 only");
				keyboard.next();
			}
		} while (!menu);
		return menuOption;
	}

	// searchByMedia method: sort by media, ask the user a media name, and do a
	// binarySearch to find the name in records.
	public void searchByMedia() {
		// define a String mediaName variable, int location of search object in
		// arrayList
		Scanner scnr = new Scanner(System.in);
		String mediaName;
		int location;
		// Sort by Media order
		Collections.sort(myMedia);
		// Ask the user for the name of the media that they wish to find
		System.out.println("Enter the name of the media");
		mediaName = scnr.next();
		// Create a searchObject of type Media, using only media name
		Media searchObject = new Media(null, mediaName);
		// Do binary search on myMedia using searchObject
		location = Collections.binarySearch(myMedia, searchObject);
		// Check the value of the location returned... If it is < 0 , say "not found"
		if (location < 0)
			System.out.println("not found\n");
		// Otherwise, get the object in myMedia and display it to the user
		else
			System.out.println(myMedia.get(location));
	}

	// searchByArtist method: sort by artist name, ask the user an artist name, and
	// do a
	// binarySearch to find the name in records.
	public void searchByArtist() {
		// define a String artistName variable, int location of search object in
		// arrayList
		Scanner scnr = new Scanner(System.in);
		String artistName;
		int location;
		// Sort by Artist order - Collections.sort(myMedia, new
		// ComparatorByArtist());
		Collections.sort(myMedia, new ComparatorByArtist());
		// Ask the user for the name of the artist that they wish to find
		System.out.println("Enter the name of the artist");
		artistName = scnr.next();
		// Create a searchObject of type Media, using only artist name.
		Media searchObject = new Media(artistName, null);
		// Do binary search on myMedia using searchObject and new
		// ComparatorByArtist()
		location = Collections.binarySearch(myMedia, searchObject, new ComparatorByArtist());
		// Check value of location returned from binary search
		// if it is -1, say "not found"
		if (location < 0)
			System.out.println("not found\n");
		// Otherwise, do a loop going backwards until the artistName
		// is no longer equal to the searchObject
		else {
			for (int i = 0; location - i >= 0 && artistName.equals(myMedia.get(location - i).getArtistName()); i++)
				System.out.print(myMedia.get(location - i));
			// Then, do another loop going forwards & printing until the artistName
			// is no longer equal to the searchObject
			for (int i = 1; location + i < myMedia.size()
					&& artistName.equals(myMedia.get(location + i).getArtistName()); i++)
				System.out.print(myMedia.get(location + i));
		}
		System.out.println();
	}

	// addToMedia method: create a CD or DVD object if the info are correct then
	// call the writeToFile method
	public void addToMedia() {
		// Define variables needed for all types of media (CD or DVD)
		Scanner scnr = new Scanner(System.in);
		String mediaType;
		ArrayList<String> coStars;
		ArrayList<String> Songs;
		int year;
		String artistName;
		String mediaName;
		String costars, song;
		DVD myDVD;
		CD myCD;
		// Ask user for type of media they wish to add, and then ask for all the data
		// for that specific media
		// Create either DVD object or CD object
		// Add the DVD or CD object to myMedia arrayList
		System.out.println("What media do you want to add ?");
		mediaType = scnr.next();
		while (!mediaType.equals("D") && !mediaType.equals("C")) {
			System.out.println("Enter a correct media: C or D");
			mediaType = scnr.next();
		}
		try {
			if (mediaType.equals("D")) {
				System.out.println("Enter the artist name.");
				artistName = scnr.next();
				System.out.println("Enter the movie name.");
				mediaName = scnr.next();
				System.out.println("Enter the movie year.");
				year = scnr.nextInt();
				while (year < 1900) {
					System.out.println("The year must be over 1900.");
					year = scnr.nextInt();
				}
				coStars = new ArrayList<String>();
				System.out.println("Enter all the costart artist. Enter done when over");
				costars = scnr.next();
				while (!costars.equals("done")) {
					coStars.add(costars);
					costars = scnr.next();
				}
				myDVD = new DVD(artistName, mediaName, year, coStars);
				myMedia.add(myDVD);
				writeToFile(myDVD);
			} else {
				System.out.println("Enter the artist name.");
				artistName = scnr.next();
				System.out.println("Enter the cd name.");
				mediaName = scnr.next();
				Songs = new ArrayList<String>();
				System.out.println("Enter the cd songs. Enter done when over");
				song = scnr.next();
				while (!song.equals("done")) {
					Songs.add(song);
					song = scnr.next();
				}
				myCD = new CD(artistName, mediaName, Songs);
				myMedia.add(myCD);
				writeToFile(myCD);
			}
		} catch (Exception e) {
			System.out.println("You entered something wrong.");
		}
		// Call a method to Open the file again for output & write the record to it
		// writeToFile(DVD or CD object)

	}

	// writeToFile method: write the info into the catalog2-1.txt file
	public void writeToFile(Media aMediaObj) {
		Scanner keyboard = new Scanner(System.in);
		String fileName = "catalog2.txt";
		boolean fileNotFound = true;
		FileWriter fw;
		PrintWriter pw = null;

		do {
			try {
				fw = new FileWriter(fileName, true);
				pw = new PrintWriter(fw);
				fileNotFound = false;
			} catch (IOException e) {
				System.out.println("Sorry, that file does not exist.  Please enter another one.");
				fileName = keyboard.nextLine();
				fileNotFound = true;
			}
		} while (fileNotFound);

		String DVDRecord = "", CDRecord = "";

		if (aMediaObj instanceof DVD) {
			// concatenate every field in the DVD into the DVDRecord string
			DVDRecord += "D ";
			DVDRecord += aMediaObj.getArtistName() + " " + aMediaObj.getMediaName() + " ";
			DVDRecord += ((DVD) aMediaObj).getMovieYear();
			if (((DVD) aMediaObj).getMyCoStars().size() > 0) {
				for (int i = 0; i < ((DVD) aMediaObj).getMyCoStars().size(); i++) {
					DVDRecord += " " + ((DVD) aMediaObj).getMyCoStars().get(i);
				}
			}

			pw.println(DVDRecord);

		} else {
			// concatenate every field in the CD into the CDRecord string
			CDRecord += "C ";
			CDRecord += aMediaObj.getArtistName() + " " + aMediaObj.getMediaName();
			if (((CD) aMediaObj).getMySongs().size() > 0) {
				for (int i = 0; i < ((CD) aMediaObj).getMySongs().size(); i++) {
					CDRecord += " " + ((CD) aMediaObj).getMySongs().get(i);
				}
			}
			pw.println(CDRecord);
		}
		pw.close();
	}

	// main method: call all the functions and repeat while user enter 4 to quit
	public static void main(String[] args) {
		Challenge4 myChallenge = new Challenge4();
		myChallenge.readFileCreate();

		myChallenge.sortByMediaPrint();
		myChallenge.sortByArtistPrint();

		int menuOption;
		do {
			menuOption = myChallenge.showMenu();
			if (menuOption == 1) {
				myChallenge.searchByMedia();
			} else if (menuOption == 2) {
				myChallenge.searchByArtist();
			} else if (menuOption == 3) {
				myChallenge.addToMedia();
			} else
				System.out.println("Thanks!  Good-bye.");

		} while (menuOption != 4);
	}
}
