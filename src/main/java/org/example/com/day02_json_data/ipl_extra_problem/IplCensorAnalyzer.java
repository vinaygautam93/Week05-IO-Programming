package org.example.com.day02_json_data.ipl_extra_problem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IplCensorAnalyzer {
    public static void main(String[] args) {
        // Input file paths
        String jsonInputFile = "ipl_matches.json";
        String csvInputFile = "ipl_matches.csv";

        // Output file paths
        String jsonOutputFile = "ipl_matches_censored.json";
        String csvOutputFile = "ipl_matches_censored.csv";

        try {
            // Process JSON and CSV files
            processJsonFile(jsonInputFile, jsonOutputFile);
            processCsvFile(csvInputFile, csvOutputFile);

            System.out.println("✅ Censorship applied successfully. Check the output files!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Process JSON File - Read, Censor, and Write back to new JSON file
     */
    private static void processJsonFile(String inputFile, String outputFile) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // Read JSON file into a JsonNode (list of match objects)
        JsonNode matches = objectMapper.readTree(new File(inputFile));

        // Apply censorship
        for (JsonNode match : matches) {
            if (match instanceof ObjectNode) {
                ObjectNode matchObj = (ObjectNode) match;

                // Mask team names
                matchObj.put("team1", censorTeamName(matchObj.get("team1").asText()));
                matchObj.put("team2", censorTeamName(matchObj.get("team2").asText()));

                // Redact Player of the Match
                matchObj.put("player_of_match", "REDACTED");
            }
        }

        // Write the censored JSON back to a new file
        objectMapper.writeValue(new File(outputFile), matches);
    }

    /**
     * Process CSV File - Read, Censor, and Write back to new CSV file
     */
    private static void processCsvFile(String inputFile, String outputFile) throws Exception {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();

        // Read CSV file into a list of maps (Each row is a map of column names to values)
        List<Map<String, String>> matches = (List<Map<String, String>>) (List<?>)
                csvMapper.readerFor(Map.class)
                        .with(csvSchema)
                        .readValues(new File(inputFile))
                        .readAll();

        // Apply censorship
        List<Map<String, String>> censoredMatches = matches.stream().map(match -> {
            match.put("team1", censorTeamName(match.get("team1")));
            match.put("team2", censorTeamName(match.get("team2")));
            match.put("player_of_match", "REDACTED");
            return match;
        }).collect(Collectors.toList());

        // Write the censored data back to a new CSV file
        csvMapper.writer(csvSchema.withLineSeparator("\n"))
                .writeValue(new File(outputFile), censoredMatches);
    }

    /**
     * Censorship Rule - Mask part of the team name
     * Example: "Mumbai Indians" → "Mumbai ***"
     */
    private static String censorTeamName(String teamName) {
        String[] parts = teamName.split(" ", 2); // Split into two parts (first word & rest)
        return parts.length > 1 ? parts[0] + " ***" : teamName; // Keep first word, replace rest
    }
}
