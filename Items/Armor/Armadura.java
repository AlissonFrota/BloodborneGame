package Items.Armor;

import Items.Item;

public abstract class Armadura extends Item{ 


    private int PhysicalDefence;
    private int VsBluntDefence;
    private int VsThrustDefence;
    private int BloodDefence;
    private int ArcaneDefence;
    private int SlowPoisonRES;
    private int RapidPoisonRES;
    private int FrenzyRES;
    private int BeastHood;
    private String ImageSrc;

    public Armadura(String[] linhaArmadura) {
        super(linhaArmadura[0]);
        this.PhysicalDefence = Integer.parseInt(linhaArmadura[1]); //Adicionar try catch
        this.VsBluntDefence = Integer.parseInt(linhaArmadura[2]);
        this.VsThrustDefence = Integer.parseInt(linhaArmadura[3]);
        this.BloodDefence = Integer.parseInt(linhaArmadura[4]);
        this.ArcaneDefence = Integer.parseInt(linhaArmadura[5]);
        this.SlowPoisonRES = Integer.parseInt(linhaArmadura[6]);
        this.RapidPoisonRES = Integer.parseInt(linhaArmadura[7]);
        this.FrenzyRES = Integer.parseInt(linhaArmadura[8]);
        this.BeastHood = Integer.parseInt(linhaArmadura[9]); //Skippar penultima tipo de item
        this.ImageSrc = linhaArmadura[11];
    }
    
    public int getPhysicalDefence() { return PhysicalDefence; }
    public int getVsBluntDefence() { return VsBluntDefence; }
    public int getVsThrustDefence() { return VsThrustDefence; }
    public int getBloodDefence() { return BloodDefence; }
    public int getArcaneDefence() { return ArcaneDefence; }
    public int getSlowPoisonRES() { return SlowPoisonRES; }
    public int getRapidPoisonRES() { return RapidPoisonRES; }
    public int getFrenzyRES() { return FrenzyRES; }
    public int getBeastHood() { return BeastHood; }
    public String getImageSrc() { return ImageSrc; }
}
