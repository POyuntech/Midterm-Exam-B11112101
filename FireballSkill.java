//import java.io.Serializable; // 若 Skill 繼承 Serializable這裡最好也加上

public class FireballSkill implements Skill {
    private static final long serialVersionUID = 1L; // For Serializable
    private final String name = "火球術"; 
    private final int baseDamage = 25;
    private final double critChance = 0.2;
    private final double critMultiplier = 1.6;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAnimation(Player player) {
        return "🔥 " + player.getName() + " 施放了【" + getName() + "】！";
    }

    @Override
    public int use(Player player, Monster target, GameContext context) {
        System.out.println(getAnimation(player)); 

        int damage = baseDamage + player.getAttackPower() / 3;
        boolean isCrit = Math.random() < critChance;

        if (isCrit) {
            damage = (int) (damage * critMultiplier);
            System.out.println("✨ 致命一擊！ ✨"); 
        }

        System.out.println("對 " + target.getName() + " 造成 " + damage + " 傷害！");
        target.takeDamage(damage);
        player.addDamageDealt(damage);

        return damage;
    }
}