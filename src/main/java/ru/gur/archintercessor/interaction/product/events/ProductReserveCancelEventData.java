package ru.gur.archintercessor.interaction.product.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import ru.gur.archintercessor.kafka.Event;
import ru.gur.archintercessor.kafka.KafkaEvent;

import java.util.UUID;

@Data
@Builder
public class ProductReserveCancelEventData implements KafkaEvent {

    UUID orderId;

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Prevents duplication when serializing to JSON (subtype discriminator property)
    public Event getEvent() {
        return Event.PRODUCT_RESERVE_CANCEL;
    }
}
