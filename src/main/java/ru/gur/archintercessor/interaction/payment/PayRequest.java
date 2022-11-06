package ru.gur.archintercessor.interaction.payment;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PayRequest {

    private String processId;

    private UUID orderId;

    private String accountId;

    private Double amount;
}
