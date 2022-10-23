package ru.gur.archintercessor.interaction.delivery;

import java.util.UUID;

public interface DeliveryClient {

    UUID createDelivery(DeliveryCreationRequest deliveryCreationRequest);

    void cancelDelivery(UUID deliveryId);
}
