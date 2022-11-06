package ru.gur.archintercessor.interaction.delivery.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import ru.gur.archintercessor.kafka.Event;
import ru.gur.archintercessor.kafka.KafkaEvent;

import java.util.UUID;

@Data
@Builder
public class DeliveryCancelEventData implements KafkaEvent {

    UUID deliveryId;

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Prevents duplication when serializing to JSON (subtype discriminator property)
    public Event getEvent() {
        return Event.DELIVERY_CANCEL;
    }
}
