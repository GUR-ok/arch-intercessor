package ru.gur.archintercessor.delegate;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public abstract class AbstractCancelableDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        try {
            System.out.println("---");
            System.out.println("ServiceTask: " + this.getClass().getSimpleName());
            System.out.println("ActivityId: " + execution.getCurrentActivityId());

            doExecute(execution);
        } catch (Exception e) {
            throw new BpmnError("delegateCancelableError");
        }
    }

    protected abstract void doExecute(DelegateExecution delegateExecution);
}
