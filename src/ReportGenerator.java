// ReportGenerator.java
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReportGenerator<T> {

    /**
     * Writes the given report data to a file.
     * @param reportData the data to write (could be String, Map, List, etc.)
     * @param filename name of the output file
     */
    public void generateReport(T reportData, String filename) {
        // Convert the generic report data into a string
        StringBuilder sb = new StringBuilder();

        if (reportData instanceof String str) {
            sb.append(str);
        } else {
            // Fallback: call toString() if not a String
            sb.append(reportData.toString());
        }

        // Write to file with UTF-8 encoding
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(filename), StandardCharsets.UTF_8)) {
            writer.write(sb.toString());
        } catch (IOException e) {
            System.err.println("Error writing report to file: " + filename);
            e.printStackTrace();
        }
    }
}
