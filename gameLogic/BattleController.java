package gameLogic;

import Entitys.Personagem;
import Entitys.Monster;
import java.util.Random;


public class BattleController {
    private Personagem player;
    private Monster monster;
    private int phase = 1;
    private final int MAX_PHASES = 5;
    private final Random rng = new Random();

    private Runnable onLevelUp;
    private Runnable onGameReset;
    private Runnable onGameComplete;

    public BattleController(Personagem player,
                            Runnable onLevelUp,
                            Runnable onGameReset,
                            Runnable onGameComplete) {
        this.player = player;
        this.onLevelUp = onLevelUp;
        this.onGameReset = onGameReset;
        this.onGameComplete = onGameComplete;
        startPhase();
    }

    private void startPhase() {
        int baseHp = 400 + phase * 100;
        int baseAtk = 10 + phase * 5;
        monster = new Monster("Phase " + phase + " Boss", baseHp, baseAtk);
        System.out.println("--- Phase " + phase + " Monster HP: " + monster.getHp() + ", ATK: " + monster.getAttack() + " ---");
    }

    public void attack() {
        int damage = player.computeRHandDamage() + rng.nextInt(5);
        monster.takeDamage(damage);
        System.out.println("Player attacks for " + damage + " Monster HP now: " + monster.getHp());
        postPlayerAction();
    }

    public void block() {
        System.out.println("Player Blocks");
        postPlayerAction();
    }

    public void shoot() {
        //int damage = player.getRangedAttack() + rng.nextInt(8);
        //monster.takeDamage(damage);
        System.out.println("Player shoots " + " Monster HP now: " + monster.getHp());
        postPlayerAction();
    }

    public void dodge() {
        System.out.println("Player Dodges");
        postPlayerAction();
    }

    private void postPlayerAction() {
        if (monster.isDead()) {
            System.out.println("Monster defeated!");
            phase++;
            if (phase > MAX_PHASES) {
                System.out.println("All phases cleared");
                onGameComplete.run();
            } else {
                System.out.println("Returning to Inventory");
                onLevelUp.run();
            }
        } else {
            monsterTurn();
        }
    }

    private void monsterTurn() {
        int damage = monster.getAttack() + rng.nextInt(5);
        player.takeDamage(damage);
        System.out.println("Monster strikes for " + damage + " Player HP now: " + player.getHp());
        if (player.isDead()) {
            System.out.println("Player Lost");
            onGameReset.run();
        }
    }

}
