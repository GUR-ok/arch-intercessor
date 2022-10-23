package ru.gur.archintercessor.interaction.order;

public interface OrderClient {

    Integer createOrder(OrderCreationRequest orderCreationRequest);

    void cancelOrder(String processId, Integer orderId);

    void approveOrder(String processId, Integer orderId);
}
