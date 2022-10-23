package ru.gur.archintercessor.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.delivery.DeliveryClient;
import ru.gur.archintercessor.interaction.delivery.DeliveryCreationRequest;
import ru.gur.archintercessor.variables.VariableKey;
import ru.gur.archintercessor.web.request.DeliveryTimeSlot;

import java.time.LocalDate;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateDelivery extends AbstractCancelableDelegate {

    private final DeliveryClient deliveryClient;

    @Override
    protected void doExecute(DelegateExecution delegateExecution) {
        System.out.println("CreateDelivery");

        final UUID deliveryId = deliveryClient.createDelivery(DeliveryCreationRequest.builder()
                .processId(delegateExecution.getProcessInstanceId())
                .deliveryDate((LocalDate) delegateExecution.getVariable(VariableKey.DELIVERY_DATE.name()))
                .deliveryTimeSlot((DeliveryTimeSlot) delegateExecution.getVariable(VariableKey.DELIVERY_TIME_SLOT.name()))
                .build());

        delegateExecution.setVariable(VariableKey.DELIVERY_ID.name(), deliveryId);

        System.out.println("CreateDelivery success");
    }
}
