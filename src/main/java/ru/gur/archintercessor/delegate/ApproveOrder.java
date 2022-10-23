package ru.gur.archintercessor.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.kafka.Producer;

@Component
@RequiredArgsConstructor
public class ApproveOrder implements JavaDelegate {

    private final Producer producer;

    @Override
    public void execute(DelegateExecution execution) {
        try {
            System.out.println("---");
            System.out.println("ServiceTask " + this.getClass());
            System.out.println("ActivityId: " + execution.getCurrentActivityId());
            System.out.println("Approve order with id: " + execution.getVariable("PRODUCT_ID"));
            System.out.println("Approve order with deliveryDate: " + execution.getVariable("DELIVERY_DATE"));
            System.out.println("Approve order with quantity: " + execution.getVariable("PRODUCT_QUANTITY"));
            System.out.println("Approve order with order Id: " + execution.getVariable("ORDER_ID"));

            System.out.println(execution.getProcessInstanceId());
            producer.sendString(execution.getProcessInstanceId() + " " + execution.getVariable("ORDER_ID"));

        } catch (Exception e) {
            throw new BpmnError("errorCode");
        }
    }
}
