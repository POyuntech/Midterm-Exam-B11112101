@FunctionalInterface
public interface Command {
    void execute(GameContext context, Player player);
}