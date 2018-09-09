package org.flowable;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;


@Service
public class DoNothingDelegate1 implements JavaDelegate {


    public void execute (DelegateExecution execution) {
        System.out.println("Do nothing delegate #1 ran for execution:" + execution.getId() + " Event Name:" + execution.getEventName());
    }
}
