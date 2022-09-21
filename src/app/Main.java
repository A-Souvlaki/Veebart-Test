package app;
import java.util.*;

import model.Ladder;
import model.Player;
import model.Snake;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("How many snakes do u want?");
        int noOfSnakes = scanner.nextInt();
        List<Snake> snakes = new ArrayList<Snake>();
        for (int i = 0; i < noOfSnakes; i++) {
        	System.out.println("Put the head and end number for snake number: " + (i+1));
            snakes.add(new Snake(scanner.nextInt(), scanner.nextInt()));
        }
        
        System.out.println("How many ladders do u want?");
        int noOfLadders = scanner.nextInt();
        List<Ladder> ladders = new ArrayList<Ladder>();
        for (int i = 0; i < noOfLadders; i++) {
        	System.out.println("Put the head and end number for this ladder number: " + (i+1));
            ladders.add(new Ladder(scanner.nextInt(), scanner.nextInt()));
        }
        
        System.out.println("How many players going to play?");
        int noOfPlayers = scanner.nextInt();
        List<Player> players = new ArrayList<Player>();
        for (int i = 0; i < noOfPlayers; i++) {
        	System.out.println("Ingrese el numero del jugador");
            players.add(new Player(scanner.nextInt()));
        }

        BoardApp bp = new BoardApp();
        bp.setPlayers(players);
        bp.setSnakes(snakes);
        bp.setLadders(ladders);
        bp.startGame();
    }
	
}
