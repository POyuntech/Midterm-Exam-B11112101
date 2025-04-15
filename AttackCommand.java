class AttackCommand implements Command {
    @Override
    public void execute(GameContext context, Player player) {
        Room currentRoom = context.getCurrentRoom();
        Monster monster = currentRoom.getMonster();

        if (monster != null && monster.isAlive()) {
            player.attack(monster);

            if (!monster.isAlive()) {
                System.out.println("💥 " + monster.getName() + " 被你擊倒了！");
                player.incrementKills();
                player.printCombatStats(monster); 

                if (context.checkWinCondition()) {
                    context.setGameOver(true, true);
                }
            } else {
                monster.attack(player);
                if (!player.isAlive()) {
                    context.setGameOver(true, false);
                } else {
                    player.printCombatStats(monster);
                }
            }
        } else if (monster != null && !monster.isAlive()){
             System.out.println(monster.getName() + " 已經被打敗了。");
        } else {
            System.out.println("這裡沒有可以攻擊的目標。");
        }
    }
}