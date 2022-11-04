package ru.gur.archintercessor.interaction.order.events;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class OrderCancelEventData implements KafkaEvent {

    UUID orderId;

    @Override
    public Event getEvent() {
        return Event.ORDER_CANCEL;
    }
}

