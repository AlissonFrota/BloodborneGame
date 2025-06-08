
package Entitys;
import Items.Armor.ChestArmor;
import Items.Armor.HandArmor;
import Items.Armor.HeadArmor;
import Items.Armor.LegArmor;
import Items.Rune.Rune;
import Items.Weapon.LHandWeapon;
import Items.Weapon.RHandWeapon;
import repository.Repository;

public class Personagem implements Damage {

    LevelStatsTable statsTable;

    private int Level;
    
    private int Vitality;
    private int Endurence;
    private int Strength;
    private int Skill;
    private int Bloodtinge;
    private int Arcane;

    private int BloodEchoes;

    private int Insight;
    private int Health;
    private int Stamina;
    private int Discovery;
    private int Defense;
    private int SlowPoisonResist;
    private int RapidPoisonResist;
    private int FrenzyResist;
    private int BestHood;
    private int MaxVials;
    private int MaxBullets;

    private int PhysicalDmgReduction;
    private int BluntDmgReduction;
    private int ThrustDmgReduction;
    private int BloodDmgReduction;
    private int ArcaneDmgReduction;
    private int FireDmgReduction;
    private int BoltDmgReduction;

    private Rune Runa1;
    private Rune Runa2;
    private Rune Runa3;

    private HeadArmor Head;
    private ChestArmor Chest;
    private HandArmor Hands;
    private LegArmor Legs;

    private Origin Origin;

    private RHandWeapon RHand;
    private LHandWeapon LHand;

    private int hp;

    public Personagem(
        Origin Origin,
        Repository repo
        ){

        this.statsTable = new LevelStatsTable(repo.getLevelStatsTable());
        
        this.Origin = Origin; 

        this.BloodEchoes = 300;

        this.Level = 10;
        this.Insight = 0;
        this.Vitality = Origin.getVitality();
        this.Endurence = Origin.getEndurence();
        this.Strength = Origin.getStrength();
        this.Skill = Origin.getSkill();
        this.Bloodtinge = Origin.getBloodtinge();
        this.Arcane = Origin.getArcane();

        this.Head = repo.getHeadArmor("Old Hunter Cap");
        this.Chest = repo.getChestArmor("Ashen Hunter Garb");
        this.Hands = repo.getHandArmor("Ashen Hunter Gloves");
        this.Legs = repo.getLegArmor("Ashen Hunter Trousers");

        this.RHand = repo.getRHandWeapon("Saw Cleaver");
        this.LHand = repo.getLHandWeapon("Hunter Pistol");

        Rune NoRune = repo.getRune("NoRune");

        this.Runa1 = NoRune;
        this.Runa2 = NoRune; // Somente NoRune pode ser duplicado, as outras não
        this.Runa3 = NoRune;

        ComputeDerivedStats();
    }

    @Override
    public boolean isDead() {
        return this.hp <= 0;
    }

    @Override
    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    public int computeRHandDamage() {
        double physical = this.RHand.getPhysicalATK()
                + (this.Strength * this.RHand.getStrengthScaling())
                + (this.Skill * this.RHand.getSkillScaling());

        double blood = this.RHand.getBloodATK()
                + (this.Bloodtinge * this.RHand.getBloodTingeScaling());

        double arcane = this.RHand.getArcaneATK()
                + (this.Arcane * this.RHand.getArcaneScaling());

        double bolt = this.RHand.getBoltATK()
                + (this.Arcane * this.RHand.getArcaneScaling());

        return (int) (physical + blood + arcane + bolt);
    }

    public int computeLHandDamage() {
        double physical = this.LHand.getPhysicalATK()
                + (this.Strength * this.RHand.getStrengthScaling())
                + (this.Skill * this.RHand.getSkillScaling());

        double blood = this.LHand.getBloodATK()
                + (this.Bloodtinge * this.RHand.getBloodTingeScaling());

        double arcane = this.LHand.getArcaneATK()
                + (this.Arcane * this.LHand.getArcaneScaling());

        double bolt = this.RHand.getBoltATK()
                + (this.Arcane * this.LHand.getArcaneScaling());

        return (int) (physical + blood + arcane + bolt);
    }

    @Override
    public int getHp() {
        return hp;
    }

    public void UpInsight(){
        this.Insight++;
        this.Level++;
    }
    public void UpVitality(){
        this.Vitality++;
        this.Level++;
    }
    public void UpStrength(){
        this.Strength++;
        this.Level++;
    }
    public void UpSkill(){
        this.Skill++;
        this.Level++;
    }
    public void UpEndurence(){
        this.Endurence++;
        this.Level++;
    }
    public void UpBloodtinge(){
        this.Bloodtinge++;
        this.Level++;
    }
    public void UpArcane(){
        this.Arcane++;
        this.Level++;
    }

