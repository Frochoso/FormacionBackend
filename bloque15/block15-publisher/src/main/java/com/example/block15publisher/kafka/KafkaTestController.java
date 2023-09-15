package com.example.block15publisher.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaTestController {
    @Autowired
    Publisher kafkaMessageProducer;

    @PostMapping("/add/{topic}/{key}")
    public void addIdCustomer(@PathVariable String topic, @PathVariable String key, @RequestBody String body) {
        kafkaMessageProducer.sendMessage(topic, key, body);
    }
}
