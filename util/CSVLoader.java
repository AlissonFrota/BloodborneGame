package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {
    public static List<String[]> carregar(String caminho) {
        List<String[]> linhas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;
            boolean isHeader = true;
            while ((linha = reader.readLine()) != null) {
                if (isHeader) { isHeader = false; continue; } //skipar primeira linha
                linhas.add(linha.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linhas;
    }
}

