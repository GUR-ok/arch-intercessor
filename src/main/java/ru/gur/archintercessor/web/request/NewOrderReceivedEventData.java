package ru.gur.archintercessor.web.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@Validated
public class NewOrderReceivedEventData implements HttpEvent {

    @NotBlank
    private String productId;

    @NotNull
    private LocalDate deliveryDate;

    @NotNull
    private Long productQuantity;

    @Override
    public Event getEvent() {
        return Event.NEW_ORDER_RECEIVED;
    }
}