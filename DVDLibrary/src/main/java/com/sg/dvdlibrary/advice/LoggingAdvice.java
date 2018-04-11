package com.sg.dvdlibrary.advice;

import com.sg.dvdlibrary.dao.DvdLibraryAuditDao;
import com.sg.dvdlibrary.dao.DvdLibraryPersistenceException;
import org.aspectj.lang.JoinPoint;


public class LoggingAdvice {

    DvdLibraryAuditDao auditDao;

    public LoggingAdvice(DvdLibraryAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";

        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (DvdLibraryPersistenceException e) {
            System.err.print("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }



}
