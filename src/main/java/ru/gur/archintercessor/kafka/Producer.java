package ru.gur.archintercessor.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class Producer {
    private final KafkaTemplate<String, String> kafkaTemplateString;

    public void sendString(String data) {
        kafkaTemplateString.send("intercessor", data)
                .addCallback(
                        result -> log.info("Kafka send complete"),
                        fail -> log.error("Kafka fail send"));
    }
}
