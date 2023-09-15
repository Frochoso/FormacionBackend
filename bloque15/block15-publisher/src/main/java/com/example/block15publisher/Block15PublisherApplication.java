package com.example.block15publisher;

import com.example.block15publisher.kafka.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Block15PublisherApplication{

    public static void main(String[] args) {
        SpringApplication.run(Block15PublisherApplication.class, args);
    }
}
