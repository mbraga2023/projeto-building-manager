package com.projetobuildingmanager.projetobuildingmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProjetoBuildingManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoBuildingManagerApplication.class, args);
	}

}
