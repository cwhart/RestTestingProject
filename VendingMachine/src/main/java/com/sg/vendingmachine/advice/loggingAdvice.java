package com.sg.vendingmachine.advice;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;
import org.aspectj.lang.JoinPoint;

public class loggingAdvice {

    //Adding a comment for commit

    VendingMachineAuditDao auditDao;

    public loggingAdvice(VendingMachineAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    public void auditPurchaseItem(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + " | Item ID: ";

        for (Object currentArg : args) {

            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachinePersistenceException e) {
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

    public void auditLogException(JoinPoint jp, Exception exception) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + " | " + exception.getMessage();

        /*for (Object currentArg : args) {

            auditEntry += currentArg;
        }*/
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachinePersistenceException e) {
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
}
