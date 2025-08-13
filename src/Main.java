// Main.java
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // 1. Parse the log file into LogEntry objects
        LogParser parser = new LogParser();
        List<LogEntry> logEntries = parser.parseFile("src/app.log");

        // 2. Analyze the logs
        LogAnalyzer analyzer = new LogAnalyzer();
        Map<String, Long> countsByLevel = analyzer.countLogsByLevel(logEntries);
        List<String> errorMessages = analyzer.getErrorMessages(logEntries);

        // 3. Build the report content
        StringBuilder reportContent = new StringBuilder();
        reportContent.append("Log Analysis Report\n");
        reportContent.append("===================\n\n");

        reportContent.append("Log Counts by Level:\n");
        countsByLevel.forEach((level, count) ->
                reportContent.append("- ").append(level).append(": ").append(count).append("\n"));

        reportContent.append("\nCritical Error Messages:\n");
        errorMessages.forEach(msg ->
                reportContent.append("- ").append(msg).append("\n"));

        // 4. Write the report to file using a generic class
        ReportGenerator<String> reportGenerator = new ReportGenerator<>();
        reportGenerator.generateReport(reportContent.toString(), "report.txt");

        System.out.println("Report generated successfully: report.txt");
    }
}
