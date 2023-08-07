package com.block5profiles.block5_profiles;

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

	}

}
