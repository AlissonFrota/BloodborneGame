public class Runa extends Item {

    double Valor;
    String Tipo;

    protected Runa(String[] LinhaRuna) {
        super(LinhaRuna[0]);
        this.Valor = Double.parseDouble(LinhaRuna[1]);
        this.Tipo = LinhaRuna[2];
    }

    public double getValor() { return Valor; }
    public String getTipo() { return Tipo; }
    
}
