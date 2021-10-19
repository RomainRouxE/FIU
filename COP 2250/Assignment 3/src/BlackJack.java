/*
 * This is a program written by Romain ROUX
 * as a deliverable of Programming Assignment 3
 * for COP2250 - Fall 2021
 * Date: 10/19/2021
 */

import java.util.Random;
import java.util.Scanner;

public class BlackJack {
	public static void initialize(String[] deck) {
		String[] suits = { " Heart", " Diamand", " Clubs", " Spades" };
		int i, j, rand;
		Random random = new Random();
		for (i = 0; i < suits.length; i++) {
			deck[13 * i] = "A" + suits[i];
			for (j = 2; j < 11; j++)
				deck[13 * i + j - 1] = j + suits[i];
			deck[13 * i + 10] = "J" + suits[i];
			deck[13 * i + 11] = "Q" + suits[i];
			deck[13 * i + 12] = "K" + suits[i];
		}
		String temp;
		for (i = 0; i < deck.length - 1; i++) {
			rand = i + random.nextInt(deck.length - i);
			temp = deck[i];
			deck[i] = deck[rand];
			deck[rand] = temp;
		}
	}

	public static int value(String card) {
		try (Scanner scanner = new Scanner(card)) {
			scanner.useDelimiter("[^0-9]");
			return Character.isDigit(card.charAt(0)) ? scanner.nextInt() : card.charAt(0) == 'A' ? 11 : 10;
		}
	}

	public static void main(String[] args) {
		String[] deck = new String[52];
		initialize(deck); // initializes and shuffles the deck of cards. Don't delete
		Scanner keyboard = new Scanner(System.in);
		String playerName;
		System.out.print("Enter the player's name: ");
		playerName = keyboard.nextLine();
		int playerScore = 0;
		int dealerScore = 0;
		System.out.println("Game starts... Dealer is dealing...");
		System.out.println("First card for " + playerName + ": " + deck[0]);// deck[0] for the player
		playerScore += value(deck[0]);
		System.out.println("First card for dealer: Hidden");// deck[1] for the dealer
		dealerScore += value(deck[1]);
		System.out.println("Second card for " + playerName + ": " + deck[2]);// deck[2] for the player
		if (deck[2].startsWith("A") && playerScore > 10)// Ace's value is 1 or 11
			playerScore++;// Ace's value is 1 here...
		else
			playerScore += value(deck[2]);// Ace's value is 11 here...
		if (playerScore == 21)// game ends early in favor of the player
			System.out.println(playerName + " won!");
		System.out.println("Second card for dealer: " + deck[3]);// deck[3] for the dealer
		if (deck[3].startsWith("A") && dealerScore > 10)// Ace's value is 1 or 11
			dealerScore++;// Ace's value is 1 here...
		else
			dealerScore += value(deck[3]);// Ace's value is 11 here...
		// Your code comes HERE...

		// i used to get the card from the deck. Initialised to 4 because the 4th first
		// cards already been given.
		int i = 4;

		// While the player want to get a new card, give a new card to the player and
		// add its value to the playerScore
		for (String choice = "hit"; choice.equals("hit");) {
			System.out.println("Hit or Stay?");
			choice = keyboard.nextLine();
			// If the player enter "stay" leave the loop.
			if (choice.equals("stay"))
				break;
			// If the player enter "hit" draw a new card fo the player and add its value to
			// the playerScore using the value function.
			// Increase i to get the next card if needed.
			else if (choice.equals("hit")) {
				System.out.println("New card for " + playerName + ": " + deck[i]);
				// If card is ace and playerScore > 10 ace value is 1
				if (deck[i].startsWith("A") && playerScore > 10)
					playerScore++;
				else
					playerScore += value(deck[i]);
				i++;
			}
			// If the player do a mistake enter something different than "hit" or "stay",
			// says than he needs to enter "stay" or "hit" only.
			else {
				System.out.println("You need to enter stay or hit");
				choice = "hit";
			}

			// Check for player score if its over 21 or equals 21.
			// If playerScore equals 21 player won and exit program.
			// If playerScore s over 21 player loose and exit program.
			if (playerScore == 21) {
				System.out.println(playerName + " won!");
				System.exit(0);
			}
			if (playerScore > 21) {
				System.out.println(playerName + " lost!");
				System.exit(0);
			}
		}

		// Reveal the first dealer card.
		// While dealer score inferior to 17 draw new card and add its value to the
		// dealder score.
		System.out.println("The first card of the dealer was: " + deck[1]);
		while (dealerScore < 17) {
			System.out.println("New card for dealer: " + deck[i]);
			// If card is ace and dealerScore > 10 ace value is 1
			if (deck[i].startsWith("A") && dealerScore > 10)
				dealerScore++;
			else
				dealerScore += value(deck[i]);
			i++;
		}

		// Print the result depanding of the player and dealer score.
		// Then exit the program.
		if (dealerScore > 21 || dealerScore < playerScore) {
			System.out.println(playerName + " won!");
			System.exit(0);
		} else if (dealerScore == playerScore) {
			System.out.println("Its a tie!");
			System.exit(0);
		} else {
			System.out.println("The dealer won!");
			System.exit(0);
		}
		// Close scanner to avoid leaks.
		keyboard.close();
	}
}
