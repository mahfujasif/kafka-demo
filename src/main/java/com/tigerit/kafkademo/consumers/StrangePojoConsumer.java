package com.tigerit.kafkademo.consumers;

import com.tigerit.kafkademo.model.PojoB;
import com.tigerit.kafkademo.model.PojoA;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StrangePojoConsumer {

    @KafkaListener(
            topics = "sb-3-t1",
            groupId = "sb-3-grp3",
            autoStartup = "true",
            batch = "false",
            errorHandler = "kafkaErrorHandler"
    )
    public void listen(@Payload PojoA strangePojo,
                       Acknowledgment ack) {
        log.info("rcvd {}", strangePojo);
        ack.acknowledge();
    }

    @KafkaListener(
            topics = "sb-3-t2",
            groupId = "sb-3-grp4",
            autoStartup = "true",
            batch = "false",
            errorHandler = "kafkaErrorHandler"
    )
    public void listen2(@Payload PojoB strangePojo,
                       Acknowledgment ack) {
        log.info("rcvd2 {}", strangePojo);
        ack.acknowledge();
    }
}
