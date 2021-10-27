package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AuditLogger {
    private static ArrayList<String> auditLogger = new ArrayList<String>();

    public static ArrayList<String> getAuditLogger() {
        return auditLogger;
    }

    public static void addEvent(String event) {
        StringBuilder sb = new StringBuilder();
        sb.append(getTime() + "\n" + event +"\n");
        auditLogger.add(sb.toString());
    }

    public static String getTime() {
        String datetime = LocalDateTime.now().toString();
        String date = datetime.substring(0, 10);
        String time = datetime.substring(11, 19);
        StringBuilder sb = new StringBuilder();
        sb.append(date + " " + time);
        return sb.toString();

    }

    public static void saveAuditLogger() {
        StringBuilder content = new StringBuilder();
        for(String entry:AuditLogger.getAuditLogger()){
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
