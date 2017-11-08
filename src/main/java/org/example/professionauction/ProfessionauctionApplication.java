package org.example.professionauction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"org.example.professionauction","org.example.blizzardapi"})
public class ProfessionauctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfessionauctionApplication.class, args);
	}
}
