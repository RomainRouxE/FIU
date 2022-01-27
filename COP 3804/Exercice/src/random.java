import java.util.Scanner;

public class random {

	static Scanner scnr = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("How many elements are in your array ?");
		int arraySize = scnr.nextInt();
		int[] rand = new int[arraySize];

		for (int i = 0; i < rand.length; i++) {
			System.out.println("Value for index " + i);
			rand[i] = scnr.nextInt();
		}

		int max = rand[1];
		for (int i = 0; i < rand.length; i++) {
			if (rand[i] > max)
				max = rand[i];
		}
		System.out.println(max);
	}

}
