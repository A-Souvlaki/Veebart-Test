package app;
import java.util.*;

import model.Board;
import model.Dice;
import model.Ladder;
import model.Player;
import model.Snake;

public class BoardApp {
	
	private Board board;
    private int initialNumberOfPlayers;
    private Queue<Player> players; 
    private boolean isGameCompleted;


    private static final int DEFAULT_BOARD_SIZE = 25 ;
    private static final int DEFAULT_NO_OF_DICES = 1;

    public BoardApp(int boardSize) {
        this.board = new Board(boardSize);
        this.players = new LinkedList<Player>();
    }

    public BoardApp() {
        this(BoardApp.DEFAULT_BOARD_SIZE);
    }

    /**
     * This method is used to set the players behavior
     */

    public void setPlayers(List<Player> players) {
        this.players = new LinkedList<Player>();
        this.initialNumberOfPlayers = players.size();
        Map<String, Integer> playerPieces = new HashMap<String, Integer>();
        for (Player player : players) {
            this.players.add(player);
            playerPieces.put(player.getId(), 0); //Each player has a piece which is initially kept outside the board (i.e., at position 0).
        }
        board.setPlayerPieces(playerPieces); //  Add pieces to board
    }
    
    /*
     * Methods used to add a customized group of snakes and ladders to the game
     */

    public void setSnakes(List<Snake> snakes) {
        board.setSnakes(snakes); // Add snakes to board
    }

    public void setLadders(List<Ladder> ladders) {
        board.setLadders(ladders); // Add ladders to board
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

    private int calculateNewPosition(int newPosition) {
        int previousPosition;

        do {
            previousPosition = newPosition;
            for (Snake snake : board.getSnakes()) {
                if (snake.getStart() == newPosition) {
                    newPosition = snake.getEnd(); // Whenever a piece ends up at a position with the head of the snake, the piece should go down to the position of the tail of that snake.
                }
            }

            for (Ladder ladder : board.getLadders()) {
                if (ladder.getStart() == newPosition) {
                    newPosition = ladder.getEnd(); // Whenever a piece ends up at a position with the start of the ladder, the piece should go up to the position of the end of that ladder.
                }
            }
        } while (newPosition != previousPosition); // There could be another snake/ladder at the tail of the snake or the end position of the ladder and the piece should go up/down accordingly.
        return newPosition;
    }

    private void movePlayer(Player player, int positions) {
        int oldPosition = board.getPlayerPieces().get(player.getId());
        int newPosition = oldPosition + positions; // Based on the dice value, the player moves their piece forward that number of cells.

        int boardSize = board.getSize();

     
        if (newPosition > boardSize) {
            newPosition = 25; // After the dice roll, if a piece is supposed to move outside position 100, it does not move.
        } else {
            newPosition = calculateNewPosition(newPosition);
        }

        board.getPlayerPieces().put(player.getId(), newPosition);

        System.out.println(player.getName() + " rolled a " + positions + " and moved from " + oldPosition +" to " + newPosition);
    }

    private int getTotalValueAfterDiceRolls() {
        
        return Dice.rollDice();
    }
    

	/*
     * Check if a custom player has reached the end of the board, thus being selected
     * as the winner
     */
    private boolean hasPlayerWon(Player player) {
      
        int playerPosition = board.getPlayerPieces().get(player.getId());
        int winningPosition = board.getSize();
        return playerPosition == winningPosition; // A player wins if it exactly reaches the position 100 and the game ends there.
    }
    
    
    /*
     * Helps to determinate if the game should end as a player have reached the end of
     * the board
     */
    private boolean isGameCompleted() {
       
        int currentNumberOfPlayers = players.size();
        return currentNumberOfPlayers < initialNumberOfPlayers;
    }
    
    /*
     * Launch the game, also is in charge to make the players move, check if there's a winner,
     * and manage the number of players.
     */
    public void startGame() {
        while (!isGameCompleted()) {
            int totalDiceValue = getTotalValueAfterDiceRolls(); // Each player rolls the dice when their turn comes.
            Player currentPlayer = players.poll();
            movePlayer(currentPlayer, totalDiceValue);
            if (hasPlayerWon(currentPlayer)) {
                System.out.println(currentPlayer.getName() + " wins the game");
                board.getPlayerPieces().remove(currentPlayer.getId());
            } else {
                players.add(currentPlayer);
            }
        }
    }

}
