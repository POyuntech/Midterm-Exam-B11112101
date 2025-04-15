class QuitCommand implements Command {
    @Override
    public void execute(GameContext context, Player player) {
        context.setGameOver(true, false); 
    }
}