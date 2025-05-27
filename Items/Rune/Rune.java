package Items.Rune;

import Items.EquiSlot;
import Items.Equipable;
import Items.Item;

public class Rune extends Item implements Equipable{

    double Valor;
    String Tipo;
    String ImageSrc;

    public Rune(String[] LinhaRuna) {
        super(LinhaRuna[0]);
        this.Valor = Double.parseDouble(LinhaRuna[1]);
        this.Tipo = LinhaRuna[2];
        this.ImageSrc = LinhaRuna[3];
    }

    public double getValor() { return Valor; }
    public String getTipo() { return Tipo; }
    public String getImageSrc() { return ImageSrc; }

    @Override
    public EquiSlot getSlot() {
        return EquiSlot.RUNE;
    }
    
}
