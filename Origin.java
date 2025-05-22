public class origin {

    private String NomeOrigem;
    private int Vitality;
    private int Endurence;
    private int Strength;
    private int Skill;
    private int Bloodtinge;
    private int Arcane;

    public origin(String NomeOrigem,int vitality, int endurence, int strength, int skill, int bloodtinge, int arcane) {
        NomeOrigem = NomeOrigem;
        Vitality = vitality;
        Endurence = endurence;
        Strength = strength;
        Skill = skill;
        Bloodtinge = bloodtinge;
        Arcane = arcane;
    }

    public static origin Milquetoast() {
        return new origin("Zé Ninguém",11,10,12,10,9,8);
    }

    public static origin Lone_Survivor() {
        return new origin("Único Sobrevivente",14,11,11,10,7,7);
    }
    public static origin Troubled_Childhood() {
        return new origin("Infancia Difícil",9,14,9,13,6,9);
    }
    public static origin Violent_Past() {
        return new origin("Passado Violento",12,11,15,9,6,7);
    }
    public static origin Professional() {
        return new origin("Profissional",9,12,9,15,7,8);
    }
    public static origin Military_Veteran() {
        return new origin("Veterano Militar",10,10,14,13,7,6);
    }
    public static origin Noble_Scion() {
        return new origin("Descendencia Nobre",7,8,9,13,14,9);
    }
    public static origin Cruel_Fate() {
        return new origin("Destino Cruel",10,12,10,9,5,14);
    }
    public static origin Waste_of_Skin() {
        return new origin("Indigno de Vida",10,9,10,9,7,9);
    }

}
