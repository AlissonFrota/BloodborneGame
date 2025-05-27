package Items.Armor;

import Items.EquiSlot;
import Items.Equipable;

public class HeadArmor extends Armadura implements Equipable{

    public HeadArmor(String[] linhaArmadura) {
        super(linhaArmadura);
        //TODO Auto-generated constructor stub
    }

    @Override
    public EquiSlot getSlot() {
        return EquiSlot.HEAD;
    }
    
}
