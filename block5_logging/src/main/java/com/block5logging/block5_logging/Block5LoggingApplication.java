package com.block5logging.block5_logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;

@SpringBootApplication
@Slf4j
public class Block5LoggingApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Block5LoggingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.error("ERROR DE COMPILACIÓN");
        log.error("ERROR DE INSTALACIÓN");
        log.error(HttpStatus.I_AM_A_TEAPOT.getReasonPhrase());
        log.warn("CREDENCIALES NO RECOMENDADOS");
        log.info("Hola");
    }

}
