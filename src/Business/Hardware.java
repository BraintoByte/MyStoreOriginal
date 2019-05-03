package Business;

import java.util.ArrayList;
import java.util.Hashtable;

public class Hardware {
	
	private Hashtable<String, Double> utensils = new Hashtable<>(4);
	
	private Hardware() {
		makeUtensils();
	}
	
	
	public static class SingletonHardware {
		
		private final static Hardware INSTANCE = new Hardware();
		
		public static Hardware GetInstance() {
			return INSTANCE;
		}
	}
	
	
	private void makeUtensils() {
		utensils.put("Hammer", 10.2);
		utensils.put("Shovel", 20.4);
		utensils.put("Drill", 22.2);
		utensils.put("Machete", 21.1);
	}
	
	public Hashtable<String, Double> getUtensils() {
		return utensils;
	}
}