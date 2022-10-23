package ru.gur.archintercessor.interaction.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gur.archintercessor.kafka.Producer;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class OrderClientImpl implements OrderClient {

    private final Producer producer;

    @Override
    public Integer createOrder(OrderCreationRequest orderCreationRequest) {
        return new Random().nextInt(100);
    }

    @Override
    public void cancelOrder(String processId, Integer orderId) {
        producer.sendString(processId + " " + orderId + "canceled");
    }

    @Override
    public void approveOrder(String processId, Integer orderId) {
        producer.sendString(processId + " " + orderId + "approved");
    }
}
