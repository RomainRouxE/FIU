
/********************************************************************* 
    Author     : Roux Romain 
    Course     : CGS 3767 
    Professor  : Michael Robinson 
    Program    : pgm2
    Purpose    : hex to binary conversion / binary to decimal conversion / ascii display 
    Due Date   : 02/20/2022 

    Certification: 
    I hereby certify that this work is my own and none of it is the work of any other person. 


    ..........{ Romain Roux }.......... 
    
*********************************************************************/

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

// RouxR_OSpgm2 class
public class RouxR_OSpgm2 {
	// array to stock the hex, binary, decimal value.
	static String[][] hex_bin_dec = new String[8][3];

	// hexToBin method: convert hex value to binary.
	// read every value from hex string and create the binary with it.
	// regex used to delete the leading 0 if needed at the end
	public static String hexToBin(String hex) {
		String binary = "";
		for (int i = 0; i < hex.length(); i++) {
			char hexVal = hex.charAt(i);
			switch (hexVal) {
			case '0':
				binary = binary + "0000";
				break;
			case '1':
				binary = binary + "0001";
				break;
			case '2':
				binary = binary + "0010";
				break;
			case '3':
				binary = binary + "0011";
				break;
			case '4':
				binary = binary + "0100";
				break;
			case '5':
				binary = binary + "0101";
				break;
			case '6':
				binary = binary + "0110";
				break;
			case '7':
				binary = binary + "0111";
				break;
			case '8':
				binary = binary + "1000";
				break;
			case '9':
				binary = binary + "1001";
				break;
			case 'A':
				binary = binary + "1010";
				break;
			case 'B':
				binary = binary + "1011";
				break;
			case 'C':
				binary = binary + "1100";
				break;
			case 'D':
				binary = binary + "1101";
				break;
			case 'E':
				binary = binary + "1110";
				break;
			case 'F':
				binary = binary + "1111";
				break;
			default:
				binary = "Invalid number";
				break;
			}
		}
		String regex = "^0+(?!$)";
		binary = binary.replaceAll(regex, "");

		return binary;
	}

	// binToDec method: convert binary to decimal.
	// get each number and multiply by 2^n if value is 1
	public static String binToDec(String binary) {
		double decimal = 0;
		for (int i = 0; i < binary.length(); i++) {
			if (binary.charAt(i) == '1')
				decimal = decimal + Math.pow(2, binary.length() - i - 1);
		}
		DecimalFormat df = new DecimalFormat("#");
		df.setMaximumFractionDigits(15);

		return df.format(decimal);
	}

	public static void print(String[][] hex_bin_dec) {
		System.out.printf("Hex Error\t Binary\t\t\t\t\t\t Decimal\t\t Found at%n");
		for (int i = 0; i < hex_bin_dec.length; i++) {
			int chips;
			double chipVal = Double.parseDouble(hex_bin_dec[i][2]);
			if (chipVal < 34359738368D)
				chips = 1;
			else if (chipVal < 68719476738D)
				chips = 2;
			else if (chipVal < 103079215108D)
				chips = 3;
			else if (chipVal < 137438953478D)
				chips = 4;
			else if (chipVal < 171798691848D)
				chips = 5;
			else if (chipVal < 206158430218D)
				chips = 6;
			else if (chipVal < 240518168588D)
				chips = 7;
			else
				chips = 8;

			System.out.printf("%s =\t %-40s =\t %-15s  =\t %d%n", hex_bin_dec[i][0], hex_bin_dec[i][1],
					hex_bin_dec[i][2], chips);
		}
		System.out.println();
	}

	// lowerCase method: print the alphabetical and its ascii value
	public static void lowerCase() {
		for (char val = 'a'; val <= 'z'; val++) {
			System.out.printf("%s=%d ", val, (int) val);
		}
		System.out.println();
	}

	// upperCase method: print the alphabetical maj and its ascii value
	public static void upperCase() {
		for (char val = 'A'; val <= 'Z'; val++) {
			System.out.printf("%s=%d ", val, (int) val);
		}
		System.out.println();
	}

	// main method: read the file, store in array the value from the conversion
	// method
	// call the other methods.
	public static void main(String[] args) throws IOException {
		File aFile = new File("RAMerrors8x4f.5");
		Scanner inFile = new Scanner(aFile);

		for (int i = 0; inFile.hasNext(); i++) {
			hex_bin_dec[i][0] = inFile.next();
			hex_bin_dec[i][1] = hexToBin(hex_bin_dec[i][0]);
			hex_bin_dec[i][2] = binToDec(hex_bin_dec[i][1]);
		}
		inFile.close();

		print(hex_bin_dec);
		lowerCase();
		upperCase();
	}
}
