package ru.gur.archintercessor.interaction.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.interaction.product.request.ProductReserveRequest;
import ru.gur.archintercessor.interaction.product.response.ProductReserveResponse;
import ru.gur.archintercessor.kafka.Producer;

import java.util.Random;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductClientImpl implements ProductClient {

    private final Producer producer;

    @Override
    public ProductReserveResponse reserveProduct(ProductReserveRequest productReserveRequest) {
        return ProductReserveResponse.builder()
                .id(UUID.randomUUID().toString())
                .amount(new Random().nextDouble())
                .build();
    }

    @Override
    public void cancelReserve(String reserveId) {
        producer.sendString(reserveId);
    }
}
