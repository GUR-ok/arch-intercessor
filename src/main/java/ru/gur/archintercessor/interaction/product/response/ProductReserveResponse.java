package ru.gur.archintercessor.interaction.product.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProductReserveResponse {

    private UUID reserveId;

    private Double amount;
}
