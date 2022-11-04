package ru.gur.archintercessor.delegate.compensation;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.order.OrderClient;
import ru.gur.archintercessor.variables.VariableKey;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CancelOrder extends AbstractCompensationDelegate {

    private final OrderClient orderClient;

    @Override
    protected void doExecute(DelegateExecution execution) throws JsonProcessingException {
        System.out.println("CancelOrder");

        orderClient.cancelOrder(execution.getProcessInstanceId(), (UUID) execution.getVariable(VariableKey.ORDER_ID.name()));
    }
}
