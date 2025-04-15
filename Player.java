import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name = "無名";
    private int hp;
    private int maxHp;
    private int attackPower;
    private List<Skill> skills;
    private long totalDamageDealt;
    private int monstersKilled;
    private List<String> usedSkillNames;

    public Player(String name, int hp, int attackPower) {
        this.name = name;
        this.maxHp = hp;
        this.hp = hp;
        this.attackPower = attackPower;
        this.skills = new ArrayList<>();
        this.totalDamageDealt = 0;
        this.monstersKilled = 0;
        this.usedSkillNames = new ArrayList<>();
    }

    // --- Getters ---
    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getAttackPower() { return attackPower; }
    public boolean isAlive() { return this.hp > 0; }
    public long getTotalDamageDealt() { return totalDamageDealt; }
    public int getMonstersKilled() { return monstersKilled; }

    // --- Setters / Modifiers ---
    public void addSkill(Skill skill) { this.skills.add(skill); }

    public Optional<Skill> getSkill(String skillNameOrAlias) {
        String lowerSkillName = skillNameOrAlias.toLowerCase();
        return skills.stream()
                     .filter(skill -> skill.getName().equalsIgnoreCase(lowerSkillName) || skill.getName().equals(skillNameOrAlias))
                     .findFirst();
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    public void heal(int amount) {
        int actualHeal = Math.min(amount, this.maxHp - this.hp);
        this.hp += actualHeal;
    }

    public int attack(Monster target) {
        if (!target.isAlive()) {
             System.out.println(target.getName() + " 已經被打敗了。");
             return 0;
        }
        int damageDealt = this.attackPower + (int)(Math.random() * 5 - 2);
        if (damageDealt <= 0) damageDealt = 1;

        System.out.println("你對 " + target.getName() + " 造成了 " + damageDealt + " 傷害！");
        target.takeDamage(damageDealt);
        this.totalDamageDealt += damageDealt;
        return damageDealt;
    }

    public void incrementKills() { this.monstersKilled++; }
    public void addDamageDealt(long damage) { this.totalDamageDealt += damage; }

    public void recordSkillUsage(String skillName) {
        if (!usedSkillNames.contains(skillName)) {
             usedSkillNames.add(skillName);
        }
    }

    // --- Output Methods ---
    public String getCurrentStatus() {
        return "你的 HP：" + this.hp + " / " + this.maxHp;
    }

    // Player.java
    public void printCombatStats(Monster currentMonster) {
        System.out.println("=== 狀態更新 ===");
        System.out.println(getCurrentStatus()); 
    
        if (currentMonster != null) { // 檢查怪物物件是否存在
            System.out.println(currentMonster.getCombatStatus());
        }
        // 顯示統計數據
        System.out.println("總傷害：" + this.totalDamageDealt + "，擊殺數：" + this.monstersKilled);
        System.out.println("================");
    }
    public List<String> getAvailableSkillNames() {
        List<String> names = new ArrayList<>();
        for (Skill s : skills) {
            names.add(s.getName());
        }
        return names;
    }
}