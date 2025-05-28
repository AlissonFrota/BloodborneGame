import java.util.List;

import Entitys.LevelStatsTable;
import Entitys.Personagem;
import repository.Repository;
import Entitys.Origin;

public class Main {

    public static void main(String[] args){
        Repository Repository = new Repository();
        Repository.LoadAll();

        double[][] rawStats = Repository.getLevelStatsTable();

        LevelStatsTable Stats = new LevelStatsTable(rawStats);

        List<Origin> Origins = Repository.getOrigins();
        
        Origin found = null;

        for (Origin origin : Origins) {
            if (origin.getName().equals("Milquetoast")) {
                found = origin;
                break;
            }
        }

        Personagem Hunter = new Personagem(found, Stats);

        System.out.println(Hunter.getHealth());

    }
    
}
