package ru.gur.archintercessor.interaction.product.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductReserveResponse {

    private String id;

    private Double amount;
}
