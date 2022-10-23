package ru.gur.archintercessor.interaction.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PaymentClientImpl implements PaymentClient {

    @Value("${createPayment.error:false}")
    private Boolean createPaymentErrorFlag;

    @Override
    public String makePayment(PayRequest payRequest) {
        if (createPaymentErrorFlag) {
            System.out.println("Error when CreatePayment");

            throw new RuntimeException();
        }
        System.out.println("Success CreatePayment");
        return UUID.randomUUID().toString();
    }
}
