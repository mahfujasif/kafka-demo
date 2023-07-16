package com.tigerit.kafkademo.producers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
public class KafkaProducer {

    @Value("${kafka.sender.timeout.sec}")
    private long waitTime;

    public KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private <T> void sendMessageWithWait(String topic, String key, T data) throws InterruptedException, ExecutionException, TimeoutException {
        try {
            SendResult<String, Object> result = StringUtils.hasLength(key) ?
                    kafkaTemplate.send(topic, key, data).get(waitTime, TimeUnit.SECONDS) :
                    kafkaTemplate.send(topic, data).get(waitTime, TimeUnit.SECONDS);
            log.debug("Successfully Sent message to kafka. Metadata: {}", result.getRecordMetadata().toString());
        } catch (Exception e) {
            log.error("Error occurred while sending to kafka topic {}", topic, e);
            throw e;
        }
    }

    private <T> void sendMessage(String topic, String key, T data) {
        CompletableFuture<SendResult<String, Object>> future = StringUtils.hasLength(key) ?
                kafkaTemplate.send(topic, key, data) :
                kafkaTemplate.send(topic, data);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.debug("Successfully Sent message to kafka. Metadata: {}", result.getRecordMetadata().toString());
            } else {
                log.error("Unable to send message='{}'", data, ex);
            }
        });
    }

    public <T> void send(String topic, T data) {
        sendMessage(topic, null, data);
    }


    public <T> void send(String topic, String key, T data) {
        sendMessage(topic, key, data);
    }
}
