package se.kth.iv1350.repairshop.util;

import java.lang.Exception;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;

/**
 * Handles the creation of error log files.
 */

public class LogHandler {
    private PrintWriter logFile;

    public LogHandler() throws IOException {
        logFile = new PrintWriter(new FileWriter("log.txt", true), true);
    }

    public void logException(Exception exception){
        logFile.println(LocalDateTime.now() + ", Exception thrown");
        exception.printStackTrace(logFile);
    }
}
