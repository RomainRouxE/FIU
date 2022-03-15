/*
 * Challenge 4 : Comparable/Comparator Interfaces AND Exception Handling
 * PID: 6322237 Section: COP 3804 Due: 03/16/2022
 * Driver class: media class.
 * 
 * Summary: Read a file stock the data in arrayList, ask the user what he wanst to do: ask more record - search media - search artist.
 */

package challenge4;

// media class
public class Media implements Comparable<Media> {

	private String artistName;
	private String mediaName;

	public Media(String artistName, String mediaName) {
		this.artistName = artistName;
		this.mediaName = mediaName;
	}

	// getArtistName
	public String getArtistName() {
		return artistName;
	}

	// setArtistName
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	// getMediaName
	public String getMediaName() {
		return mediaName;
	}

	// setMediaName
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	// print media
	@Override
	public String toString() {
		return "Media{" + "artistName=" + artistName + ", mediaName=" + mediaName + '}';
	}

	// compare media
	public int compareTo(Media aMedia) {
		return this.mediaName.compareTo(aMedia.mediaName);
	}
}
