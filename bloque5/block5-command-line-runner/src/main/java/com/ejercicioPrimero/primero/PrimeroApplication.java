package com.ejercicioPrimero.primero;

import jakarta.annotation.PostConstruct;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PseudoColumnUsage;

@SpringBootApplication
@Slf4j
public class PrimeroApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PrimeroApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        tercerMensaje("Paco");
    }

    @PostConstruct
    public void primera() {
        log.info("Hola desde la principal");
    }

    @Bean
    CommandLineRunner segundoMensaje() {
        return p -> {
            log.info("Hola desde la secundaria");
        };
    }

    public void tercerMensaje(String nombre) {
        log.info(nombre);
    }

}
