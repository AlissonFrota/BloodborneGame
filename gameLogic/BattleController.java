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
    private boolean playerBlocked = false;
    private boolean playerDodged = false;
    private boolean playerShoot = false;
    private boolean criticalchance;
    private int ammo = 20;
    private boolean counterAttackReady = false;


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
        int baseHp = 700 + phase * 100;
        int baseAtk = 50 + phase * 5;
        monster = new Monster("Phase " + phase + " Boss", baseHp, baseAtk);
        System.out.println("--- Phase " + phase + " Monster HP: " + monster.getHp() + ", ATK: " + monster.getAttack() + " ---");
    }

    public void attack() {
        int baseDamage = player.computeRHandDamage();
        int damage = baseDamage + rng.nextInt(5);

        if (counterAttackReady) {
            damage = (int)(damage * 1.5);
            System.out.println("Counter-attack bonus!");
            counterAttackReady = false;
        }

        monster.takeDamage(damage);
        System.out.println("Player attacks for " + damage + " damage!");
        System.out.println("Monster HP: " + monster.getHp());
        postPlayerAction();
    }

    public void block() {
        playerBlocked = true;
        System.out.println("Player assumes defensive stance");
        postPlayerAction();
    }

    public void shoot() {
        if(ammo > 0) {

            int hitChance = 60 + player.getSkill();
            hitChance = Math.min(hitChance, 95);
            boolean Hit = (rng.nextInt(100) < hitChance);

            if(Hit) {
                int baseDamage = player.computeLHandDamage();
                int randomBonus = rng.nextInt(8);
                int totalDamage = baseDamage + randomBonus;

                boolean isCritical = (rng.nextInt(100) < player.getSkill());
                if(isCritical) {
                    totalDamage *= 2;
                    System.out.println("Critical hit!");
                }

                monster.takeDamage(totalDamage);
                System.out.println("Player shoots and hits for " + totalDamage + " damage!" + " Monster HP now: " + monster.getHp());
            } else {
                System.out.println("Player shoots but misses!");
            }

        ammo--;
        System.out.println("Ammo left: " + ammo + "/20");

        postPlayerAction();
    } else {
        System.out.println("Click! Without ammo!");
        postPlayerAction();
    }
}

    public void dodge() {
        int dodgeChance = 30 + (player.getSkill() / 10);
        dodgeChance = Math.min(dodgeChance, 80);
        playerDodged = (rng.nextInt(100) < dodgeChance);
        if (playerDodged) {
            counterAttackReady = true; // Se esquivou, prepara bônus de ataque
            System.out.println("Player Dodges");
        } else {
            System.out.println("Player attempts to dodge but fails.");
        }
            postPlayerAction();
    }
    private void postPlayerAction() {
        if (monster.isDead()) {
            System.out.println("Monster defeated!");
            System.out.println("New Phase about to Start!");
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
        if(playerBlocked) {
            damage /= 2; // Reduz dano pela metade
            playerBlocked = false;
        }
        if(playerDodged){
            damage = 0;
            playerDodged = false;
        }
        player.takeDamage(damage);
        System.out.println("Monster strikes for " + damage + " Damage "+ " Player HP now: " + player.getHp());
        if (player.isDead()) {
            System.out.println("Player Lost");
            onGameReset.run();
        }
    }
}
