package org.example;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AuditLoggerTest {

    @Test
    public void afterTwoEventsSizeIsTwo(){
        AuditLogger.addEvent("Test 1");
        AuditLogger.addEvent("Test 2");
        ArrayList<String> auditLogger = AuditLogger.getAuditLogger();
        assertEquals(2,auditLogger.size());
    }

}