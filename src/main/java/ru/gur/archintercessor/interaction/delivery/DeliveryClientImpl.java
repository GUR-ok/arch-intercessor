package ru.gur.archintercessor.interaction.delivery;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.gur.archintercessor.interaction.delivery.events.DeliveryCancelEventData;
import ru.gur.archintercessor.kafka.KafkaEvent;
import ru.gur.archintercessor.kafka.Producer;

import java.net.URI;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeliveryClientImpl implements DeliveryClient {

    private final Producer producer;

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${interaction.delivery.uri}")
    private URI deliveryUri;

    private static final String TOPIC = "delivery";

    @Override
    public UUID createDelivery(final DeliveryCreationRequest deliveryCreationRequest) {
        Assert.notNull(deliveryCreationRequest, "deliveryCreationRequest must not be null");

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(deliveryUri + "/delivery"); // The allRequestParams must have been built for all the query params

        final RequestEntity<DeliveryCreationRequest> requestEntity =
                RequestEntity
                        .post(builder.toUriString(), String.class)
                        .body(deliveryCreationRequest);
        log.info("DeliveryCreationRequest: {}", requestEntity);

        return restTemplate.exchange(requestEntity, UUID.class).getBody();
    }

    @Override
    public void cancelDelivery(final String processId, final UUID deliveryId) throws JsonProcessingException {
        Assert.hasText(processId, "processId must not be blank");
        Assert.notNull(deliveryId, "deliveryId must not be null");

        final KafkaEvent kafkaEvent = DeliveryCancelEventData.builder()
                .deliveryId(deliveryId)
                .build();

        producer.sendEvent(TOPIC, processId, kafkaEvent);
    }
}
