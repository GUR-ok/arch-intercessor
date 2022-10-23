package ru.gur.archintercessor.service.handler;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import ru.gur.archintercessor.variables.VariableKey;
import ru.gur.archintercessor.web.request.Event;
import ru.gur.archintercessor.web.request.EventSource;
import ru.gur.archintercessor.web.request.NewOrderReceivedEventData;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class NewOrderReceivedHandler implements EventHandler<NewOrderReceivedEventData> {

    private static final String PROCESS_KEY = "PROCESS_KEY2";

    private final RuntimeService runtimeService;

    @Override
    public boolean canHandle(final EventSource eventSource) {
        Assert.notNull(eventSource, "EventSource must not be null");

        return Event.NEW_ORDER_RECEIVED.equals(eventSource.getEvent());
    }

    @Override
    public String handleEvent(final NewOrderReceivedEventData eventSource) {
        Assert.notNull(eventSource, "EventSource must not be null");

        Map<String,Object> variables = new HashMap<>();
        variables.put(VariableKey.PRODUCT_ID.name(), eventSource.getProductId());
        variables.put(VariableKey.DELIVERY_DATE.name(), eventSource.getDeliveryDate());
        variables.put(VariableKey.PRODUCT_QUANTITY.name(), eventSource.getProductQuantity());
        variables.put(VariableKey.DELIVERY_TIME_SLOT.name(), eventSource.getDeliveryTimeSlot());
        variables.put(VariableKey.ACCOUNT_ID.name(), eventSource.getAccountId());

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_KEY, variables);

        return processInstance.getId();
    }
}
