package org.example.professionauction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"org.example.professionauction","org.example.blizzardapi", "org.example.dataObjects"})
@EntityScan(basePackages = {"org.example.dataObjects"})
@EnableJpaRepositories(basePackages = {"org.example.dataObjects"})
public class ProfessionauctionApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProfessionauctionApplication.class, args);
	}
}
