package Main;

import Business.MyStoreFront;

public class Driver {

	public static void main(String[] args) {
		MyStoreFront store = new MyStoreFront("Some Store!");
		store.initializeStore();
	}
}