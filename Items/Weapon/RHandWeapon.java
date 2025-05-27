package Items.Weapon;

import Items.EquiSlot;
import Items.Equipable;
import Items.Item;

public class RHandWeapon extends Weapon implements Equipable {

    private int StReq;
    private int SkReq;
    private int BReq;
    private int AReq;

    private int Base;
    private int BaseAlt;
    private int Phys;
    private int Bldt;
    private int FireATK;
    private int ArcATK;
    private int BoltATK;

    private double StSc;
    private double SkScBase;
    private double SkSc;
    private double BSc;
    private double AScBase;
    private double ASc;

    private int Rally;
    private int Dura;

    protected RHandWeapon(String name) {
        super(name);
        //TODO Auto-generated constructor stub
    }

    @Override
    public EquiSlot getSlot() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSlot'");
    }
    
}
