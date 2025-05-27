package Items.Weapon;

import Items.Equipable;
import Items.Item;

public abstract class Weapon extends Item implements Equipable{

    protected Weapon(String name) {
        super(name);
        
    }
    
}
