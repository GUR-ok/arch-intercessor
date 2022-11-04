package ru.gur.archintercessor.delegate.compensation;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReturnDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {
        try {
            System.out.println("Return: " + this.getClass().getSimpleName());
            System.out.println("ActivityId: " + delegateExecution.getCurrentActivityId());

            delegateExecution.getProcessEngineServices().getRuntimeService()
                    .createProcessInstanceModification(delegateExecution.getProcessInstanceId())
                    .startAfterActivity("Activity_0ovlep4")
                    .execute();
        } catch (Exception e) {
            log.error("Delegate {}; Exception: {}", this.getClass().getSimpleName(), e);
            throw new BpmnError("delegateCancelableError");
        }
    }
}
