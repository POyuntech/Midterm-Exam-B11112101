import java.io.Serializable;

public class Monster implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int hp;
    private int attackPower;
    private int maxHp;

    public Monster(String name, int hp, int attackPower) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attackPower = attackPower;
    }
    public int getMaxHp() {
        return maxHp;
    }
    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getAttackPower() { return attackPower; }
    public boolean isAlive() { return this.hp > 0; }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    public void attack(Player player) {
        if (!isAlive()) return;
        System.out.println(this.name + " 反擊你，造成 " + this.attackPower + " 傷害！");
        player.takeDamage(this.attackPower);
    }

    @Override
    public String toString() {
        return name + "（HP: " + hp + "）";
    }

    public String getCombatStatus() {
        return name + " HP：" + hp + " / " + maxHp;
    }
    
}