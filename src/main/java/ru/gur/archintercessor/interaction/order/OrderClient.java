package ru.gur.archintercessor.interaction.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.gur.archintercessor.interaction.order.request.CreateOrderRequest;

import java.util.UUID;

public interface OrderClient {

    UUID createOrder(CreateOrderRequest orderCreationRequest);

    void cancelOrder(String processId, UUID orderId) throws JsonProcessingException;

    void approveOrder(String processId, UUID orderId) throws JsonProcessingException;
}
