package ru.gur.archintercessor.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

@Component
public class CancelDelivery extends AbstractCompensationDelegate{

    @Override
    protected void doExecute(DelegateExecution execution) {
        System.out.println("CancelDelivery");
    }
}
