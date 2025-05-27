package Items.Weapon;

import Items.Item;

public abstract class Weapon extends Item {

    private int PhysicalATK;//1
    private int BloodATK;//2
    private int ArcaneATK;//3
    private int BoltATK;//4
    private int StrengthReq;//5
    private int SkillReq;//6
    private int BloodTingeReq;//7
    private int ArcaneReq;//8
    private double StrengthScaling;//9
    private double SkillScaling;//10
    private double BloodTingeScaling;//11
    private double ArcaneScaling;//12
    //Rally     //13
    private int Durability;//14
    private int QsBulletUse;//15
    private String ImageSrc;//16

    protected Weapon(String[] LineWeapon) {
        super(LineWeapon[0]);
        this.PhysicalATK = Integer.parseInt(LineWeapon[1]);
        this.BloodATK = Integer.parseInt(LineWeapon[2]);
        this.ArcaneATK = Integer.parseInt(LineWeapon[3]);
        this.BoltATK = Integer.parseInt(LineWeapon[4]);
        this.StrengthReq = Integer.parseInt(LineWeapon[5]);
        this.SkillReq = Integer.parseInt(LineWeapon[6]);
        this.BloodTingeReq = Integer.parseInt(LineWeapon[7]);
        this.ArcaneReq = Integer.parseInt(LineWeapon[8]);
        this.StrengthScaling = Double.parseDouble(LineWeapon[9]);
        this.SkillScaling = Double.parseDouble(LineWeapon[10]);
        this.BloodTingeScaling = Double.parseDouble(LineWeapon[11]);
        this.ArcaneScaling = Double.parseDouble(LineWeapon[12]);//pular rally

        this.Durability = Integer.parseInt(LineWeapon[14]);
        this.QsBulletUse = Integer.parseInt(LineWeapon[15]);

        this.ImageSrc = LineWeapon[16];
    }

    public int getPhysicalATK() { return PhysicalATK; }
    public int getBloodATK() { return BloodATK; }
    public int getArcaneATK() { return ArcaneATK; }
    public int getBoltATK() { return BoltATK; }
    public int getStrengthReq() { return StrengthReq; }
    public int getSkillReq() { return SkillReq; }
    public int getBloodTingeReq() { return BloodTingeReq; }
    public int getArcaneReq() { return ArcaneReq; }
    public double getStrengthScaling() { return StrengthScaling; }
    public double getSkillScaling() { return SkillScaling; }
    public double getBloodTingeScaling() { return BloodTingeScaling; }
    public double getArcaneScaling() { return ArcaneScaling; }
    public int getDurability() { return Durability; }
    public int getQsBulletUse() { return QsBulletUse; }
    public String getImageSrc() { return ImageSrc; }
    
}
