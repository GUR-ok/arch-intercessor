package ru.gur.archintercessor.delegate.compensation;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.product.ProductClient;
import ru.gur.archintercessor.variables.VariableKey;

@Component
@RequiredArgsConstructor
public class CancelReserve extends AbstractCompensationDelegate {

    private final ProductClient productClient;

    @Override
    protected void doExecute(DelegateExecution execution) {
        System.out.println("CancelReserve");

        productClient.cancelReserve(execution.getProcessInstanceId(), (String) execution.getVariable(VariableKey.RESERVE_ID.name()));
    }
}