    public void setRHand(RHandWeapon RHand) {
        this.RHand = RHand;
        ComputeDerivedStats();
    }
    public void setLHand(LHandWeapon LHand) {
        this.LHand = LHand;
        ComputeDerivedStats();
    }
    public void setRuna(Rune Runa, int RuneIndex){
        switch(RuneIndex){
            case 1:
                setRuna1(Runa);
                break;
            case 2:
                setRuna2(Runa);
                break;
            case 0:
                setRuna3(Runa);
                break;
        }
    }
    public void setRuna1(Rune rune){
        this.Runa1 = rune;
        ComputeDerivedStats();
    }
    public void setRuna2(Rune rune){
        this.Runa2 = rune;
        ComputeDerivedStats();
    }
    public void setRuna3(Rune rune){
        this.Runa3 = rune;
        ComputeDerivedStats();
    }
    public void setHeadArmor(HeadArmor Head){
        this.Head = Head;
        ComputeDerivedStats();
    }
    public void setChestArmor(ChestArmor Chest){
        this.Chest = Chest;
        ComputeDerivedStats();
    }
    public void setHandArmor(HandArmor Hands){
        this.Hands = Hands;
        ComputeDerivedStats();
    }
    public void setLegArmor(LegArmor Legs){
        this.Legs = Legs;
        ComputeDerivedStats();
    }

