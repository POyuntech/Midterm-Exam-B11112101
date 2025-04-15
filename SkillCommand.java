import java.util.Optional; // 需要 import

class SkillCommand implements Command {
    private String skillNameOrAlias;

    public SkillCommand(String skillNameOrAlias) {
        this.skillNameOrAlias = skillNameOrAlias;
    }

    @Override
    public void execute(GameContext context, Player player) {
        Room currentRoom = context.getCurrentRoom();
        Monster monster = currentRoom.getMonster();

        if (monster == null || !monster.isAlive()) {
            System.out.println("這裡沒有可以施放技能的目標。");
            return;
        }

        Optional<Skill> skillOpt = player.getSkill(skillNameOrAlias);

        if (skillOpt.isPresent()) {
            Skill skill = skillOpt.get();
            skill.use(player, monster, context);
            player.recordSkillUsage(skill.getName());

            if (!monster.isAlive()) {
                System.out.println("💀 " + monster.getName() + " 被你用【" + skill.getName() + "】燒成灰燼了！");
                player.incrementKills();
                player.printCombatStats(monster); // 顯示包含死亡狀態的統計

                if (context.checkWinCondition()) {
                     context.setGameOver(true, true);
                }
            } else {
                System.out.println(monster.getName() + " 還活著！");
                monster.attack(player);
                 if (!player.isAlive()) {
                     context.setGameOver(true, false);
                 } else {
                     player.printCombatStats(monster);
                 }
            }
        } else {
            System.out.println("你不會使用這個技能：" + skillNameOrAlias);
            System.out.println("可用技能：" + String.join("、", player.getAvailableSkillNames()));
        }
    }
}