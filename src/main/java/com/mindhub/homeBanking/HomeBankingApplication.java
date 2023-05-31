package com.mindhub.homeBanking;

import com.mindhub.homeBanking.models.Client;
import com.mindhub.homeBanking.repositories.ClientRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class HomeBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeBankingApplication.class, args);
	}
	@Bean

	public CommandLineRunner initData(ClientRepository repository){
		return (args -> {
			repository.save(new Client("Melba", "Morel", "melba@mindhub.com"));
			repository.save(new Client("Edu","Oriolani","edu.oriolani@gmail.com"));
		});

	}

}

