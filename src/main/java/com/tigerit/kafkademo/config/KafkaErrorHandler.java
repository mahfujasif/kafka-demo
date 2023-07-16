package com.tigerit.kafkademo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Component
public class KafkaErrorHandler implements KafkaListenerErrorHandler {
    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException exception) {
        log.error("errrrrrrrrr {} {} {}", message, exception.getGroupId(), exception.getMessage());
        return null;
    }


//    @Override
//    public void handle(Exception thrownException, ConsumerRecord<?, ?> record) {
//        // Custom error handling logic
//        System.out.println("Error occurred while consuming message: " + record.value());
//        thrownException.printStackTrace();
//    }
}
