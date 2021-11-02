package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Used to save events during Vending Machine runtime.
 */
public class AuditLogger {
    private static ArrayList<String> auditLogger = new ArrayList<String>();

    /**
     * Getter of Audit Logger
     * @return Audit Logger as an ArrayList
     */
    public static ArrayList<String> getAuditLogger() {
        return auditLogger;
    }

    /**
     * Method used to save event message in Audit Logger
     * @param event Event text to be saved to Audit Logger
     */
    public static void addEvent(String event) {
        StringBuilder sb = new StringBuilder();
        sb.append(getTime() + "\n" + event + "\n");
        auditLogger.add(sb.toString());
    }

    /**
     * Method used to get actual local time
     * @return Local date time in form: YYYY-MM-DD HH:MM:SS
     */
    public static String getTime() {
        String datetime = LocalDateTime.now().toString();
        String date = datetime.substring(0, 10);
        String time = datetime.substring(11, 19);
        StringBuilder sb = new StringBuilder();
        sb.append(date + " " + time);
        return sb.toString();

    }

    /**
     * Method used to save current AuditLogger as a text file
     */
    public static void saveAuditLogger() {
        StringBuilder content = new StringBuilder();
        for (String entry : AuditLogger.getAuditLogger()) {
            content.append(entry);
        }
        String path = "src/main/resources/Logger.txt";
        try {

            Files.writeString(Paths.get(path), content.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
