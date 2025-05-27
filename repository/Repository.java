package repository;

import java.util.List;

import Entitys.Origin;
import Items.Armor.HandArmor;
import Items.Armor.HeadArmor;
import Items.Armor.LegArmor;
import Items.Rune.Rune;
import Items.Weapon.LHandWeapon;
import Items.Weapon.RHandWeapon;
import Items.Armor.ChestArmor;
import util.CSVparser;

public class Repository {
    //Runa
    private List<Rune> Runes;

    //Origem
    private List<Origin> Origins;

    //Armaduras
    private List<ChestArmor> ChestArmors;
    private List<HandArmor> HandArmors;
    private List<LegArmor> LegArmors;
    private List<HeadArmor> HeadArmors;

    //Armas
    private List<RHandWeapon> RHandWeapons;
    private List<LHandWeapon> LHandWeapons;

    public void LoadAll(){
        Runes = CSVparser.LoadRunes("Data\\RunesCSV.csv");

        Origins = CSVparser.LoadOrigins("Data\\OriginCSV.csv");

        ChestArmors = CSVparser.LoadChestArmor("Data\\ChestArmorCSV.csv");
        HandArmors = CSVparser.LoadArmArmor("Data\\HandArmorCSV.csv");
        LegArmors = CSVparser.LoadLegArmor("Data\\LegsArmorCSV.csv");
        HeadArmors = CSVparser.LoadHeadArmor("Data\\HeadArmorCSV.csv");

        RHandWeapons = CSVparser.LoadRHandWeapon("Data\\RHandWeaponsCSV.csv");
        LHandWeapons = CSVparser.LoadLHandWeapon("Data\\LHandWeaponsCSV.csv");
    }

    public List<Rune> getRunes() { return Runes; }

    public List<Origin> getOrigins() { return Origins; }

    public List<ChestArmor> getChestArmors() { return ChestArmors; }
    public List<HandArmor> getHandArmors() { return HandArmors; }
    public List<LegArmor> getLegArmors() { return LegArmors; }
    public List<HeadArmor> getHeadArmors() { return HeadArmors; }

    public List<RHandWeapon> getRHandWeapons() { return RHandWeapons; }
    public List<LHandWeapon> getLHandWeapons() { return LHandWeapons; }
}
