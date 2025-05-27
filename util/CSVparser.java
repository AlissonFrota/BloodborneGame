package util;

import java.util.ArrayList;
import java.util.List;

import Entitys.Origin;
import Items.Armor.HandArmor;
import Items.Armor.ChestArmor;
import Items.Armor.HeadArmor;
import Items.Armor.LegArmor;

public class CSVparser {

    public static List<ChestArmor> LoadChestArmor(String CSVpath){
        List<String[]> dados = CSVLoader.carregar(CSVpath);

        List<ChestArmor> ChestArmors = new ArrayList<>();

        for (String[] linha : dados){
            ChestArmors.add(new ChestArmor(linha));
        }

        return ChestArmors;
    }

    public static List<HeadArmor> LoadHeadArmor(String CSVpath){
        List<String[]> dados = CSVLoader.carregar(CSVpath);

        List<HeadArmor> HeadArmors = new ArrayList<>();

        for (String[] linha : dados){
            HeadArmors.add(new HeadArmor(linha));
        }

        return HeadArmors;
    }

    public static List<LegArmor> LoadLegArmor(String CSVpath){
        List<String[]> dados = CSVLoader.carregar(CSVpath);

        List<LegArmor> LegArmors = new ArrayList<>();

        for (String[] linha : dados){
            LegArmors.add(new LegArmor(linha));
        }

        return LegArmors;
    }

    public static List<HandArmor> LoadArmArmor(String CSVpath){
        List<String[]> dados = CSVLoader.carregar(CSVpath);

        List<HandArmor> HandArmors = new ArrayList<>();

        for (String[] linha : dados){
            HandArmors.add(new HandArmor(linha));
        }

        return HandArmors;
    }

    public static List<Origin> LoadOrigins(String CSVpath){
        List<String[]> dados = CSVLoader.carregar(CSVpath);

        List<Origin> Origins = new ArrayList<>();

        for (String[] linha : dados){
            Origins.add(new Origin(linha));
        }

        return Origins;
    }

}
