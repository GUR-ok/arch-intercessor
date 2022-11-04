package ru.gur.archintercessor.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Slf4j
public abstract class AbstractCancelableDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        try {
            System.out.println("---");
            System.out.println("ServiceTask: " + this.getClass().getSimpleName());
            System.out.println("ActivityId: " + execution.getCurrentActivityId());

            doExecute(execution);
        } catch (Exception e) {
            log.error("Delegate {}; Exception: {}", this.getClass().getSimpleName(), e);
            throw new BpmnError("delegateCancelableError");
        }
    }

    protected abstract void doExecute(DelegateExecution delegateExecution);
}
