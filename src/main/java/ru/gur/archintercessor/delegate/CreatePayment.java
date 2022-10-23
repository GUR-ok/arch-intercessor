package ru.gur.archintercessor.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.payment.PayRequest;
import ru.gur.archintercessor.interaction.payment.PaymentClient;
import ru.gur.archintercessor.variables.VariableKey;

@Component
@RequiredArgsConstructor
public class CreatePayment extends AbstractCancelableDelegate {

    private final PaymentClient paymentClient;

    @Override
    protected void doExecute(DelegateExecution delegateExecution) {
        System.out.println("CreatePayment");

        final String paymentId = paymentClient.makePayment(PayRequest.builder()
                .accountId((String) delegateExecution.getVariable(VariableKey.ACCOUNT_ID.name()))
                .amount((Double) delegateExecution.getVariable(VariableKey.AMOUNT.name()))
                .build());

        delegateExecution.setVariable(VariableKey.PAYMENT_ID.name(), paymentId);
    }
}
