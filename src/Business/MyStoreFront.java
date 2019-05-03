package Business;

import java.util.ArrayList;
import java.util.Scanner;
import Business.Hardware.SingletonHardware;
import Business.Pastries.SingletonPastries;

public class MyStoreFront {

	private Hardware hardware = SingletonHardware.GetInstance();
	private Pastries pastries = SingletonPastries.GetInstance();
	private String storeNm;


	public MyStoreFront(String storeNm) {
		this.storeNm = storeNm;
	}

	public void initializeStore() {
		
		System.out.println("Welcome to " + storeNm);
		Scanner input = new Scanner(System.in);

		boolean done = false;
		boolean selection = false;
		ArrayList<String> totalItems = new ArrayList<>(pastries.getCandies().size() + pastries.getDesserts().size() + hardware.getUtensils().size());
		ArrayList<Double> prices = new ArrayList<>(pastries.getCandies().size() + pastries.getDesserts().size() + hardware.getUtensils().size());
		
		do {
			System.out.println("Please select which cathegory you want, press b to go back, q to quit, d for done\n\n 1. Candies\n2. Desserts\n3. Hardware");
			String stdIn = input.next();

			try {
				// Making the user selecting stuff
				if(stdIn.equals("q")) {
					System.exit(0);
				}else if(stdIn.equals("b")) {
					System.out.println("No back menu");
				}else if(stdIn.equals("d")) {
					done = true;
				}else if(Integer.parseInt(stdIn) == 1) {

					System.out.println("These are the items we offer");
					String[] candiesArr = pastries.getCandies().keySet().toArray(new String[pastries.getCandies().size()]);

					// Iterating through it
					for (int i = 0; i < candiesArr.length; i++) {
						System.out.println((i + 1) + ". " + candiesArr[i]);
					}

					System.out.println("Please select an item: ");

					do {
						try {
							
							stdIn = input.next();

							if(stdIn.equals("b")) {
								selection = true;
							}else if(stdIn.equals("q")) {
								System.exit(0);
							} else if(stdIn.equals("d")){
								selection = true;
							}else {
								boolean exists = false;
								for (int i = 0; i < candiesArr.length; i++) {
									if(Integer.parseInt(stdIn) - 1 > -1 && Integer.parseInt(stdIn) - 1 < candiesArr.length) {
										totalItems.add(candiesArr[Integer.parseInt(stdIn) - 1]);
										exists = true;
									}
								}
								if(exists) {
									System.out.println("You have selected item: " + candiesArr[Integer.parseInt(stdIn) - 1]);
								}else {
									System.out.println("Wrong item selected!");
								}
							}

						}catch(Exception e) {
							System.out.println("Wrong selection");
							selection = false;
						}
					}while(!selection);
				}else if(Integer.parseInt(stdIn) == 2) {
					System.out.println("These are the items we offer");
					String[] dessertsArr = pastries.getDesserts().keySet().toArray(new String[pastries.getDesserts().size()]);

					// Iterating through it
					for (int i = 0; i < dessertsArr.length; i++) {
						System.out.println((i + 1) + ". " + dessertsArr[i]);
					}
					
					System.out.println("Please select an item: ");

					do {
						try {
							
							// Getting the input
							stdIn = input.next();

							// DO NOT THOUCH!
							if(stdIn.equals("b")) {
								selection = true;
							}else if(stdIn.equals("q")) {
								System.exit(0);
							} else if(stdIn.equals("d")){
								selection = true;
							}else {
								boolean exists = false;
								for (int i = 0; i < dessertsArr.length; i++) {
									if(Integer.parseInt(stdIn) - 1 > -1 && Integer.parseInt(stdIn) - 1 < dessertsArr.length) {
										totalItems.add(dessertsArr[Integer.parseInt(stdIn) - 1]);
										exists = true;
									}
								}
								if(exists) {
									System.out.println("You have selected item: " + dessertsArr[Integer.parseInt(stdIn) - 1]);
								}else {
									System.out.println("Wrong item selected!");
								}
							}

						}catch(Exception e) {
							System.out.println("Wrong selection");
							selection = false;
						}
					}while(!selection);
				}else if(Integer.parseInt(stdIn) == 3) {
					System.out.println("These are the items we offer");
					String[] hdArr = hardware.getUtensils().keySet().toArray(new String[hardware.getUtensils().size()]);

					for (int i = 0; i < hdArr.length; i++) {
						System.out.println((i + 1) + ". " + hdArr[i]);
					}
					
					System.out.println("Please select an item: ");

					do {
						try {
							
							stdIn = input.next();

							// DO NOT THOUCH!
							if(stdIn.equals("b")) {
								selection = true;
							}else if(stdIn.equals("q")) {
								System.exit(0);
							} else if(stdIn.equals("d")){
								selection = true;
							}else {
								boolean exists = false;
								for (int i = 0; i < hdArr.length; i++) {
									if(Integer.parseInt(stdIn) - 1 > -1 && Integer.parseInt(stdIn) - 1 < hdArr.length) {
										totalItems.add(hdArr[Integer.parseInt(stdIn) - 1]);
										exists = true;
									}
								}
								if(exists) {
									System.out.println("You have selected item: " + hdArr[Integer.parseInt(stdIn) - 1]);
								}else {
									System.out.println("Wrong item selected!");
								}
							}
						}catch(Exception e) {
							System.out.println("Wrong selection");
							selection = false;
						}
					}while(!selection);
				}

				// If all good then get done
				if(stdIn.equals("d")) {
					done = true;
				}
				
				selection = false;

			}catch (Exception e) {
				System.out.println("Wrong selection!");
				done = false;
			}
		}while(!done);
		
		System.out.println("Items selected: " + totalItems.size());
		
		Double cost = 0.0;
		
		if(totalItems.size() > 0) {
			cost = calculateTotalCost(totalItems, prices);
			myFinalResult(cost, totalItems.toArray(new String[totalItems.size()]), prices.toArray(new Double[prices.size()]));
		}
		
		myFinalResult(cost, totalItems.toArray(new String[totalItems.size()]), prices.toArray(new Double[prices.size()]));
		
		return;
	}
	
	
	private Double calculateTotalCost(ArrayList<String> items, ArrayList<Double> prices) {
		
		// Calculating cost
		// Organizing wheter something exists or not!
		boolean h = false;
		boolean d = false;
		boolean c = false;
		
		String[] itemsArr = items.toArray(new String[items.size()]);
		
		Double result = 0.0;
		
		for (int i = 0; i < itemsArr.length; i++) {
			hardware.getUtensils().get(itemsArr[i]);
			
			if(hardware.getUtensils().get(itemsArr[i]) != null) {
				h = true;
			}
		}
		
		for (int i = 0; i < itemsArr.length; i++) {
			pastries.getCandies().get(itemsArr[i]);
			
			if(pastries.getCandies().get(itemsArr[i]) != null) {
				c = true;
			}
		}
		
		for (int i = 0; i < itemsArr.length; i++) {
			pastries.getDesserts().get(itemsArr[i]);
			
			if(pastries.getDesserts().get(itemsArr[i]) != null) {
				d = true;
			}
		}
		
		for (int i = 0; i < itemsArr.length; i++) {
			if(h && hardware.getUtensils().containsKey(itemsArr[i])) {
				result += hardware.getUtensils().get(itemsArr[i]);
				prices.add(hardware.getUtensils().get(itemsArr[i]));
			}
			
			if(c && pastries.getCandies().containsKey(itemsArr[i])) {
				result += pastries.getCandies().get(itemsArr[i]);
				prices.add(pastries.getCandies().get(itemsArr[i]));
			}
			
			if(d && pastries.getDesserts().containsKey(itemsArr[i])) {
				result += pastries.getDesserts().get(itemsArr[i]);
				prices.add(pastries.getDesserts().get(itemsArr[i]));
			}
		}
		
		return result;
	}
	

	private void myFinalResult(Double cost, String[] itemsSelected, Double[] prices) {
		
		// Final assessment
		for (int i = 0; i < itemsSelected.length; i++) {
			System.out.println(itemsSelected[i] + " " + prices[i]);
		}
		
		System.out.println("Your total cost: " + cost);
		System.out.println("GoodBye!");
	}
}