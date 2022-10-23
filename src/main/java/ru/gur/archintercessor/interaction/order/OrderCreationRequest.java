package ru.gur.archintercessor.interaction.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderCreationRequest {

    private String processId;
}
