package com.example.block15publisher.kafka.json;

import com.example.block15publisher.kafka.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class KafkaControllerJson {
    @Autowired
    Mensaje mensaje;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @PostMapping("/json/{topic}/{key}")
    public Mensaje addJsonMessage(@PathVariable String topic, @PathVariable String key,
                                  @RequestBody Coche body) throws InterruptedException {

        String message = "marca: " + body.getMarca() + ". matricula: " + body.getMatricula();

        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, key, message);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                System.err.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            } else {
                System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
        });
        waitMessage();
        return mensaje;
    }

    private void waitMessage() throws InterruptedException {
        long initialDate = System.currentTimeMillis();
        long finalDate = initialDate + 1000 * 10; // Add 10 seconds
        while (!mensaje.isRecibido() && finalDate > System.currentTimeMillis()) {
            Thread.currentThread().sleep(500);
        }
    }
}