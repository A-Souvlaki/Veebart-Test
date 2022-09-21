package model;
import java.util.*;

public class Board {
	
	private int size;
    private List<Snake> snakes; 
    private List<Ladder> ladders;
    private Map<Integer, Integer> playerPieces;
   

	public Board(int size) {
		this.size = size;
        this.snakes = new ArrayList<Snake>();
        this.ladders = new ArrayList<Ladder>();
        this.playerPieces = new HashMap<Integer, Integer>();
	}



	public int getSize() {
        return size;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public void setSnakes(List<Snake> snakes) {
        this.snakes = snakes;
    }

    public List<Ladder> getLadders() {
        return ladders;
    }

    public void setLadders(List<Ladder> ladders) {
        this.ladders = ladders;
    }

    public Map<Integer, Integer> getPlayerPieces() {
        return playerPieces;
    }

    public void setPlayerPieces(Map<Integer, Integer> playerPieces) {
        this.playerPieces = playerPieces;
    }

}
