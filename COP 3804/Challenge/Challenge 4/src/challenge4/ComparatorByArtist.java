/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenge4;

import java.util.Comparator;

/**
 *
 * @author mtsguest
 */
public class ComparatorByArtist implements Comparator<Media> {

	public int compare(Media media1, Media media2) {
		return media1.getArtistName().compareTo(media2.getArtistName());
	}
}