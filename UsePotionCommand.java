class UsePotionCommand implements Command {
    private final int HEAL_AMOUNT = 30;

    @Override
    public void execute(GameContext context, Player player) {
        Room currentRoom = context.getCurrentRoom();
        if (currentRoom.hasPotion()) {
            int currentHp = player.getHp();
            player.heal(HEAL_AMOUNT);
            int healedAmount = player.getHp() - currentHp;

            System.out.println("🧪 你喝下治療藥水，回復 " + healedAmount + " HP！");
            currentRoom.removePotion();
            System.out.println(player.getCurrentStatus());
        } else {
            System.out.println("這裡沒有治療藥水。");
        }
    }
}