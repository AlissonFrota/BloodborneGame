import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {
    
    static ArrayList<Arma> Armas = new ArrayList<Arma>();

    static ArrayList<Armadura> Armaduras = new ArrayList<Armadura>();

    static ArrayList<Ferramenta> Ferramentas = new ArrayList<Ferramenta>();

    static ArrayList<Origin> Origins = new ArrayList<Origin>();

    static ArrayList<Runa> Runas = new ArrayList<Runa>();

    public DataHandler(){
        ReadArmorCSV();
        ReadOriginCSV();
        ReadOriginCSV();
        ReadRuneCSV();
    }

    static void ReadArmorCSV() {
        String filePath = "Dado/ArmorCSV.csv";

        List<String[]> rows = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;

            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                rows.add(values);
            }

            String[][] matrix = new String[rows.size()][];
            matrix = rows.toArray(matrix);

            for (int i = 1; i < matrix.length; i++) {
                Armaduras.add(new Armadura(matrix[i]));
            }

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    static void ReadOriginCSV() {
        String filePath = "Dado/OriginCSV.csv";

        List<String[]> rows = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;

            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                rows.add(values);
            }

            String[][] matrix = new String[rows.size()][];
            matrix = rows.toArray(matrix);

            String[][] Transposta = new String[matrix[0].length][matrix.length];
            for (int i = 0; i < matrix.length; i++) {
                for(int j = 0; j < matrix[0].length; j++) {
                    Transposta[j][i] = matrix[i][j];
                }
            }

            for (int i = 1; i < Transposta.length; i++) {
                Origins.add(new Origin(Transposta[i]));
            }

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    static void ReadRuneCSV(){
        String filePath = "Dado/RunesCSV.csv";

        List<String[]> rows = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;

            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                rows.add(values);
            }

            String[][] matrix = new String[rows.size()][];
            matrix = rows.toArray(matrix);

            for (int i = 0; i < matrix.length; i++) {
                Runas.add(new Runa(matrix[i]));
            }

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void PrintRunas(){
        System.out.printf(
            "%-15s %5s %5s\n",
            "Nome", "Valor", "Tipo"
        );
        System.out.println("-------------------------------------------------------------------------------------------------");

        for (Runa runa : Runas) {
            String nome = runa.getName();
            if (nome.length() > 15) {
                nome = nome.substring(0, 12) + "..."; 
            }

            System.out.printf(
                "%-15s %5.2f %-5s\n",
                nome,
                runa.getValor(),
                runa.getTipo()
            );
        }
    }

    public static void PrintArmors() {
        System.out.printf(
            "%-15s %5s %5s %5s %5s %5s %5s %5s %6s %6s %6s %6s %8s\n",
            "Nome", "PhDR", "BuDR", "PiDR", "BdDR", "ADR", "FDR", "BoDR", "SPRES", "FPRES", "FRRES", "Beast", "Tipo"
        );
        System.out.println("-------------------------------------------------------------------------------------------------");

        for (Armadura armadura : Armaduras) {
            String nome = armadura.getName();
            if (nome.length() > 15) {
                nome = nome.substring(0, 12) + "..."; 
            }

            System.out.printf(
                "%-15s %5d %5d %5d %5d %5d %5d %5d %6d %6d %6d %6d %8d\n",
                nome,
                armadura.getPhDR(),
                armadura.getBuDR(),
                armadura.getPiDR(),
                armadura.getBdDR(),
                armadura.getADR(),
                armadura.getFDR(),
                armadura.getBoDR(),
                armadura.getSPRES(),
                armadura.getFPRES(),
                armadura.getFRRES(),
                armadura.getBeast(),
                armadura.getTypeOfArmor()
            );
        }
    }

    public static void PrintOrigins() {
        System.out.printf(
                "%-15s %5s %5s %5s %5s %5s %5s \n",
                "Class Name","Vitality //","Endurance //","Strengh //","Skill //","BloodTing //","Arcane"
        );
        System.out.println("-------------------------------------------------------------------------------------------------");

        for (Origin origins : Origins) {
            System.out.printf(
                    "%-15s %5d %5d %5d %5d %5d %5d \n",

                    origins.getNomeOrigem(),
                    origins.getVitality(),
                    origins.getEndurence(),
                    origins.getStrength(),
                    origins.getSkill(),
                    origins.getBloodtinge(),
                    origins.getArcane()
            );
        }
    }


}
