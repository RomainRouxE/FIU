/*
 * Challenge 2 : Inheritance & Polymorphism in Holiday Celebrations
 * PID: 6322237 Section: COP 3804 Due: 02/09/2022
 * subclass: ChildrenTradition.
 * 
 * Summary: Read a file stock de data in arrayList and display them.
 */

import java.text.DecimalFormat;

/* ChildrenTradition class: get, set and display the data for children */
public class ChildrenTradition extends ParentsTradition {
	private String holidayGame;
	private String holidayStorybook;
	private String holidayMovie;
	private double costOfMovie;

	public ChildrenTradition(String holidayName, String holidaySeason, String timeOfDayCelebration, String mainDishName,
			int numberOfSideDishes, String locationOfGathering, double costOfMeal, String mealSource, int numberInvited,
			String traditionalDessert, String traditionalDrink, double costPerDessert, double costPerDrink,
			String holidayGame, String holidayStorybook, String holidayMovie, double costOfMovie) {

		super(holidayName, holidaySeason, timeOfDayCelebration, mainDishName, numberOfSideDishes, locationOfGathering,
				costOfMeal, mealSource, numberInvited, traditionalDessert, traditionalDrink, costPerDessert,
				costPerDrink);

		this.holidayGame = holidayGame;
		this.holidayStorybook = holidayStorybook;
		this.holidayMovie = holidayMovie;
		this.costOfMovie = costOfMovie;
	}

	/* get and set data */
	public String getHolidayGame() {
		return holidayGame;
	}

	public void setHolidayGame(String holidayGame) {
		this.holidayGame = holidayGame;
	}

	public String getHolidayStorybook() {
		return holidayStorybook;
	}

	public void setHolidayStorybook(String holidayStorybook) {
		this.holidayStorybook = holidayStorybook;
	}

	public String getHolidayMovie() {
		return holidayMovie;
	}

	public void setGetHolidayMovie(String holidayMovie) {
		this.holidayMovie = holidayMovie;
	}

	public double getCostOfMovie() {
		return costOfMovie;
	}

	public void setCostOfMovie(double costOfMovie) {
		this.costOfMovie = costOfMovie;
	}

	// print last part of data for children
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("##0.00");
		return super.toString() + "AND, before and after our meal we like to play " + holidayGame + ".\n"
				+ "We also like watching holiday movies like " + holidayMovie + " which usually cost about $"
				+ df.format(costOfMovie) + ".\n" + "At bedtime, we like to read holiday stories like "
				+ holidayStorybook + ". \n";
	}

	// Overide previous celebrate and print this one for children
	@Override
	public String celebrate() {
		return "We children like to celebrate the holidays like " + toString();
	}

	// Print cost of movie
	@Override
	public String tabulateCosts() {
		DecimalFormat df = new DecimalFormat("##0.00");
		return super.tabulateCosts() + " plus the cost of a movie: $" + df.format(costOfMovie);
	}
}
