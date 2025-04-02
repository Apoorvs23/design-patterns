package LLDTicTacToe.Model;

public interface GameInterface {
    void initializeGame();
    String startGame();
    boolean isGameOver();
    Player getCurrentPlayer();
} 