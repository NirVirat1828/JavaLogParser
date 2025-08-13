// LogParser.java
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LogParser {

    /**
     * Reads a log file and parses each line into a LogEntry object.
     * @param filePath the path to the log file
     * @return a list of parsed log entries
     */
    public List<LogEntry> parseFile(String filePath) {
        List<LogEntry> entries = new ArrayList<>();

        // Try-with-resources ensures the reader closes automatically
        try (BufferedReader reader = Files.newBufferedReader(Path.of(filePath), StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) continue;

                try {
                    // Find delimiters based on known format: TIMESTAMP [LEVEL] - Message
                    int levelStart = line.indexOf('[');
                    int levelEnd = line.indexOf(']');
                    int messageStart = line.indexOf(" - ");

                    // Validate format before parsing
                    if (levelStart == -1 || levelEnd == -1 || messageStart == -1) {
                        System.err.println("Skipping malformed line: " + line);
                        continue;
                    }

                    String timestamp = line.substring(0, levelStart).trim();
                    String level = line.substring(levelStart + 1, levelEnd).trim();
                    String message = line.substring(messageStart + 3).trim();

                    // Create immutable record
                    entries.add(new LogEntry(timestamp, level, message));

                } catch (Exception e) {
                    System.err.println("Error parsing line: " + line + " - " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
        }

        return entries;
    }
}
