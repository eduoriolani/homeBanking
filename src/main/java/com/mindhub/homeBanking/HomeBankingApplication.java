package com.mindhub.homeBanking;

import com.mindhub.homeBanking.models.Account;
import com.mindhub.homeBanking.models.Client;
import com.mindhub.homeBanking.repositories.AccountRepository;
import com.mindhub.homeBanking.repositories.ClientRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;


@SpringBootApplication
public class HomeBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeBankingApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(ClientRepository repositoryClient, AccountRepository accountRepository){
		return (args -> {
			Client client1 = new Client("Melba", "Morel", "melba@mindhub.com");
			repositoryClient.save(client1);

			Account account1 = new Account("VIN001", LocalDate.now(), 5000.0, client1);
			Account account2 = new Account( "VIN002", LocalDate.now().plusDays(1), 7500.0, client1);
			accountRepository.save(account1);
			accountRepository.save(account2);
		});

	}
}


