package Items.Weapon;

import Items.EquiSlot;
import Items.Equipable;
import Items.Item;

public class RHandWeapon extends Weapon implements Equipable {

    private int Rally;

    public RHandWeapon(String[] LineWeapon) {
        super(LineWeapon);
        this.Rally = Integer.parseInt(LineWeapon[13]);
    }

    public int getRally() { return Rally; }

    @Override
    public EquiSlot getSlot() {
        return EquiSlot.RHAND;
    }
    
}
