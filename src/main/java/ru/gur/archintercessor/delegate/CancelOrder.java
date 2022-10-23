package ru.gur.archintercessor.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.kafka.Producer;

@Component
@RequiredArgsConstructor
public class CancelOrder extends AbstractCompensationDelegate {

    private final Producer producer;

    @Override
    protected void doExecute(DelegateExecution execution) {
        System.out.println("CancelOrder");
        System.out.println(execution.getProcessInstanceId());

        producer.sendString(execution.getProcessInstanceId() + " " + execution.getVariable("ORDER_ID"));
    }
}
