package com.bloc6_simple_controllers.block6_simple_controllers;

import com.bloc6_simple_controllers.block6_simple_controllers.domain.Persona;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class MainClass implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MainClass.class, args);
    }

    @GetMapping(value = "/user/{nombre}")
    public String devuelveNombre(@PathVariable String nombre) {
        return "Hola " + nombre + "!";
    }

    @PostMapping(value = "/useradd")
    public ResponseEntity<Persona> userAdd(@RequestBody Persona persona) {
        return new ResponseEntity<>(new Persona(persona.getNombre(), persona.getPoblacion(), persona.getEdad() + 1), HttpStatus.CREATED);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}