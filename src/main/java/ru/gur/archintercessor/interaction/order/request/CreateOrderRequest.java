package ru.gur.archintercessor.interaction.order.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CreateOrderRequest {

    private String processId;

    private Long productQuantity;
}
