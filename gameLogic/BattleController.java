package gameLogic;

import Entitys.Personagem;
import Entitys.Monster;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import ui.BattlePane;

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
    private BattlePane battlePane;

    public void setBattlePane(BattlePane battlePane) {
        this.battlePane = battlePane;
    }

    private Runnable onLevelUp;
    private Runnable onGameReset;
    private Runnable onGameComplete;
    private Runnable onLoadingStart;

    public BattleController(Personagem player,
                            Runnable onLevelUp,
                            Runnable onGameReset,
                            Runnable onGameComplete,
                            Runnable onLoadingStart) {
        this.player = player;
        this.onLevelUp = onLevelUp;
        this.onGameReset = onGameReset;
        this.onGameComplete = onGameComplete;
        this.onLoadingStart = onLoadingStart;
    }

    public void startPhase() {
        int baseHp = 500 + (int)(100 * Math.pow(1.4, phase));
        int baseAtk = 30 + (int)(10 * Math.pow(1.2, phase));
        monster = new Monster("Phase " + phase + " Boss", baseHp, baseAtk);

        battlePane.logToTerminal("=== PHASE " + phase + " ===");
        battlePane.logToTerminal("Monster: " + monster.getName());
        battlePane.logToTerminal("HP: " + monster.getHp() + " | ATK: " + monster.getAttack());
    }

    public void attack() {
        int baseDamage = player.computeRHandDamage();
        int damage = baseDamage + rng.nextInt(5);

        if (counterAttackReady) {
            damage = (int)(damage * 1.5);
            battlePane.logToTerminal("Counter-attack bonus!");
            counterAttackReady = false;
        }

        monster.takeDamage(damage);
        battlePane.logToTerminal("Player attacks for " + damage + " damage!");
        battlePane.logToTerminal("Monster HP: " + monster.getHp());
        postPlayerAction();
    }

    public void block() {
        playerBlocked = true;
        battlePane.logToTerminal("Player prepares to block next attack");
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
                    battlePane.logToTerminal("Critical hit!");
                }

                monster.takeDamage(totalDamage);
                battlePane.logToTerminal("Player shoots and hits for " + totalDamage + " damage!" + " Monster HP now: " + monster.getHp());
            } else {
                battlePane.logToTerminal("Player shoots but misses!");
            }

            ammo--;
            battlePane.logToTerminal("Ammo left: " + ammo + "/20");

            postPlayerAction();
        } else {
            battlePane.logToTerminal("Click! Without ammo!");
            postPlayerAction();
        }
    }

    public void dodge() {
        int dodgeChance = 30 + (player.getSkill() / 10);
        dodgeChance = Math.min(dodgeChance, 80);
        playerDodged = (rng.nextInt(100) < dodgeChance);
        if (playerDodged) {
            counterAttackReady = true; // Se esquivou, prepara bônus de ataque
            battlePane.logToTerminal("Player Dodges");
        } else {
            battlePane.logToTerminal("Player attempts to dodge but fails.");
        }
        postPlayerAction();
    }
    private void postPlayerAction() {
        if (monster.isDead()) {
            battlePane.logToTerminal("Monster defeated!");
            battlePane.logToTerminal("Rewards!!!");
            battlePane.logToTerminal("10+ Blood Echoes!");
            player.setBloodEchoes(10);
            battlePane.logToTerminal("New Phase about to Start!");
            phase++;

            if (phase > MAX_PHASES) {
                battlePane.logToTerminal("All phases cleared! Victory!");
                onGameComplete.run();
            } else {
                onLoadingStart.run();
                startPhase();
            }
        } else {
            monsterTurn();
        }
    }


    public int getCurrentPhase() {
        return phase;
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
        battlePane.logToTerminal("Monster strikes for " + damage + " Damage "+ " Player HP now: " + player.getHp());
        if (player.isDead()) {
            battlePane.logToTerminal("Player Lost");
            onGameReset.run();
        }
    }
}