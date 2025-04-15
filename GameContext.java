import java.util.ArrayList;
import java.util.List;

public class GameContext {
    private Room currentRoom;
    private List<Monster> allMonsters = new ArrayList<>();
    private boolean isGameOver = false;
    private boolean playerWon = false;

    // --- Getters ---
    public Room getCurrentRoom() { return currentRoom; }
    public boolean isGameOver() { return isGameOver; }
    public boolean didPlayerWin() { return playerWon; }

    // --- Setters ---
    public void setCurrentRoom(Room currentRoom) { this.currentRoom = currentRoom; }
    public void addMonster(Monster monster) { this.allMonsters.add(monster); }
    public void setGameOver(boolean gameOver, boolean won) {
        this.isGameOver = gameOver;
        if (gameOver) {
            this.playerWon = won;
        }
    }

    // --- Logic ---
    public boolean checkWinCondition() {
        if (allMonsters.isEmpty()) return false;
        for (Monster m : allMonsters) {
            if (m.isAlive()) {
                return false;
            }
        }
        if (!isGameOver) {
             this.setGameOver(true, true); // 直接在這裡標記勝利
        }
        return true; 
    }
}