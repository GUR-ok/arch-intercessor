package ru.gur.archintercessor.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

@Component
public class CreateDelivery extends AbstractCancelableDelegate {

    @Override
    protected void doExecute(DelegateExecution delegateExecution) {
        System.out.println("CreateDelivery");
    }
}
