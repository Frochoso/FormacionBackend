package com.segundoProyecto.segundo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@Slf4j
@PropertySource("classpath:application.yaml")
public class SegundoApplication implements CommandLineRunner {

    @Value("${greeting}")
    private String greeting;

    @Value("${my.number}")
    private String number;

    @Value("${MYURL}")
    private String myurl;

    @Value("${MYURL2}")
    private String myurl2;

    @Value("${new.property: new.property no tiene valor}")
    private String newProperty;

    public static void main(String[] args) {
        SpringApplication.run(SegundoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        lectorDeValores();
    }

    public void lectorDeValores() {
        log.info("El valor de greeting es: " + greeting);
        log.info("El valor de my.number es: " + number);
        log.info("El valor de new.property es: " + newProperty);
        log.info("El valor de MYURL es: " + myurl);
        log.info("El valor de MYURL2 es: " + myurl2);
    }

}
