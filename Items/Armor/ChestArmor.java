package Items.Armor;

import Items.EquiSlot;
import Items.Equipable;

public class ChestArmor extends Armadura implements Equipable {

    public ChestArmor(String[] linhaArmadura) {
        super(linhaArmadura);
        //TODO Auto-generated constructor stub
    }

    @Override
    public EquiSlot getSlot() {
        return EquiSlot.CHEST;
    }
    
}
