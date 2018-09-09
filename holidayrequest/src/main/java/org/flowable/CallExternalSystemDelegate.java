package org.flowable;

import org.flowable.engine.delegate.DelegateExecution;

public class CallExternalSystemDelegate implements org.flowable.engine.delegate.JavaDelegate {

    public void execute(DelegateExecution execution) {
        System.out.println("Calling the external system for employee "
                + execution.getVariable("employee"));
    }
}
