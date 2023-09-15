package com.example.block15listener.kafka;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Listener {
    @Value(value = "${message.topic.name}")
    String topicName1;

    @Autowired
    Mensaje mensaje;

    @org.springframework.kafka.annotation.KafkaListener(topics = "${message.topic.name}", groupId = "${message.group.name}")
    public void listenTopic1(@Payload String message,
                             @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                             @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                             @Header(name = KafkaHeaders.RECEIVED_TIMESTAMP, required = false) Long timeStamp) {
        System.out.println("Received Message of  topic: " + topicName1 + " in  listener: " + message +
                " TimeStamp: " + new Date(timeStamp));
        mensaje.setParticion(partition);
        mensaje.setTopico(topic);
        mensaje.setFecha(new Date(timeStamp));
        mensaje.setRecibido(true);
    }

}
