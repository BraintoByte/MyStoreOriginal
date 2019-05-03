package Business;

import java.awt.List;
import java.util.ArrayList;
import java.util.Hashtable;

public class Pastries {
	
	private Hashtable<String, Double> desserts = new Hashtable(7);
	private Hashtable<String, Double> candies = new Hashtable(4);
	
	
	private Pastries() {
		makeDesserts();
		makeCandies();
	}
	
	
	public static class SingletonPastries {
		
		private final static Pastries INSTANCE = new Pastries();
		
		public static Pastries GetInstance() {
			return INSTANCE;
		}
	}
	
	
	private void makeDesserts() {
		desserts.put("Cream puff", 1.0);
		desserts.put("Cheesecake", 1.2);
		desserts.put("Chelsea bun", 1.5);
		desserts.put("Chiffon cake", 1.2);
		desserts.put("Chocolate cake", 2.0);
		desserts.put("Chokladboll", 2.0);
		desserts.put("Chorley cake", 1.4);
	}
	
	private void makeCandies() {
		candies.put("Twix", 1.0);
		candies.put("Mars", 1.2);
		candies.put("Jelly Beans", 1.0);
		candies.put("Sour Heads", 2.4);
	}
	
	public Hashtable<String, Double> getDesserts() {
		return desserts;
	}
	
	public Hashtable<String, Double> getCandies() {
		return candies;
	}
}