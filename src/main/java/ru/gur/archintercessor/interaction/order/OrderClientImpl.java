package ru.gur.archintercessor.interaction.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.gur.archintercessor.interaction.order.events.KafkaEvent;
import ru.gur.archintercessor.interaction.order.events.OrderApproveEventData;
import ru.gur.archintercessor.interaction.order.events.OrderCancelEventData;
import ru.gur.archintercessor.interaction.order.request.CreateOrderRequest;
import ru.gur.archintercessor.kafka.Producer;

import java.net.URI;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderClientImpl implements OrderClient {

    private final Producer producer;
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${interaction.profiles.uri}")
    private URI profilesUri;

    @Override
    public UUID createOrder(final CreateOrderRequest orderCreationRequest) {
        Assert.notNull(orderCreationRequest, "orderCreationRequest must not be null");

        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("profileId", UUID.randomUUID().toString()); //profileId is stubbed
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(profilesUri + "/orders") // rawValidURl = http://example.com/hotels
                .queryParams(params); // The allRequestParams must have been built for all the query params

        final RequestEntity<CreateOrderRequest> requestEntity =
                RequestEntity
                        .post(builder.toUriString(), String.class, params)
                        .header("idempotency-key", UUID.randomUUID().toString()) //idemp key is stubbed
                        .body(orderCreationRequest);
        log.info("CreateOrderRequest: {}", requestEntity);

        return restTemplate.exchange(requestEntity, UUID.class).getBody();
    }

    @Override
    public void cancelOrder(final String processId, final UUID orderId) throws JsonProcessingException {
        Assert.hasText(processId, "processId must not be null");
        Assert.notNull(orderId, "orderId must not be null");

        final KafkaEvent kafkaEvent = OrderCancelEventData.builder()
                .orderId(orderId)
                .build();

        final ObjectMapper objectMapper = new ObjectMapper();
        final String event = objectMapper.writeValueAsString(kafkaEvent);

        producer.sendString(processId, event);
    }

    @Override
    public void approveOrder(final String processId, final UUID orderId) throws JsonProcessingException {
        Assert.hasText(processId, "processId must not be null");
        Assert.notNull(orderId, "orderId must not be null");

        final KafkaEvent kafkaEvent = OrderApproveEventData.builder()
                .orderId(orderId)
                .build();

        final ObjectMapper objectMapper = new ObjectMapper();
        final String event = objectMapper.writeValueAsString(kafkaEvent);

        producer.sendString(processId, event);
    }
}
