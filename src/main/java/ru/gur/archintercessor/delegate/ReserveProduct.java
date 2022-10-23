package ru.gur.archintercessor.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.product.ProductClient;
import ru.gur.archintercessor.interaction.product.request.ProductReserveRequest;
import ru.gur.archintercessor.interaction.product.response.ProductReserveResponse;
import ru.gur.archintercessor.variables.VariableKey;

@Component
@RequiredArgsConstructor
public class ReserveProduct extends AbstractCancelableDelegate {

    private final ProductClient productClient;

    @Override
    protected void doExecute(DelegateExecution delegateExecution) {
        System.out.println("ReserveProduct");

        final ProductReserveResponse productReserveResponse = productClient.reserveProduct(ProductReserveRequest.builder()
                .productId((String) delegateExecution.getVariable(VariableKey.PRODUCT_ID.name()))
                .build());

        delegateExecution.setVariable(VariableKey.RESERVE_ID.name(), productReserveResponse.getId());
        delegateExecution.setVariable(VariableKey.AMOUNT.name(), productReserveResponse.getAmount());
    }
}
