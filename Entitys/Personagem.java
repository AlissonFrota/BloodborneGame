
package Entitys;
import Items.Armor.ChestArmor;
import Items.Armor.HandArmor;
import Items.Armor.HeadArmor;
import Items.Armor.LegArmor;
import Items.Rune.Rune;

public class Personagem {

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

    Personagem(
        Origin Origin
        ){
        this.Origin = Origin; 
    }

}
