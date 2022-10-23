package ru.gur.archintercessor.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class ReturnDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("Return: " + this.getClass().getSimpleName());
        System.out.println("ActivityId: " + delegateExecution.getCurrentActivityId());

        delegateExecution.getProcessEngineServices().getRuntimeService()
                .createProcessInstanceModification(delegateExecution.getProcessInstanceId())
                .startAfterActivity("Activity_0ovlep4")
                .execute();
    }
}
