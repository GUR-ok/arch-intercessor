package ru.gur.archintercessor.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.payment.PayRequest;
import ru.gur.archintercessor.interaction.payment.PaymentClient;
import ru.gur.archintercessor.variables.VariableKey;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreatePayment extends AbstractCancelableDelegate {

    private final PaymentClient paymentClient;

    @Override
    protected void doExecute(DelegateExecution delegateExecution) {
        System.out.println("CreatePayment");

        final String paymentId = paymentClient.makePayment(PayRequest.builder()
                .processId(delegateExecution.getProcessInstanceId())
                .orderId((UUID) delegateExecution.getVariable(VariableKey.ORDER_ID.name()))
                .accountId((UUID) delegateExecution.getVariable(VariableKey.ACCOUNT_ID.name()))
                .amount((Double) delegateExecution.getVariable(VariableKey.AMOUNT.name()))
                .build());

        delegateExecution.setVariable(VariableKey.PAYMENT_ID.name(), paymentId);
    }
}
