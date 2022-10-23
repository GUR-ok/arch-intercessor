package ru.gur.archintercessor.web.request;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "event"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = NewOrderReceivedEventData.class, name = "NEW_ORDER_RECEIVED")
})
public interface HttpEvent extends EventSource{
}
