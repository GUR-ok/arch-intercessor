package ru.gur.archintercessor.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.order.OrderClient;
import ru.gur.archintercessor.interaction.order.request.CreateOrderRequest;
import ru.gur.archintercessor.variables.VariableKey;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateOrder extends AbstractCancelableDelegate {

    private final OrderClient orderClient;

    @Override
    protected void doExecute(DelegateExecution delegateExecution) {

        final UUID orderId = orderClient.createOrder(CreateOrderRequest.builder()
                .processId(delegateExecution.getProcessInstanceId())
                .productQuantity((Long) delegateExecution.getVariable(VariableKey.PRODUCT_QUANTITY.name()))
                .build());

        delegateExecution.setVariable(VariableKey.ORDER_ID.name(), orderId);
    }
}
