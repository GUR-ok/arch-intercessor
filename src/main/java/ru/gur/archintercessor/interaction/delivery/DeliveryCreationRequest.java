package ru.gur.archintercessor.interaction.delivery;

import lombok.Builder;
import lombok.Data;
import ru.gur.archintercessor.web.request.DeliveryTimeSlot;

import java.time.LocalDate;

@Data
@Builder
public class DeliveryCreationRequest {

    private DeliveryTimeSlot deliveryTimeSlot;

    private LocalDate deliveryDate;

    private String processId;
}
