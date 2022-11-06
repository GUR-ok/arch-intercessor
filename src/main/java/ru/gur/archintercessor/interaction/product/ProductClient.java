package ru.gur.archintercessor.interaction.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.gur.archintercessor.interaction.product.request.ProductReserveRequest;
import ru.gur.archintercessor.interaction.product.response.ProductReserveResponse;

import java.util.UUID;

public interface ProductClient {

    ProductReserveResponse reserveProduct(ProductReserveRequest productReserveRequest);

    void cancelReserve(String processId, UUID reserveId) throws JsonProcessingException;
}
