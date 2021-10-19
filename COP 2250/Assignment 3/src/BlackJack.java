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

		int i = 4;
		for (String choice = "hit"; choice.equals("hit"); i++) {
			System.out.println("Hit or Stay?");
			choice = keyboard.nextLine();
			if (choice.equals("stay"))
				break;
			System.out.println("New card for " + playerName + ": " + deck[i]);
			playerScore += value(deck[i]);
			if (playerScore == 21) {
				System.out.println(playerName + " won!");
				System.exit(0);
			}
			if (playerScore > 21) {
				System.out.println(playerName + " lost!");
				System.exit(0);
			}
		}

		System.out.println("The first card of the dealer was: " + deck[1]);
		while (dealerScore < 17) {
			System.out.println("New card for dealer: " + deck[i]);
			dealerScore += value(deck[i]);
			i++;
		}

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

	}
}
