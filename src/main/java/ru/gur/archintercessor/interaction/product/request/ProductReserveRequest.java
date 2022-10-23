package ru.gur.archintercessor.interaction.product.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductReserveRequest {

    private String productId;
}
