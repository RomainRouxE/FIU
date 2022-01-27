
/* Challenge 1: BINGO!
 * Romain Roux 6322237
 * domain class BingoCard
 */
import java.util.Random;

/* 
 * domain class: BingoCard
 * Every action on the bingo array are done here
 */
public class BingoCard {
	private static int[][] BingoCardArray = new int[5][5];

	/*
	 * setBingo method: generate 50 random number for the bingo.
	 */
	public void setBingo() {
		Random rand = new Random();
		for (int collumn = 0; collumn < BingoCardArray.length; collumn++) {
			for (int row = 0; row < BingoCardArray.length; row++) {
				// random betwwen 1-15
				if (collumn == 0)
					BingoCardArray[row][collumn] = rand.nextInt(15) + 1;
				// random betwwen 16-30
				if (collumn == 1)
					BingoCardArray[row][collumn] = rand.nextInt(15) + 16;
				// random betwwen 31-45
				if (collumn == 2)
					BingoCardArray[row][collumn] = rand.nextInt(15) + 31;
				// random betwwen 46-60
				if (collumn == 3)
					BingoCardArray[row][collumn] = rand.nextInt(15) + 46;
				// random betwwen 61-75
				if (collumn == 4)
					BingoCardArray[row][collumn] = rand.nextInt(15) + 61;
			}
		}
	}

	/*
	 * checkBingo method: compare each bingo number with the random number received.
	 * If numbers are equals replace by 0.
	 */
	public void checkBingo(int num) {
		for (int i = 0; i < BingoCardArray.length; i++)
			for (int j = 0; j < BingoCardArray.length; j++)
				if (BingoCardArray[i][j] == num)
					BingoCardArray[i][j] = 0;
	}

	/*
	 * gotBingo method: check each line and both diagonal to see if there is a
	 * bingo. Return true if bingo is found else return false.
	 */
	public boolean gotBingo() {
		int total = 0;

		for (int i = 0; i < BingoCardArray.length; i++) {
			for (int j = 0; j < BingoCardArray.length; j++) {
				total = total + BingoCardArray[i][j];
			}
			if (total == 0)
				return true;
			total = 0;
		}
		for (int i = 0; i < BingoCardArray.length; i++) {
			total = total + BingoCardArray[i][i];
		}
		if (total == 0)
			return true;
		total = 0;

		for (int i = 0; i < BingoCardArray.length; i++) {
			total = total + BingoCardArray[BingoCardArray.length - i - 1][i];
		}
		if (total == 0)
			return true;

		return false;
	}

	/* print method: print the bingo array. */
	public void print() {
		System.out.println("");
		for (int i = 0; i < BingoCardArray.length; i++) {
			for (int j = 0; j < BingoCardArray.length; j++) {
				System.out.print(BingoCardArray[i][j] + " ");
				// Make all the number inline.
				if (BingoCardArray[i][j] < 10)
					System.out.print(" ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
}
