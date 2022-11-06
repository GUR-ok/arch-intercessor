package ru.gur.archintercessor.interaction.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.gur.archintercessor.interaction.product.events.ProductReserveCancelEventData;
import ru.gur.archintercessor.interaction.product.request.ProductReserveRequest;
import ru.gur.archintercessor.interaction.product.response.ProductReserveResponse;
import ru.gur.archintercessor.kafka.KafkaEvent;
import ru.gur.archintercessor.kafka.Producer;

import java.net.URI;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductClientImpl implements ProductClient {

    private final Producer producer;

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${interaction.store.uri}")
    private URI storeUri;

    private static final String TOPIC = "store";

    @Override
    public ProductReserveResponse reserveProduct(final ProductReserveRequest productReserveRequest) {
        Assert.notNull(productReserveRequest, "productReserveRequest must not be null");

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(storeUri + "/products"); // The allRequestParams must have been built for all the query params

        final RequestEntity<ProductReserveRequest> requestEntity =
                RequestEntity
                        .post(builder.toUriString(), String.class)
                        .body(productReserveRequest);
        log.info("ProductReserveRequest: {}", requestEntity);

        return restTemplate.exchange(requestEntity, ProductReserveResponse.class).getBody();
    }

    @Override
    public void cancelReserve(final String processId, final UUID reserveId) throws JsonProcessingException {
        Assert.hasText(processId, "processId must not be blank");
        Assert.notNull(reserveId, "reserveId must not be null");

        final KafkaEvent kafkaEvent = ProductReserveCancelEventData.builder()
                .orderId(reserveId)
                .build();

        producer.sendEvent(TOPIC, processId, kafkaEvent);
    }
}
