package ru.gur.archintercessor.interaction.delivery;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.UUID;

public interface DeliveryClient {

    UUID createDelivery(DeliveryCreationRequest deliveryCreationRequest);

    void cancelDelivery(String processId, UUID deliveryId) throws JsonProcessingException;
}
