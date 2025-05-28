
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
        Rune NoRune,
        ChestArmor NoChestArmor,
        HandArmor NoHandArmor,
        HeadArmor NoHeadArmor,
        LegArmor NoLegsArmor,
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

        this.Head = NoHeadArmor;
        this.Chest = NoChestArmor;
        this.Hands = NoHandArmor;
        this.Legs = NoLegsArmor;

        this.Runa1 = NoRune;
        this.Runa2 = NoRune; // Somente NoRune pode ser duplicado, as outras não
        this.Runa3 = NoRune;

        ComputeDerivedStats();
    }

    public void setRuna1(Rune rune){
        this.Runa1 = rune;
        ComputeDerivedStats();
    }
    public void setRuna2(Rune rune){
        this.Runa2 = rune;
        ComputeDerivedStats();
    }
    public void SetRuna3(Rune rune){
        this.Runa3 = rune;
        ComputeDerivedStats();
    }

    private void ComputeDerivedStats(){
        //Vida
        this.Health = (int)(this.statsTable.getHealth(this.Vitality));

        if(this.Runa1.getName().contains("Clockwise")){
            this.Health = (int)(this.Health * Runa1.getValor());
        }
        if(this.Runa2.getName().contains("Clockwise")){
            this.Health = (int)(this.Health * Runa2.getValor());
        }
        if(this.Runa3.getName().contains("Clockwise")){
            this.Health = (int)(this.Health * Runa2.getValor());
        }

        //Stamina
        this.Stamina = (int)(this.statsTable.getStamina(this.Endurence));

        if(this.Runa1.getName().contains("Anticlockwise")){
            this.Stamina = (int)(this.Stamina * Runa1.getValor());
        }
        if(this.Runa2.getName().contains("Anticlockwise")){
            this.Stamina = (int)(this.Stamina * Runa2.getValor());
        }
        if(this.Runa3.getName().contains("Anticlockwise")){
            this.Stamina = (int)(this.Stamina * Runa3.getValor());
        }

        //Discovery

        this.Discovery = (int)(this.statsTable.getDiscovery(this.Arcane));

        if(this.Runa1.getName().contains("Eye")){
            this.Discovery += (int)(Runa1.getValor());
        }
        if(this.Runa2.getName().contains("Eye")){
            this.Discovery += (int)(Runa2.getValor());
        }
        if(this.Runa3.getName().contains("Eye")){
            this.Discovery += (int)(Runa3.getValor());
        }
        
        //Defence

        this.Defense = (int)(this.statsTable.getDefence(this.Level));

        //MaxVials

        this.MaxVials = 20;

        if(this.Runa1.getName().contains("Communion")){
            this.MaxVials += (int)Runa1.getValor();
        }
        if(this.Runa2.getName().contains("Communion")){
            this.MaxVials += (int)Runa2.getValor();
        }
        if(this.Runa3.getName().contains("Communion")){
            this.MaxVials += (int)Runa3.getValor();
        }

        //MaxBullets

        this.MaxBullets = 20;

        if(this.Runa1.getName().contains("Formless Oedon")){
            this.MaxVials += (int)Runa1.getValor();
        }
        if(this.Runa2.getName().contains("Formless Oedon")){
            this.MaxVials += (int)Runa2.getValor();
        }
        if(this.Runa3.getName().contains("Formless Oedon")){
            this.MaxVials += (int)Runa3.getValor();
        }

        //SlowPoisonResist

        
    }

    public int getHealth() { return Health; }
    public int getStamina() { return Stamina; }

}