    private void ComputeDerivedStats(){
        //Vida
        this.Health = (int)(this.statsTable.getHealth(this.Vitality));

        this.hp = this.Health;

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

        this.SlowPoisonResist = (int)this.statsTable.getSlowPoisonResist(this.Endurence);
        this.SlowPoisonResist += this.Head.getSlowPoisonRES() + this.Chest.getSlowPoisonRES() + this.Legs.getSlowPoisonRES() + this.Hands.getSlowPoisonRES();

        if(this.Runa1.getName().contains("Clear Deep")){
            this.SlowPoisonResist += (int)Runa1.getValor();
        }
        if(this.Runa2.getName().contains("Clear Deep")){
            this.SlowPoisonResist += (int)Runa2.getValor();
        }
        if(this.Runa3.getName().contains("Clear Deep")){
            this.SlowPoisonResist += (int)Runa3.getValor();
        }
        
        //RapidPoisonResist
        
        this.RapidPoisonResist = (int)this.statsTable.getRapidPoisonResist(this.Endurence);
        this.RapidPoisonResist += this.Head.getRapidPoisonRES() + this.Chest.getRapidPoisonRES() + this.Legs.getRapidPoisonRES() + this.Hands.getRapidPoisonRES();

        if(this.Runa1.getName().contains("Stunning Deep Sea")){
            this.RapidPoisonResist += (int)Runa1.getValor();
        }
        if(this.Runa2.getName().contains("Stunning Deep Sea")){
            this.RapidPoisonResist += (int)Runa2.getValor();
        }
        if(this.Runa3.getName().contains("Stunning Deep Sea")){
            this.RapidPoisonResist += (int)Runa3.getValor();
        }

        //FrenzyResist

        this.FrenzyResist = (int)this.statsTable.getFrenzyResist(this.Insight);
        this.FrenzyResist += this.Head.getFrenzyRES() + this.Chest.getFrenzyRES() + this.Legs.getFrenzyRES() + this.Hands.getFrenzyRES();

        if(this.Runa1.getName().contains("Sea.")){
            this.FrenzyResist += (int)Runa1.getValor();
        }
        if(this.Runa2.getName().contains("Sea.")){
            this.FrenzyResist += (int)Runa2.getValor();
        }
        if(this.Runa3.getName().contains("Sea.")){
            this.FrenzyResist += (int)Runa3.getValor();
        }

        // Great Deep Sea

        if(this.Runa1.getName().contains("Great Deep Sea")){
            int valor = (int)Runa1.getValor();
            this.SlowPoisonResist += valor;
            this.RapidPoisonResist += valor;
            this.FrenzyResist += valor;
        }
        if(this.Runa2.getName().contains("Great Deep Sea")){
            int valor = (int)Runa2.getValor();
            this.SlowPoisonResist += valor;
            this.RapidPoisonResist += valor;
            this.FrenzyResist += valor;
        }
        if(this.Runa3.getName().contains("Great Deep Sea")){
            int valor = (int)Runa3.getValor();
            this.SlowPoisonResist += valor;
            this.RapidPoisonResist += valor;
            this.FrenzyResist += valor;
        }

        //BeatHood

        this.BestHood = (int)this.statsTable.getBeastHood(this.Insight);
        this.BestHood += this.Head.getBeastHood() + this.Chest.getBeastHood() + this.Legs.getBeastHood() + this.Hands.getBeastHood();

        if(this.Runa1.getName().contains("Beast")){
            this.BestHood += (int)Runa1.getValor();
        }
        if(this.Runa2.getName().contains("Beast")){
            this.BestHood += (int)Runa2.getValor();
        }
        if(this.Runa3.getName().contains("Beast")){
            this.BestHood += (int)Runa3.getValor();
        }

        //Physical Damage Reduction

        this.PhysicalDmgReduction = this.Head.getPhysicalDefence() + this.Chest.getPhysicalDefence() + this.Legs.getPhysicalDefence() + this.Hands.getPhysicalDefence();

        //Blunt Damage Reduction

        this.BluntDmgReduction = this.Head.getVsBluntDefence() + this.Chest.getVsBluntDefence() + this.Legs.getVsBluntDefence() + this.Hands.getVsBluntDefence();
        
        //Thurst Damege Reduction

        this.ThrustDmgReduction = this.Head.getVsThrustDefence() + this.Chest.getVsThrustDefence() + this.Legs.getVsThrustDefence() + this.Hands.getVsThrustDefence();

        //Bload damage Reduction

        this.BloodDmgReduction = this.Head.getBloodDefence() + this.Chest.getBloodDefence() + this.Legs.getBloodDefence() + this.Hands.getBloodDefence();


        //Physical Blunt Thurst Damage Reduction

        if(this.Runa1.getName().contains("Lake.")){
            this.PhysicalDmgReduction = (int)(this.PhysicalDmgReduction + Runa1.getValor());
            this.BluntDmgReduction = (int)(this.BluntDmgReduction + Runa1.getValor());
            this.ThrustDmgReduction = (int)(this.ThrustDmgReduction + Runa1.getValor());
            this.BloodDmgReduction = (int)(this.BloodDmgReduction + Runa1.getValor());
        }
        if(this.Runa2.getName().contains("Lake.")){
            this.PhysicalDmgReduction = (int)(this.PhysicalDmgReduction + Runa2.getValor());
            this.BluntDmgReduction = (int)(this.BluntDmgReduction + Runa2.getValor());
            this.ThrustDmgReduction = (int)(this.ThrustDmgReduction + Runa2.getValor());
            this.BloodDmgReduction = (int)(this.BloodDmgReduction + Runa2.getValor());
        }
        if(this.Runa3.getName().contains("Lake.")){
            this.PhysicalDmgReduction = (int)(this.PhysicalDmgReduction + Runa3.getValor());
            this.BluntDmgReduction = (int)(this.BluntDmgReduction + Runa3.getValor());
            this.ThrustDmgReduction = (int)(this.ThrustDmgReduction + Runa3.getValor());
            this.BloodDmgReduction = (int)(this.BloodDmgReduction + Runa3.getValor());
        }

        //Arcane Dmg Reduction

        this.ArcaneDmgReduction = this.Head.getArcaneDefence() + this.Chest.getArcaneDefence() + this.Legs.getArcaneDefence() + this.Hands.getArcaneDefence();

        if(this.Runa1.getName().contains("Arcane Lake")){
            this.ArcaneDmgReduction = (int)(this.ArcaneDmgReduction + Runa1.getValor());
        }
        if(this.Runa2.getName().contains("Arcane Lake")){
            this.ArcaneDmgReduction = (int)(this.ArcaneDmgReduction + Runa2.getValor());
        }
        if(this.Runa3.getName().contains("Arcane Lake")){
            this.ArcaneDmgReduction = (int)(this.ArcaneDmgReduction + Runa3.getValor());
        }

        //Fire Dmg Reduction

        this.FireDmgReduction = this.Head.getFireDefence() + this.Chest.getFireDefence() + this.Legs.getFireDefence() + this.Hands.getFireDefence();

        if(this.Runa1.getName().contains("Fading Lake")){
            this.FireDmgReduction = (int)(this.FireDmgReduction + Runa1.getValor());
        }
        if(this.Runa2.getName().contains("Fading Lake")){
            this.FireDmgReduction = (int)(this.FireDmgReduction + Runa2.getValor());
        }
        if(this.Runa3.getName().contains("Fading Lake")){
            this.FireDmgReduction = (int)(this.FireDmgReduction + Runa3.getValor());
        }

        //Bolt Dmg Reduction

        this.BoltDmgReduction = this.Head.getBoltDefence() + this.Chest.getBoltDefence() + this.Legs.getBoltDefence() + this.Hands.getBoltDefence();

        if(this.Runa1.getName().contains("Dissipating Lake")){
            this.BoltDmgReduction = (int)(this.BoltDmgReduction + Runa1.getValor());
        }
        if(this.Runa2.getName().contains("Dissipating Lake")){
            this.BoltDmgReduction = (int)(this.BoltDmgReduction + Runa2.getValor());
        }
        if(this.Runa3.getName().contains("Dissipating Lake")){
            this.BoltDmgReduction = (int)(this.BoltDmgReduction + Runa3.getValor());
        }

        //Great Lake 

        if(this.Runa1.getName().contains("Great Lake")){
            this.PhysicalDmgReduction = (int)(this.PhysicalDmgReduction + Runa1.getValor());
            this.BluntDmgReduction = (int)(this.BluntDmgReduction + Runa1.getValor());
            this.ThrustDmgReduction = (int)(this.ThrustDmgReduction + Runa1.getValor());
            this.BloodDmgReduction = (int)(this.BloodDmgReduction + Runa1.getValor());
            this.ArcaneDmgReduction = (int)(this.ArcaneDmgReduction + Runa1.getValor());
            this.FireDmgReduction = (int)(this.FireDmgReduction + Runa1.getValor());
            this.BoltDmgReduction = (int)(this.BoltDmgReduction + Runa1.getValor());
        }
        if(this.Runa2.getName().contains("Great Lake")){
            this.PhysicalDmgReduction = (int)(this.PhysicalDmgReduction + Runa2.getValor());
            this.BluntDmgReduction = (int)(this.BluntDmgReduction + Runa2.getValor());
            this.ThrustDmgReduction = (int)(this.ThrustDmgReduction + Runa2.getValor());
            this.BloodDmgReduction = (int)(this.BloodDmgReduction + Runa2.getValor());
            this.ArcaneDmgReduction = (int)(this.ArcaneDmgReduction + Runa2.getValor());
            this.FireDmgReduction = (int)(this.FireDmgReduction + Runa2.getValor());
            this.BoltDmgReduction = (int)(this.BoltDmgReduction + Runa2.getValor());
        }
        if(this.Runa3.getName().contains("Great Lake")){
            this.PhysicalDmgReduction = (int)(this.PhysicalDmgReduction + Runa3.getValor());
            this.BluntDmgReduction = (int)(this.BluntDmgReduction + Runa3.getValor());
            this.ThrustDmgReduction = (int)(this.ThrustDmgReduction + Runa3.getValor());
            this.BloodDmgReduction = (int)(this.BloodDmgReduction + Runa3.getValor());
            this.ArcaneDmgReduction = (int)(this.ArcaneDmgReduction + Runa3.getValor());
            this.FireDmgReduction = (int)(this.FireDmgReduction + Runa3.getValor());
            this.BoltDmgReduction = (int)(this.BoltDmgReduction + Runa3.getValor());
        }
    }

