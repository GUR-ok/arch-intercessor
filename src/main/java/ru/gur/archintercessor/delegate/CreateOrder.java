package ru.gur.archintercessor.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateOrder extends AbstractCancelableDelegate {

    @Override
    protected void doExecute(DelegateExecution delegateExecution) {
        System.out.println("CreateOrder");

        delegateExecution.setVariable("ORDER_ID", UUID.randomUUID().toString());
    }
}
