package com.example.block15publisher.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class Publisher {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${message.topic.name:salutation}")
    private String topicName;

    public void sendMessage(String topic, String key, String message) {
        if (topic == null || topic.trim().equals(""))
            topic = topicName;

        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, key, message);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                System.err.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            } else {
                System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
        });
    }
}
