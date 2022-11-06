package ru.gur.archintercessor.interaction.delivery;

import lombok.Builder;
import lombok.Data;
import ru.gur.archintercessor.web.request.DeliveryTimeSlot;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class DeliveryCreationRequest {

    private String processId;

    private UUID orderId;

    private DeliveryTimeSlot deliveryTimeSlot;

    private LocalDate deliveryDate;
}
