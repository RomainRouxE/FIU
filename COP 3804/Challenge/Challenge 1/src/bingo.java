
/*
 * Challenge 1 : BINGO!
 * Romain Roux 6322237
 * Driver class bingo.
 * 
 * Summary: program to play the bingo game while the user want. Print the result at the end.
 */
import java.util.Random;
import java.util.Scanner;

/* driver class: bingo. */
public class bingo {
	public static int totalWins = 0;
	public static BingoCard myCard;

	/*
	 * playGame method: generate a random num between 1-75, 50 times and call
	 * checkBingo each times
	 */
	public static void playGame() {
		Random rand = new Random();
		for (int i = 0; i < 50; i++) {
			int ranNum = rand.nextInt(75) + 1;
			myCard.checkBingo(ranNum);
		}
	}

	/*
	 * determineWinner method: call gotBingo, then depandinf of the returned value
	 * print the result and increase totalWins by 1 if needed Ask user if he wants
	 * to play again and return it.
	 */
	public static String determineWinner() {
		String playAgain;
		Scanner scnr = new Scanner(System.in);

		if (myCard.gotBingo()) {
			System.out.println("You won !\n");
			totalWins++;
		} else
			System.out.println("You lost.\n");

		System.out.println("You have won " + totalWins + " game so far.");

		System.out.println("Do you want to play again ?");
		playAgain = scnr.next();
		// Close scnr only when user doesn t want to play again
		if (!playAgain.equalsIgnoreCase("yes"))
			scnr.close();

		return playAgain;
	}

	/* print method: call print */
	public static void print() {
		myCard.print();
	}

	/*
	 * main method: create new bingo board and call the other method. Repeat while
	 * user want to play again
	 */
	public static void main(String[] args) {
		String check = "yes";

		while (check.equalsIgnoreCase("yes")) {
			myCard = new BingoCard();
			myCard.setBingo();
			print();
			playGame();
			// print(); // print board after number checked
			check = determineWinner();
		}
		System.out.println("You won " + totalWins + " games. Bye");
	}
}
