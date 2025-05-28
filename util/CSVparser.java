package util;

import java.util.ArrayList;
import java.util.List;

import Entitys.Origin;
import Items.Armor.HandArmor;
import Items.Armor.ChestArmor;
import Items.Armor.HeadArmor;
import Items.Armor.LegArmor;
import Items.Rune.Rune;
import Items.Weapon.LHandWeapon;
import Items.Weapon.RHandWeapon;

public class CSVparser {

    public static List<ChestArmor> LoadChestArmor(String CSVpath){
        List<String[]> dados = CSVLoader.Load(CSVpath);

        List<ChestArmor> ChestArmors = new ArrayList<>();

        for (String[] linha : dados){
            ChestArmors.add(new ChestArmor(linha));
        }

        return ChestArmors;
    }

    public static List<HeadArmor> LoadHeadArmor(String CSVpath){
        List<String[]> dados = CSVLoader.Load(CSVpath);

        List<HeadArmor> HeadArmors = new ArrayList<>();

        for (String[] linha : dados){
            HeadArmors.add(new HeadArmor(linha));
        }

        return HeadArmors;
    }

    public static List<LegArmor> LoadLegArmor(String CSVpath){
        List<String[]> dados = CSVLoader.Load(CSVpath);

        List<LegArmor> LegArmors = new ArrayList<>();

        for (String[] linha : dados){
            LegArmors.add(new LegArmor(linha));
        }

        return LegArmors;
    }

    public static List<HandArmor> LoadArmArmor(String CSVpath){
        List<String[]> dados = CSVLoader.Load(CSVpath);

        List<HandArmor> HandArmors = new ArrayList<>();

        for (String[] linha : dados){
            HandArmors.add(new HandArmor(linha));
        }

        return HandArmors;
    }

    public static List<Origin> LoadOrigins(String CSVpath){
        List<String[]> dados = CSVLoader.Load(CSVpath);

        List<Origin> Origins = new ArrayList<>();

        for (String[] linha : dados){
            Origins.add(new Origin(linha));
        }

        return Origins;
    }

    public static List<Rune> LoadRunes(String CSVpath){
        List<String[]> dados = CSVLoader.Load(CSVpath);

        List<Rune> Runes = new ArrayList<>();

        for (String[] linha : dados){
            Runes.add(new Rune(linha));
        }

        return Runes;
    }

    public static List<RHandWeapon> LoadRHandWeapon(String CSVpath){
        List<String[]> dados = CSVLoader.Load(CSVpath);

        List<RHandWeapon> RHandWeapons = new ArrayList<>();

        for (String[] linha : dados){
            RHandWeapons.add(new RHandWeapon(linha));
        }

        return RHandWeapons;
    }

    public static List<LHandWeapon> LoadLHandWeapon(String CSVpath){
        List<String[]> dados = CSVLoader.Load(CSVpath);

        List<LHandWeapon> LHandWeapons = new ArrayList<>();

        for (String[] linha : dados){
            LHandWeapons.add(new LHandWeapon(linha));
        }

        return LHandWeapons;
    }

    public static double[][] LoadStatsTable(String CSVpath) {
        List<String[]> dados = CSVLoader.Load(CSVpath);

        int numRows = 98;                   
        int numCols = dados.get(0).length;          

        double[][] statsMatrix = new double[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            String[] linha = dados.get(i);

            for (int j = 0; j < numCols; j++) {
                statsMatrix[i][j] = Double.parseDouble(linha[j]);
            }
        }

        return statsMatrix;
    }


    

}
