package ru.gur.archintercessor.interaction.payment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PayRequest {

    private String accountId;

    private Double amount;
}
