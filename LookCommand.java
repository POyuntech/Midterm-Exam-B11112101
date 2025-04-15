class LookCommand implements Command {
    @Override
    public void execute(GameContext context, Player player) {
        System.out.print(context.getCurrentRoom().getLookInfo());
        System.out.println(player.getCurrentStatus());
    }
}