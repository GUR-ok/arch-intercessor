package ru.gur.archintercessor.interaction.delivery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.kafka.Producer;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeliveryClientImpl implements DeliveryClient {

    private final Producer producer;

    @Override
    public UUID createDelivery(DeliveryCreationRequest deliveryCreationRequest) {
        return UUID.randomUUID();
    }

    @Override
    public void cancelDelivery(UUID deliveryId) {
        producer.sendString(deliveryId.toString());
    }
}
