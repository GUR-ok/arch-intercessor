package ru.gur.archintercessor.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.order.OrderClient;
import ru.gur.archintercessor.interaction.order.OrderCreationRequest;
import ru.gur.archintercessor.variables.VariableKey;

@Component
@RequiredArgsConstructor
public class CreateOrder extends AbstractCancelableDelegate {

    private final OrderClient orderClient;

    @Override
    protected void doExecute(DelegateExecution delegateExecution) {
        System.out.println("CreateOrder");

        final Integer orderId = orderClient.createOrder(OrderCreationRequest.builder()
                .processId(delegateExecution.getProcessInstanceId())
                .build());

        delegateExecution.setVariable(VariableKey.ORDER_ID.name(), orderId);
    }
}
