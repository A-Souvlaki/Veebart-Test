package simple;

import java.util.*;

public class Snakes {

	public static void main(String[] args) {
		Game game = new Game();
		game.startGame();
	}

static class Game {
	
	/*
	 * Constant value which indicates the final square in the board,
	 *also acts like win condition.
	**/
	public final static int WIN = 25;
	
	/*
	 * Hashes used to represent snakes and leaders behavior.
	 */
	static Map<Integer, Integer> snakes = new HashMap<Integer, Integer>();;
	static Map<Integer, Integer> laders = new HashMap<Integer, Integer>();;
	
	{

			laders.put(3, 11);
			laders.put(6, 17);
			laders.put(9, 18);
			laders.put(10, 12);

			snakes.put(14, 14);
			snakes.put(19, 8);
			snakes.put(22, 20);
			snakes.put(24, 16);

	}
	
	/*
	 * This method is used to generate a random number between 1 and 6, 
	 * if the random number obtained equals 0, then is assigned as 1, otherwise
	 * a random number is returned.
	 */
	public static int rollDice() {

		Random r = new Random();
		int dice = r.nextInt(7);

		return (dice == 0 ? 1 : dice);
	}
	
	/*
	 * Helps to identify if the player has reached the final position in the board,
	 * true if the values is equals to WIN, otherwise false.
	 */
	public boolean checkWin(int pos) {

		return pos == WIN;
	}
	
	/*
	 * Calculates the player position in the board, if the player reaches a snake square,
	 * changes the position to a minor number; if the player reaches a lader square changes the position
	 * to a higher number.
	 * 
	 * The player position is defined by the actual position in the square and the dice value obtained
	 * in each roll
	 * 
	 *	
	 */
	public int position(int pos, int dice) {

		int actual = pos + dice;

		if (pos > WIN) {
			System.out.println("You have exceded by: " + (pos-WIN) +" squares");
			actual = WIN;
			return actual;
		}

		if (null != snakes.get(actual)) {
			System.out.println("A snake has swallowed you, your position: " + actual);
			actual = snakes.get(actual);
		}

		if (null != laders.get(actual)) {
			System.out.println("Climbing up :), your position: " + actual);
			actual = laders.get(actual);
		}
		
		return actual;

	}

	public void startGame() {

		int player1Position = 0;

		int currentPlayer = -1;

		Scanner scanner = new Scanner(System.in);

		int pressed = 0;
		int diceValue = 0;
		
		
		System.out.println("Simple Snakes and Laders Game!");
		
		
		do {
			
			if (currentPlayer == -1) {
				
				if (checkWin(player1Position)) {
					System.out.println("Congratulations! You've won");
					return;
				}
				
				System.out.println("Press '0' to roll Dice");

				pressed = scanner.nextInt();
				System.out.println("Last square: " + player1Position);
				diceValue = rollDice();
				
				System.out.println("Dice Value: " + diceValue);
				player1Position = position(player1Position, diceValue);
				
			
				System.out.println("Player New Position:" + player1Position);
				
				System.out.println("-------------------------");
				
				

				}
				currentPlayer = -currentPlayer;
			} while (0 == pressed);
		}
	}
}
