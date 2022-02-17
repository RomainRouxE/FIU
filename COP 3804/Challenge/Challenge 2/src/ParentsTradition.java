/*
 * Challenge 2 : Inheritance & Polymorphism in Holiday Celebrations
 * PID: 6322237 Section: COP 3804 Due: 02/09/2022
 * subclass: ParentsTradition.
 * 
 * Summary: Read a file stock de data in arrayList and display them.
 */

import java.text.DecimalFormat;

/* ParentsTradition class: get, set and display the data for parents */
public class ParentsTradition extends GrandparentsTradition {
	private int numberInvited;
	private String traditionalDessert;
	private String traditionalDrink;
	private double costPerDessert;
	private double costPerDrink;

	public ParentsTradition(String holidayName, String holidaySeason, String timeOfDayCelebration, String mainDishName,
			int numberOfSideDishes, String locationOfGathering, double costOfMeal, String mealSource, int numberInvited,
			String traditionalDessert, String traditionalDrink, double costPerDessert, double costPerDrink) {

		super(holidayName, holidaySeason, timeOfDayCelebration, mainDishName, numberOfSideDishes, locationOfGathering,
				costOfMeal, mealSource);

		this.numberInvited = numberInvited;
		this.traditionalDessert = traditionalDessert;
		this.traditionalDrink = traditionalDrink;
		this.costPerDessert = costPerDessert;
		this.costPerDrink = costPerDrink;
	}

	/* get and set data */
	public int getNumberInvited() {
		return numberInvited;
	}

	public void setNumberInvited(int numberInvited) {
		this.numberInvited = numberInvited;
	}

	public String getTraditionalDessert() {
		return traditionalDessert;
	}

	public void setTraditionalDessert(String traditionalDessert) {
		this.traditionalDessert = traditionalDessert;
	}

	public String getTraditionalDrink() {
		return traditionalDrink;
	}

	public void setTraditionalDrink(String traditionalDrink) {
		this.traditionalDrink = traditionalDrink;
	}

	public double getCostPerDessert() {
		return costPerDessert;
	}

	public void setCostPerDessert(double costPerDessert) {
		this.costPerDessert = costPerDessert;
	}

	public double getCostPerDrink() {
		return costPerDrink;
	}

	public void setCostPerDrink(double costPerDrink) {
		this.costPerDrink = costPerDrink;
	}

	// print second part of data for children and parents
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("##0.00");
		return super.toString() + "We ALSO like to invite at least " + numberInvited
				+ " friends over to our house after our meal to eat " + traditionalDessert + " for dessert, and drink "
				+ traditionalDrink + ".\n" + "We pay $" + df.format(costPerDessert) + " for each dessert, and "
				+ df.format(costPerDrink) + " for each drink. \n";
	}

	// Overide previous celebrate and print this one for children and parents
	@Override
	public String celebrate() {
		return "We parents like to celebrate the holidays like " + toString();
	}

	// Print cost of (desert & drink) * num of guest
	@Override
	public String tabulateCosts() {
		DecimalFormat df = new DecimalFormat("##0.00");
		return super.tabulateCosts() + " plus the costs of desserts & drinks per guest: $"
				+ df.format((costPerDessert + costPerDrink) * numberInvited);
	}

}