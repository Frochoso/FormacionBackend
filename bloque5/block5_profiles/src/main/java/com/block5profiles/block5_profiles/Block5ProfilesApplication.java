package com.block5profiles.block5_profiles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class Block5ProfilesApplication implements CommandLineRunner {

    @Value("${spring.profiles.active}")
    private static String activeProfile;

    public static void main(String[] args) {
        SpringApplication.run(Block5ProfilesApplication.class, args);

        if (activeProfile.equals("INT")) {
            System.out.println("Hola desde el perfil INT");
        } else if (activeProfile.equals("local")) {
            System.out.println("Hola desde el perfil local");
        } else if (activeProfile.equals("PRO")) {
            System.out.println("Hola desde el perfil PRO");
        }
    }

    @Override
    public void run(String... args) throws Exception {

    }

}
