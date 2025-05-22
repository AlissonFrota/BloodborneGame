public class Armadura extends Item{

    private int PhDR;
    private int BuDR;
    private int PiDR;
    private int BdDR;
    private int ADR;
    private int FDR;
    private int BoDR;
    private int SPRES;
    private int FPRES;
    private int FRRES;
    private int Beast;

    private int TypeOfArmor; // 1 -> Cabeça !2 -> Costas !3 -> Luvas !4 -> Calças

    // Formato de LinhaArmadura -> Hunter Hat 50 50 40 50 20 50 30 5 11 14 16 1 1
    protected Armadura(String[] linhaArmadura) {
        super(linhaArmadura[0]);
        this.PhDR = Integer.parseInt(linhaArmadura[1]); //Adicionar try catch
        this.BuDR = Integer.parseInt(linhaArmadura[2]);
        this.PiDR = Integer.parseInt(linhaArmadura[3]);
        this.BdDR = Integer.parseInt(linhaArmadura[4]);
        this.ADR = Integer.parseInt(linhaArmadura[5]);
        this.FDR = Integer.parseInt(linhaArmadura[6]);
        this.BoDR = Integer.parseInt(linhaArmadura[7]);
        this.SPRES = Integer.parseInt(linhaArmadura[8]);
        this.FPRES = Integer.parseInt(linhaArmadura[9]);
        this.FRRES = Integer.parseInt(linhaArmadura[10]);
        this.Beast = Integer.parseInt(linhaArmadura[11]); // Skippar penultima linha, dado não rotulado
        this.TypeOfArmor = Integer.parseInt(linhaArmadura[13]);
    }
    
    public int getPhDR() { return PhDR; }
    public int getBuDR() { return BuDR; }
    public int getPiDR() { return PiDR; }
    public int getBdDR() { return BdDR; }
    public int getADR() { return ADR; }
    public int getFDR() { return FDR; }
    public int getBoDR() { return BoDR; }
    public int getSPRES() { return SPRES; }
    public int getFPRES() { return FPRES; }
    public int getFRRES() { return FRRES; }
    public int getBeast() { return Beast; }
    public int getTypeOfArmor() { return TypeOfArmor; }

}
