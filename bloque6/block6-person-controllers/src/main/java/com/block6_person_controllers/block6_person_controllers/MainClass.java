package com.block6_person_controllers.block6_person_controllers;

import com.block6_person_controllers.block6_person_controllers.domain.Persona;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainClass {

    public static void main(String[] args) {
        SpringApplication.run(MainClass.class, args);
    }

    @Bean
    @Qualifier("bean1")
    public Persona personaBean1() {
        return new Persona("Carla", "Churriana", 25);
    }

    @Bean
    @Qualifier("bean2")
    public Persona personaBean2() {
        return new Persona("Luciana", "Estepa", 30);
    }

    @Bean
    @Qualifier("bean3")
    public Persona personaBean3() {
        return new Persona("Javier", "Santa Elena", 20);
    }
}