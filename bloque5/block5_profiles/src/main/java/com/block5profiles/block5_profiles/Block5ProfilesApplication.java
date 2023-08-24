package com.block5profiles.block5_profiles;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class Block5ProfilesApplication implements CommandLineRunner {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    public static void main(String[] args) {
        SpringApplication.run(Block5ProfilesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (activeProfile.equals("INT")) {
            perfilInt();
        } else if (activeProfile.equals("local")) {
            perfilLocal();
        } else if (activeProfile.equals("PRO")) {
            perfilPro();
        }
    }

    public static void perfilInt() {
        System.out.println("Hola desde el perfil INT");
    }

    public static void perfilLocal() {
        System.out.println("Hola desde el perfil local");
    }

    public static void perfilPro() {
        System.out.println("Hola desde el perfil PRO");
    }
}
