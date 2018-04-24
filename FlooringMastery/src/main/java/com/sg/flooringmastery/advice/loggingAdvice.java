package com.sg.flooringmastery.advice;

import com.sg.flooringmastery.dao.AuditDao;
import com.sg.flooringmastery.dao.OrderPersistenceException;
import org.aspectj.lang.JoinPoint;

public class loggingAdvice {

    AuditDao auditDao;

    public loggingAdvice(AuditDao auditDao) {
        this.auditDao = auditDao;
    }

    public void auditCreateUpdateDelete(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + " | Item ID: ";

        for (Object currentArg : args) {

            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (OrderPersistenceException e) {
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

    public void auditLogException(JoinPoint jp, Exception exception) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + " | " + exception.getMessage() + " ";

        for (Object currentArg : args) {

            auditEntry += currentArg + " ";
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (OrderPersistenceException e) {
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
    //..
}
