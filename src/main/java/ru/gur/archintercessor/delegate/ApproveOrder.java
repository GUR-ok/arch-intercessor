package ru.gur.archintercessor.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.order.OrderClient;
import ru.gur.archintercessor.variables.VariableKey;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApproveOrder implements JavaDelegate {

    private final OrderClient orderClient;

    @Override
    public void execute(DelegateExecution execution) {
        try {
            System.out.println("---");
            System.out.println("ServiceTask " + this.getClass());
            System.out.println("ActivityId: " + execution.getCurrentActivityId());
            System.out.println("Approve order with id: " + execution.getVariable(VariableKey.ORDER_ID.name()));
            System.out.println("Approve order with deliveryDate: " + execution.getVariable(VariableKey.DELIVERY_DATE.name()));
            System.out.println("Approve order with deliveryTimeSlot: " + execution.getVariable(VariableKey.DELIVERY_TIME_SLOT.name()));
            System.out.println("Approve order with quantity: " + execution.getVariable(VariableKey.PRODUCT_QUANTITY.name()));
            System.out.println("Approve order with product Id: " + execution.getVariable(VariableKey.PRODUCT_ID.name()));
            System.out.println("Approve order with account Id: " + execution.getVariable(VariableKey.ACCOUNT_ID.name()));
            System.out.println("Approve order with amount: " + execution.getVariable(VariableKey.AMOUNT.name()));
            System.out.println("Approve order with payment Id: " + execution.getVariable(VariableKey.PAYMENT_ID.name()));
            System.out.println("Approve order with reserve Id: " + execution.getVariable(VariableKey.RESERVE_ID.name()));
            System.out.println("Approve order with delivery Id: " + execution.getVariable(VariableKey.DELIVERY_ID.name()));

            System.out.println(execution.getProcessInstanceId());

            orderClient.approveOrder(execution.getProcessInstanceId(), (UUID) execution.getVariable(VariableKey.ORDER_ID.name()));
        } catch (Exception e) {
            log.error("Delegate {}; Exception: {}", this.getClass().getSimpleName(), e);
            throw new BpmnError("errorCode");
        }
    }
}
