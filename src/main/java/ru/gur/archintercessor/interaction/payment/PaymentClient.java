package ru.gur.archintercessor.interaction.payment;

public interface PaymentClient {

    String makePayment(PayRequest payRequest);
}
