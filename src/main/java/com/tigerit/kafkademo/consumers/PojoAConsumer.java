package com.tigerit.kafkademo.consumers;

import com.tigerit.kafkademo.model.PojoA;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PojoAConsumer {


//    @KafkaListener(
//            topics = "${kafka.topic.demo.a}",
//            groupId = "${kafka.topic.demo.a.consumer.group}",
//            autoStartup = "${kafka.topic.demo.a.auto.startup}",
//            batch = "false",
//            errorHandler = "kafkaErrorHandler"
//    )
    public void listen(@Payload PojoA strangePojo,
                       Acknowledgment ack) {
        log.info("rcvd {}", strangePojo);
        ack.acknowledge();
    }
}
