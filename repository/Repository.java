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

    //Status
    private double[][] LevelStatsTable;

    public void LoadAll(){
        Runes = CSVparser.LoadRunes("Data/RunesCSV.csv");

        Origins = CSVparser.LoadOrigins("Data/OriginCSV.csv");

        ChestArmors = CSVparser.LoadChestArmor("Data/ChestArmorCSV.csv");
        HandArmors = CSVparser.LoadArmArmor("Data/HandArmorCSV.csv");
        LegArmors = CSVparser.LoadLegArmor("Data/LegsArmorCSV.csv");
        HeadArmors = CSVparser.LoadHeadArmor("Data/HeadArmorCSV.csv");

        RHandWeapons = CSVparser.LoadRHandWeapon("Data/RHandWeaponsCSV.csv");
        LHandWeapons = CSVparser.LoadLHandWeapon("Data/LHandWeaponsCSV.csv");

        LevelStatsTable = CSVparser.LoadStatsTable("Data/StatsCSV.csv");
    }

    public List<Rune> getRunes() { return Runes; }

    public List<Origin> getOrigins() { return Origins; }

    public List<ChestArmor> getChestArmors() { return ChestArmors; }
    public List<HandArmor> getHandArmors() { return HandArmors; }
    public List<LegArmor> getLegArmors() { return LegArmors; }
    public List<HeadArmor> getHeadArmors() { return HeadArmors; }

    public List<RHandWeapon> getRHandWeapons() { return RHandWeapons; }
    public List<LHandWeapon> getLHandWeapons() { return LHandWeapons; }

    public Rune getRune(String Name){

        for(Rune rune : Runes){
            if(rune.getName().equals(Name)){
                return rune;
            }
        }
        System.out.println("Runa não Achada");
        return null;
    }
    public Origin getOrigin(String Name){
        for(Origin origin : Origins){
            if(origin.getName().equals(Name)){
                return origin;
            }
        }
        System.out.println("Origem não Achada");
        return null;
    }
    public HeadArmor getHeadArmor(String Name){
        for(HeadArmor head : HeadArmors){
            if(head.getName().equals(Name)){
                return head;
            }
        }
        System.out.println("HeadArmor não Achada");
        return null;
    }
    public ChestArmor getChestArmor(String Name){
        for(ChestArmor chest : ChestArmors){
            if(chest.getName().equals(Name)){
                return chest;
            }
        }
        System.out.println("ChestArmor não Achada");
        return null;
    }
    public LegArmor getLegArmor(String Name){
        for(LegArmor leg : LegArmors){
            if(leg.getName().equals(Name)){
                return leg;
            }
        }
        System.out.println("LegArmor não Achada");
        return null;
    }
    public HandArmor getHandArmor(String Name){
        for(HandArmor hand : HandArmors){
            if(hand.getName().equals(Name)){
                return hand;
            }
        }
        System.out.println("HandArmor não Achada");
        return null;
    }

    public double[][] getLevelStatsTable() { return LevelStatsTable; }
}
