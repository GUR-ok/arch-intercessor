package ru.gur.archintercessor.interaction.product.request;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProductReserveRequest {

    private String processId;

    private UUID orderId;

    private String productId;

    private Long quantity;
}
