import java.util.List;

import Entitys.LevelStatsTable;
import Entitys.Personagem;
import Items.Armor.ChestArmor;
import Items.Armor.HandArmor;
import Items.Armor.HeadArmor;
import Items.Armor.LegArmor;
import Items.Rune.Rune;
import repository.Repository;
import Entitys.Origin;

public class Main {

    public static void main(String[] args){
        Repository Repository = new Repository();
        Repository.LoadAll();

        double[][] rawStats = Repository.getLevelStatsTable();
        LevelStatsTable Stats = new LevelStatsTable(rawStats);
        
        Origin origin = Repository.getOrigin("Milquetoast");
        Rune NoRune = Repository.getRune("NoRune");

        HeadArmor Head = Repository.getHeadArmor("Naked");
        ChestArmor Chest = Repository.getChestArmor("Naked");
        HandArmor Hand = Repository.getHandArmor("Naked");
        LegArmor Leg = Repository.getLegArmor("Naked");


        Personagem Hunter = new Personagem(origin,NoRune ,Chest ,Hand,Head,Leg,Stats);

        Hunter.setRuna1(Repository.getRune("Anticlockwise Metamorphosis (1)"));

        System.out.println(Hunter.getHealth());
        System.out.println(Hunter.getStamina());

    }
    
}
