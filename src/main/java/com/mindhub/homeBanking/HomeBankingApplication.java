package com.mindhub.homeBanking;

import com.mindhub.homeBanking.models.Account;
import com.mindhub.homeBanking.models.Client;
import com.mindhub.homeBanking.models.Transaction;
import com.mindhub.homeBanking.models.TransactionType;
import com.mindhub.homeBanking.repositories.AccountRepository;
import com.mindhub.homeBanking.repositories.ClientRepository;
import com.mindhub.homeBanking.repositories.TransactionRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;


@SpringBootApplication
public class HomeBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeBankingApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(ClientRepository repositoryClient, AccountRepository accountRepository, TransactionRepository transactionRepository){
		return (args -> {
			Client client1 = new Client("Melba", "Morel", "melba@mindhub.com");
			Client client2 = new Client( "Edu", "Oriolani", "edu.oriolani@gmail.com");
			repositoryClient.save(client1);
			repositoryClient.save(client2);

			Account account1 = new Account("VIN001", LocalDate.now(), 5000.0, client1);
			Account account2 = new Account( "VIN002", LocalDate.now().plusDays(1), 7500.0, client1);
			Account account3 = new Account("VIN003", LocalDate.now(), 24000.0, client2);
			client1.addAccounts(account1);
			client1.addAccounts(account2);
			client2.addAccounts(account3);
			accountRepository.save(account1);
			accountRepository.save(account2);
			accountRepository.save(account3);

			Transaction transaction1 = new Transaction(TransactionType.DEBIT, -400.0, "Debito de 400", LocalDateTime.now());
			Transaction transaction2 = new Transaction(TransactionType.CREDIT, 5400.0, "Acreditacion de 5400", LocalDateTime.now());
			Transaction transaction3 = new Transaction(TransactionType.CREDIT, 3400.0, "Acreditacion de $3400", LocalDateTime.now());
			Transaction transaction4 = new Transaction(TransactionType.CREDIT, 3250.0, "Acreditacion de $3250", LocalDateTime.now());
			account1.addTransactions(transaction1);
			account1.addTransactions(transaction2);
			account2.addTransactions(transaction4);
			account3.addTransactions(transaction3);

			transactionRepository.save(transaction1);
			transactionRepository.save(transaction2);
			transactionRepository.save(transaction3);
			transactionRepository.save(transaction4);


		});


	}
}


