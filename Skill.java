import java.io.Serializable;

public interface Skill extends Serializable {
    String getName();
    int use(Player player, Monster target, GameContext context);
    String getAnimation(Player player); // 將 player 傳入以顯示名稱
}