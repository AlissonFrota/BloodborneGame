package repository;

import java.util.List;

import Entitys.Origin;
import Items.Armor.HandArmor;
import Items.Armor.HeadArmor;
import Items.Armor.LegArmor;
import Items.Armor.ChestArmor;
import util.CSVparser;

public class Repository {

    //Origem
    private List<Origin> Origins;

    //Armaduras
    private List<ChestArmor> ChestArmors;
    private List<HandArmor> HandArmors;
    private List<LegArmor> LegArmors;
    private List<HeadArmor> HeadArmors;

    public void LoadAll(){
        Origins = CSVparser.LoadOrigins("Data\\OriginCSV.csv");

        ChestArmors = CSVparser.LoadChestArmor("Data\\ChestArmorCSV.csv");
        HandArmors = CSVparser.LoadArmArmor("Data\\HandArmorCSV.csv");
        LegArmors = CSVparser.LoadLegArmor("Data\\LegsArmorCSV.csv");
        HeadArmors = CSVparser.LoadHeadArmor("Data\\HeadArmorCSV.csv");
    }

    public List<Origin> getOrigins() { return Origins; }

    public List<ChestArmor> getChestArmors() { return ChestArmors; }
    public List<HandArmor> getHandArmors() { return HandArmors; }
    public List<LegArmor> getLegArmors() { return LegArmors; }
    public List<HeadArmor> getHeadArmors() { return HeadArmors; }
}
