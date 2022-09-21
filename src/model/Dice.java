package model;

import java.util.Random;

public class Dice {
	
	 public static int rollDice() {
		 Random r = new Random();
		 int dice = r.nextInt(7);

		return (dice == 0 ? 1 : dice);
	 }

}
