
package Entitys;
import Items.Armor.ChestArmor;
import Items.Armor.HandArmor;
import Items.Armor.HeadArmor;
import Items.Armor.LegArmor;
import Items.Rune.Rune;

public class Personagem {

    LevelStatsTable statsTable;

    private int Level;
    
    private int Vitality;
    private int Endurence;
    private int Strength;
    private int Skill;
    private int Bloodtinge;
    private int Arcane;

    private int Health;
    private int HealthPhantom;
    private int Stamina;
    private int StaminaSecond;
    private int Discovery;
    private int Defense;
    private int SlowPoisonResist;
    private int RapidPoisonResist;
    private int FrenzyResist;
    private int BestHood;
    private int MaxVials;
    private int MaxBullets;

    private Rune Runa1;
    private Rune Runa2;
    private Rune Runa3;

    private HeadArmor Head;
    private ChestArmor Chest;
    private HandArmor Hands;
    private LegArmor Legs;

    private Origin Origin;

    public Personagem(
        Origin Origin,
        LevelStatsTable statsTable
        ){

        this.statsTable = statsTable;
        
        this.Origin = Origin; 

        this.Level = 10;
        this.Vitality = Origin.getVitality();
        this.Endurence = Origin.getEndurence();
        this.Strength = Origin.getStrength();
        this.Skill = Origin.getSkill();
        this.Bloodtinge = Origin.getBloodtinge();
        this.Arcane = Origin.getArcane();

        ComputeDerivedStats();
    }

    private void ComputeDerivedStats(){
        this.Health = (int)(this.statsTable.getHealth(this.Vitality));
    }

    public int getHealth() { return Health; }

}
