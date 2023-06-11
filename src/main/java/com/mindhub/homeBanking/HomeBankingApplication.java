package com.mindhub.homeBanking;

import com.mindhub.homeBanking.models.*;
import com.mindhub.homeBanking.repositories.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@SpringBootApplication
public class HomeBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeBankingApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(ClientRepository repositoryClient, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository){
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

			List<Integer> hipotecario = List.of(12,24,36,48,60);
			List<Integer> personal = List.of(6,12,24);
			List<Integer> automotriz = List.of(12,24,36);
			Loan loan1 = new Loan("Hipotecario", hipotecario, 500000.0);
			Loan loan2 = new Loan("Personal", personal , 100000.0);
			Loan loan3 = new Loan( "Automotriz", automotriz, 300000.0);
			loanRepository.save(loan1);
			loanRepository.save(loan2);
			loanRepository.save(loan3);
			ClientLoan clientLoan1 = new ClientLoan(loan1, client1, 48 , 400000.0);
			ClientLoan clientLoan2 = new ClientLoan(loan2, client1, 12 , 50000.0);
			ClientLoan clientLoan3 = new ClientLoan(loan3, client2, 36, 200000.0);
			clientLoanRepository.save(clientLoan1);
			clientLoanRepository.save(clientLoan2);
			clientLoanRepository.save(clientLoan3);
		});


	}
}


