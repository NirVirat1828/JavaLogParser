// LogAnalyzer.java
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogAnalyzer {

    /**
     * Counts log entries grouped by their level (INFO, WARN, ERROR, etc.)
     * @param entries list of LogEntry objects
     * @return map of level -> count
     */
    public Map<String, Long> countLogsByLevel(List<LogEntry> entries) {
        // group by level and count
        return entries.stream()
                .collect(Collectors.groupingBy(LogEntry::level, Collectors.counting()));
    }

    /**
     * Extracts only the error messages from the logs.
     * @param entries list of LogEntry objects
     * @return list of messages from ERROR level logs
     */
    public List<String> getErrorMessages(List<LogEntry> entries) {
        return entries.stream()
                .filter(entry -> "ERROR".equalsIgnoreCase(entry.level())) // only ERROR logs
                .map(LogEntry::message) // extract just the message text
                .collect(Collectors.toList());
    }
}
