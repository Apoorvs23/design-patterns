package LLDTicTacToe.Model;

import java.util.Deque;
import java.util.LinkedList;

public class PlayerManager {
    private final Deque<Player> players;

    public PlayerManager() {
        this.players = new LinkedList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player getCurrentPlayer() {
        return players.peekFirst();
    }

    public Player nextPlayer() {
        Player currentPlayer = players.removeFirst();
        players.addLast(currentPlayer);
        return currentPlayer;
    }

    public void keepCurrentPlayer() {
        Player currentPlayer = players.removeFirst();
        players.addFirst(currentPlayer);
    }
} 