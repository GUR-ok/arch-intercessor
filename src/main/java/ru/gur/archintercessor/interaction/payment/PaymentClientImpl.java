package ru.gur.archintercessor.interaction.payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentClientImpl implements PaymentClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${interaction.billing.uri}")
    private URI billingUri;

    @Override
    public String makePayment(final PayRequest payRequest) {
        Assert.notNull(payRequest, "payRequest must not be null");

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(billingUri + "/account"); // The allRequestParams must have been built for all the query params

        final RequestEntity<PayRequest> requestEntity =
                RequestEntity
                        .post(builder.toUriString(), String.class)
                        .body(payRequest);
        log.info("DeliveryCreationRequest: {}", requestEntity);

        return restTemplate.exchange(requestEntity, String.class).getBody();
    }
}
