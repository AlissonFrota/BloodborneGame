package Items.Armor;

import Items.EquiSlot;
import Items.Equipable;

public class LegArmor extends Armadura implements Equipable{

    public LegArmor(String[] linhaArmadura) {
        super(linhaArmadura);
        //TODO Auto-generated constructor stub
    }

    @Override
    public EquiSlot getSlot() {
        return EquiSlot.LEGS;
    }
    
}