package app;
import java.util.*;

import model.Board;
import model.Dice;
import model.Ladder;
import model.Player;
import model.Snake;

public class BoardApp {
	
	private Board board;
	
	private static final int BOARD_SIZE = 25;
	
	/**
	 * Attributes for the bonus
	 */
    private int initialPlayers;
    private Queue<Player> players; 
    
    public BoardApp(int boardSize) {
    	
        this.board= new Board(boardSize);
        this.players = new LinkedList<Player>();
    }
    
    //Constructor for the default size
    public BoardApp() {
        this(BoardApp.BOARD_SIZE);
    }

    /**
     * This method is used to set the players behavior
     */

    public void setPlayers(List<Player> players) {
    	
        this.players = new LinkedList<Player>();
        this.initialPlayers = players.size();
        Map<Integer, Integer> playerPieces = new HashMap<Integer, Integer>();
        
        for (Player player : players) {
            this.players.add(player);
            playerPieces.put(player.getNumber(), 0); //setting a player at a predefined position, in this case 0
        }
        board.setPlayerPieces(playerPieces); 
    }
    
    
    /*
     * Methods used to add a customized group of snakes and ladders to the game
     */
    public void setSnakes(List<Snake> snakes) {
        board.setSnakes(snakes); 
    }

    public void setLadders(List<Ladder> ladders) {
        board.setLadders(ladders);
    }

    /**
     * Calculates the player position in the board, if the player reaches a snake square,
	 * changes the position to a minor number; if the player reaches a lader square changes the position
	 * to a higher number.
	 * 
     */
    
        
    /*
     * For a custom player, get its position in the board and allows to calculate a new
     * position square, based on the value defined by the method rollDice    
     */
    private void movePlayer(Player player, int actual) {
        
    	int oldPosition = board.getPlayerPieces().get(player.getNumber());
        int newPosition = oldPosition + actual;

        int boardSize = board.getSize();

        if (newPosition > boardSize) {
      
            newPosition = oldPosition; //Avoid bypass the board size for the winners.
        } else {
            newPosition = calculatePosition(newPosition);
        }

        board.getPlayerPieces().put(player.getNumber(), newPosition);

        System.out.println(player.getNumber() + " rolled a " + actual + " and moved from " + oldPosition +" to " + newPosition);
    }
    
    private int calculatePosition(int newPosition) {
    	
    	int lastSquare = newPosition;
    	
    	
    	do {
    	
    		for (Snake snake : board.getSnakes()) {
                if (snake.getStart() == newPosition) {
                    newPosition = snake.getEnd(); 
                    System.out.println("A snake has swallowed you, your position: " + newPosition);
         
                }
            }

            for (Ladder ladder : board.getLadders()) {
                if (ladder.getStart() == newPosition) {
                    newPosition = ladder.getEnd(); 
                	System.out.println("Climbing up :), your position: " + newPosition);
                }
            }
    		
    	} while(newPosition != lastSquare);
    	
    	
		return newPosition;
	}

	/*
     * Check if a custom player has reached the end of the board, thus being selected
     * as the winner
     */
    private boolean checkWinner(Player player) {
        
        int playerPosition = board.getPlayerPieces().get(player.getNumber());
        int winningPosition = board.getSize();
        return playerPosition == winningPosition; 
    }
    
    /*
     * Helps to determinate if the game should end as a player have reached the end of
     * the board
     */
    private boolean isGameCompleted() {
        
        int currentNumberOfPlayers = players.size();
        return currentNumberOfPlayers < initialPlayers;
    }
    
    /*
     * A complex mechanism to get a random number between 1 and 6
     */
    private int rollDice() {
    	return Dice.rollDice();
    }
    
    /*
     * Launch the game, also is in charge to make the players move, check if there's a winner,
     * and manage the number of players.
     */
    public void startGame() {
    	
    	boolean flag = true;
    	
        while (flag) {
            int totalDiceValue = rollDice(); 
            Player currentPlayer = players.poll();
            movePlayer(currentPlayer, totalDiceValue);
            
            if (checkWinner(currentPlayer)) {
                System.out.println(currentPlayer.getNumber() + " wins the game");
                flag =false;
                board.getPlayerPieces().remove(currentPlayer.getNumber());
            } else {
                players.add(currentPlayer);
            }
        }
    }	

}
