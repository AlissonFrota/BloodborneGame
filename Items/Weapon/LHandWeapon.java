package Items.Weapon;

import Items.EquiSlot;
import Items.Equipable;

public class LHandWeapon extends Weapon implements Equipable {


    public LHandWeapon(String[] LineWeapon) {
        super(LineWeapon);
    }

    @Override
    public EquiSlot getSlot() {
        return EquiSlot.LHAND;
    }
    
}
