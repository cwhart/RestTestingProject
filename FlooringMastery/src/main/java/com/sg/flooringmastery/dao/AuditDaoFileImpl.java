package com.sg.flooringmastery.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditDaoFileImpl implements AuditDao {


        public static final String AUDIT_FILE = "audit.txt";
        @Override
        public void writeAuditEntry(String entry) throws OrderPersistenceException {
            PrintWriter out;

            try {
                out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
            } catch (IOException e) {
                throw new OrderPersistenceException("Could not persist order information.", e);
            }

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm"));
            out.println(timestamp + " : " + entry);
            out.flush();

        } //..
    }

