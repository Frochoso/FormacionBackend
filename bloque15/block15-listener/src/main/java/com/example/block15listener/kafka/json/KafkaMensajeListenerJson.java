package com.example.block15listener.kafka.json;

import com.example.block15listener.kafka.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class KafkaMensajeListenerJson {
    @Value(value = "${message.topic.name}")
    String topicName1;


    @Autowired
    Mensaje mensaje;

    @KafkaListener(topics = "${message.topic.name}", groupId = "${message.group.name}")
    public Mensaje listenTopic1(@Payload String car,
                                @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                @Header(name = KafkaHeaders.RECEIVED_TIMESTAMP, required = false) Long timeStamp) {
        System.out.println("Received Message of  topic: " + topicName1 + " in  listener: " + car +
                " TimeStamp: " + new Date(timeStamp));
        mensaje.setParticion(partition);
        mensaje.setTopico(topic);
        mensaje.setFecha(new Date(timeStamp));
        return mensaje;
    }
}