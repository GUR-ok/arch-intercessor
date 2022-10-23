package ru.gur.archintercessor.delegate.compensation;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.order.OrderClient;
import ru.gur.archintercessor.variables.VariableKey;

@Component
@RequiredArgsConstructor
public class CancelOrder extends AbstractCompensationDelegate {

    private final OrderClient orderClient;

    @Override
    protected void doExecute(DelegateExecution execution) {
        System.out.println("CancelOrder");

        orderClient.cancelOrder(execution.getProcessInstanceId(), (Integer) execution.getVariable(VariableKey.ORDER_ID.name()));
    }
}
