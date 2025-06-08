package util;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

import Entitys.Origin;
import Entitys.Personagem;
import repository.Repository;

public class PersonagemIO {

    private static final String FILE_NAME = "personagem.csv";

    public static void savePersonagem(Personagem p, String saveDir) throws IOException {
        File dir = new File(saveDir);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("Could not create save directory: " + saveDir);
        }
        File file = new File(dir, FILE_NAME);
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            pw.println(String.join(",",
                    "Origin","Level","Vitality","Endurence","Strength","Skill","Bloodtinge","Arcane",
                    "BloodEchoes","Insight",
                    "Head","Chest","Hands","Legs",
                    "Runa1","Runa2","Runa3",
                    "RHand","LHand"
            ));

            pw.printf("%s,%d,%d,%d,%d,%d,%d,%d,%d,%d,%s,%s,%s,%s,%s,%s,%s,%s,%s%n",
                    p.getOrigin().getName(),
                    p.getLevel(),
                    p.getVitality(),
                    p.getEndurence(),
                    p.getStrength(),
                    p.getSkill(),
                    p.getBloodtinge(),
                    p.getArcane(),
                    p.getBloodEchoes(),
                    p.getInsight(),
                    p.getHead().getName(),
                    p.getChest().getName(),
                    p.getHands().getName(),
                    p.getLegs().getName(),
                    p.getRuna1().getName(),
                    p.getRuna2().getName(),
                    p.getRuna3().getName(),
                    p.getRHand().getName(),
                    p.getLHand().getName()
            );
        }
    }

    public static Personagem loadPersonagemIfExists(String saveDir, Repository repo) throws IOException {
        File file = new File(saveDir, FILE_NAME);
        if (!file.exists()) return null;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String[] headers = br.readLine().split(",");
            String[] values  = br.readLine().split(",");

            Map<String,String> map = new HashMap<>();
            for (int i = 0; i < headers.length; i++) {
                map.put(headers[i], values[i]);
            }

            Origin origin = repo.getOrigin(map.get("Origin"));
            Personagem p = new Personagem(origin, repo);

            String[] intsToRestore = {
                    "Level","Vitality","Endurence","Strength","Skill","Bloodtinge","Arcane",
                    "Insight"
            };
            for (String field : intsToRestore) {
                setIntField(p, field, Integer.parseInt(map.get(field)));
            }
            int desiredBE = Integer.parseInt(map.get("BloodEchoes"));
            int currentBE = p.getBloodEchoes();
            p.setBloodEchoes(desiredBE - currentBE);


            p.setHeadArmor   ( repo.getHeadArmor  ( map.get("Head") ) );
            p.setChestArmor  ( repo.getChestArmor ( map.get("Chest") ) );
            p.setHandArmor   ( repo.getHandArmor  ( map.get("Hands") ) );
            p.setLegArmor    ( repo.getLegArmor   ( map.get("Legs") ) );

            p.setRuna1       ( repo.getRune       ( map.get("Runa1") ) );
            p.setRuna2       ( repo.getRune       ( map.get("Runa2") ) );
            p.setRuna3       ( repo.getRune       ( map.get("Runa3") ) );

            p.setRHand       ( repo.getRHandWeapon(map.get("RHand")) );
            p.setLHand       ( repo.getLHandWeapon(map.get("LHand")) );


            Method compute = Personagem.class
                    .getDeclaredMethod("ComputeDerivedStats");
            compute.setAccessible(true);
            compute.invoke(p);

            return p;
        } catch (Exception e) {
            throw new IOException("Failed to load Personagem", e);
        }
    }

    private static void setIntField(Personagem p, String fieldName, int value) throws Exception {
        Field f = Personagem.class.getDeclaredField(fieldName);
        f.setAccessible(true);
        f.setInt(p, value);
    }
}