    public int getLevel() { return Level; }
    public int getHealth() { return Health; }
    public int getDiscovery() { return Discovery; }
    public int getDefense() { return Defense; }
    public int getStamina() { return Stamina; }
    public int getSlowPoisonResist() { return SlowPoisonResist; }
    public int getRapidPoisonResist() { return RapidPoisonResist; }
    public int getFrenzyResist() { return FrenzyResist; }
    public int getMaxBullets() { return MaxBullets; }
    public int getMaxVials() { return MaxVials; }
    public int getBestHood() { return BestHood; }
    public int getSkill() { return Skill; }
    public int getStrength() { return Strength; }
    public int getBloodtinge() { return Bloodtinge; }
    public int getArcaneDmgReduction() { return ArcaneDmgReduction; }
    public int getArcane() { return Arcane; }
    public int getBloodDmgReduction() { return BloodDmgReduction; }
    public int getBluntDmgReduction() { return BluntDmgReduction; }
    public int getBoltDmgReduction() { return BoltDmgReduction; }
    public int getFireDmgReduction() { return FireDmgReduction; }
    public int getPhysicalDmgReduction() { return PhysicalDmgReduction; }
    public int getEndurence() { return Endurence; }
    public int getInsight() { return Insight; }
    public int getThrustDmgReduction() { return ThrustDmgReduction; }
    public int getVitality() { return Vitality; }
    public int getBloodEchoes() { return BloodEchoes; }
    public Origin getOrigin() { return Origin; }
    public RHandWeapon getRHand() { return RHand; }
    public LHandWeapon getLHand() { return LHand; }
    public HeadArmor getHead() { return Head; }
    public ChestArmor getChest() { return Chest; }
    public HandArmor getHands() { return Hands; }
    public LegArmor getLegs() { return Legs; }
    public Rune getRuna1() { return Runa1; }
    public Rune getRuna2() { return Runa2; }
    public Rune getRuna3() { return Runa3; }


}
