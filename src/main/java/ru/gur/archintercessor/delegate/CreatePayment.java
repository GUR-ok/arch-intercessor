package ru.gur.archintercessor.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CreatePayment extends AbstractCancelableDelegate {

    @Value("${createPayment.error:false}")
    private Boolean createPaymentErrorFlag;

    @Override
    protected void doExecute(DelegateExecution delegateExecution) {
        if (createPaymentErrorFlag) {
            System.out.println("Error when CreatePayment");

            throw new RuntimeException();
        }
        System.out.println("Success CreatePayment");
    }
}
