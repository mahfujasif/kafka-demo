package com.tigerit.kafkademo.services;

import com.tigerit.kafkademo.model.PojoA;
import com.tigerit.kafkademo.model.SubEntity;
import com.tigerit.kafkademo.producers.KafkaProducer;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collections;

@Slf4j
@Service
public class DemoService {

    @Value("${kafka.topic.demo.a}")
    private String topicA;

    @Value("${kafka.topic.demo.b}")
    private String topicB;

    private final KafkaProducer kafkaProducer;

    public DemoService(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

//    @PostConstruct
    public void runDemo() {
        log.info("Send demo message");
        PojoA pojoA = new PojoA(1, 2, LocalDateTime.now(), "testString".getBytes(StandardCharsets.UTF_8), Collections.singletonList(new SubEntity(5, "22")));
        kafkaProducer.send(topicA, pojoA);

    }
}
