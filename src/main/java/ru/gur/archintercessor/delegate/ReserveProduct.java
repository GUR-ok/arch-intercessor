package ru.gur.archintercessor.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.product.ProductClient;
import ru.gur.archintercessor.interaction.product.request.ProductReserveRequest;
import ru.gur.archintercessor.interaction.product.response.ProductReserveResponse;
import ru.gur.archintercessor.variables.VariableKey;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ReserveProduct extends AbstractCancelableDelegate {

    private final ProductClient productClient;

    @Override
    protected void doExecute(DelegateExecution delegateExecution) {
        System.out.println("ReserveProduct");

        final ProductReserveResponse productReserveResponse = productClient.reserveProduct(ProductReserveRequest.builder()
                .processId(delegateExecution.getProcessInstanceId())
                .orderId((UUID) delegateExecution.getVariable(VariableKey.ORDER_ID.name()))
                .productId((String) delegateExecution.getVariable(VariableKey.PRODUCT_ID.name()))
                .quantity((Long) delegateExecution.getVariable(VariableKey.PRODUCT_QUANTITY.name()))
                .build());

        delegateExecution.setVariable(VariableKey.RESERVE_ID.name(), productReserveResponse.getReserveId());
        delegateExecution.setVariable(VariableKey.AMOUNT.name(), productReserveResponse.getAmount());
    }
}
