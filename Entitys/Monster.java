package Entitys;

public class Monster implements Damage {
    private String name;
    private int hp;
    private int attack;

    public Monster(String name, int hp, int attack) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    @Override
    public void takeDamage(int damage) {

        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    @Override
    public boolean isDead() {
        return this.hp <= 0;
    }
}
