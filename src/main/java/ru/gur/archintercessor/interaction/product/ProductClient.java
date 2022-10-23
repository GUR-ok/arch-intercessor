package ru.gur.archintercessor.interaction.product;

import ru.gur.archintercessor.interaction.product.request.ProductReserveRequest;
import ru.gur.archintercessor.interaction.product.response.ProductReserveResponse;

public interface ProductClient {

    ProductReserveResponse reserveProduct(ProductReserveRequest productReserveRequest);

    void cancelReserve(String reserveId);
}
