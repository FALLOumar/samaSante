package com.springRest.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.apache.commons.csv.*;
import java.nio.file.Paths;

@Service
public class MedicalDataService {

    @Value("${medical.data.path}")
    private String medicalDataPath;

    public List<Map<String, String>> loadMedicalData() {
        List<Map<String, String>> records = new ArrayList<>();

        try (Reader reader = Files.newBufferedReader(Paths.get(medicalDataPath));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
            for (CSVRecord csvRecord : csvParser) {
                Map<String, String> record = new HashMap<>();
                csvRecord.toMap().forEach(record::put);
                records.add(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return records;
    }

    public void analyzeData() {
        // Charger les données médicales (optionnel)
        List<Map<String, String>> data = loadMedicalData();

        // Exécuter le script Python
        try {
            ProcessBuilder pb = new ProcessBuilder("python", "path/to/predictive_analysis.py");
            pb.redirectErrorStream(true);
            Process process = pb.start();

            // Lire la sortie du script Python
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Attendre la fin de l'exécution du script
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Python script executed successfully.");

                // Lire l'accuracy à partir du fichier texte généré par le script Python
                String accuracy = new String(Files.readAllBytes(Paths.get("accuracy.txt")));
                System.out.println("Accuracy: " + accuracy);
            } else {
                System.out.println("Python script execution failed with exit code " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
