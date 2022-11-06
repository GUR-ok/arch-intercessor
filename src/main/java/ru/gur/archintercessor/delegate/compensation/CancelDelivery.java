package ru.gur.archintercessor.delegate.compensation;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.delivery.DeliveryClient;
import ru.gur.archintercessor.variables.VariableKey;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CancelDelivery extends AbstractCompensationDelegate{

    private final DeliveryClient deliveryClient;

    @Override
    protected void doExecute(DelegateExecution execution) throws JsonProcessingException {
        System.out.println("CancelDelivery");

        deliveryClient.cancelDelivery(execution.getProcessInstanceId(), (UUID) execution.getVariable(VariableKey.DELIVERY_ID.name()));
    }
}
