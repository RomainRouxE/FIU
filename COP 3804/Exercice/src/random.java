import java.util.Scanner;

public class random {
	public static Student[] createArrayOfStudents() {
		Student[] theSStudents = new Students[5];
		Student stud1 = new Student("abc", "def", 4.4, 4, "12345478");
		Student stud2 = new Student("abfc", "dhef", 3.4, 5, "17655678");
		Student stud3 = new Student("aebc", "dehf", 2.4, 6, "12345678");
		Student stud4 = new Student("dbc", "defn", 4.2, 4, "12365678");
		Student stud5 = new Student("cbc", "defn", 4.3, 4, "12345678");

		theStudent[0] = stud1;
		theStudent[1] = stud2;
		theStudent[2] = stud3;
		theStudent[3] = stud4;
		theStudent[4] = stud5;

		return theStudents;
	}

	static Scanner scnr = new Scanner(System.in);

	public static void main(String[] args) {
		Student[] myStudents = createArrayOfStudents();
	}

}
