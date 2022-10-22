package ru.gur.archintercessor.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        try {
            doExecute(execution);
        } catch (Exception e) {
            throw new BpmnError("delegateErrorCode");
        }
    }

    private void doExecute(DelegateExecution execution) {
        System.out.println("Version: " + execution.getProcessEngineServices()
                .getRepositoryService()
                .getProcessDefinition(execution.getProcessDefinitionId())
                .getVersion());
        System.out.println("Version tag: " + execution.getProcessEngineServices()
                .getRepositoryService()
                .getProcessDefinition(execution.getProcessDefinitionId())
                .getVersionTag());
   }
}
