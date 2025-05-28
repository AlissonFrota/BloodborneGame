package Entitys;
public class Origin {

    private String Name;
    private int Vitality;
    private int Endurence;
    private int Strength;
    private int Skill;
    private int Bloodtinge;
    private int Arcane;

    public Origin(String[] LinhaOrigin) {
        this.Name = LinhaOrigin[0];
        this.Vitality = Integer.parseInt(LinhaOrigin[1]); //Adicionar try catch
        this.Endurence = Integer.parseInt(LinhaOrigin[2]);
        this.Strength = Integer.parseInt(LinhaOrigin[3]);
        this.Skill = Integer.parseInt(LinhaOrigin[4]);
        this.Bloodtinge = Integer.parseInt(LinhaOrigin[5]);
        this.Arcane = Integer.parseInt(LinhaOrigin[6]);
    }

    public String getName() { return Name; }
    public int getVitality() { return Vitality; }
    public int getEndurence() { return Endurence; }
    public int getStrength() { return Strength; }
    public int getSkill() { return Skill; }
    public int getBloodtinge() { return Bloodtinge; }
    public int getArcane() { return Arcane; }
}
