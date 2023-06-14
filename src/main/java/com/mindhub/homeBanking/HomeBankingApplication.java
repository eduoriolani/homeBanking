package com.mindhub.homeBanking;

import com.mindhub.homeBanking.enums.CardColorType;
import com.mindhub.homeBanking.enums.CardType;
import com.mindhub.homeBanking.enums.TransactionType;
import com.mindhub.homeBanking.models.*;
import com.mindhub.homeBanking.repositories.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


@SpringBootApplication
public class HomeBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeBankingApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(ClientRepository repositoryClient, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository){
		return args -> {
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

			Transaction transaction1 = new Transaction(TransactionType.DEBIT, -400.00, "Shopping", LocalDateTime.now());
			Transaction transaction2 = new Transaction(TransactionType.CREDIT, 5400.00, "Loading", LocalDateTime.now());
			Transaction transaction3 = new Transaction(TransactionType.DEBIT, -3400.00, "Tax Fee", LocalDateTime.now());
			Transaction transaction4 = new Transaction(TransactionType.CREDIT, 3250.00, "Loading", LocalDateTime.now());
			account1.addTransactions(transaction1);
			account1.addTransactions(transaction2);
			account2.addTransactions(transaction4);
			account3.addTransactions(transaction3);

			transactionRepository.save(transaction1);
			transactionRepository.save(transaction2);
			transactionRepository.save(transaction3);
			transactionRepository.save(transaction4);

			Set<Integer> mortgage = Set.of(12,24,36,48,60);
			Set<Integer> personal = Set.of(6,12,24);
			Set<Integer> automotive = Set.of(12,24,36);
			Loan loan1 = new Loan("Mortgage", mortgage, 500000.0);
			Loan loan2 = new Loan("Personal", personal , 100000.0);
			Loan loan3 = new Loan( "Automotive", automotive, 300000.0);
			loanRepository.save(loan1);
			loanRepository.save(loan2);
			loanRepository.save(loan3);
			ClientLoan clientLoan1 = new ClientLoan(loan1,48 , 400000.0);
			ClientLoan clientLoan2 = new ClientLoan(loan2,12 , 50000.0);
			ClientLoan clientLoan3 = new ClientLoan(loan3, 36, 200000.0);

			clientLoanRepository.save(clientLoan1);
			clientLoanRepository.save(clientLoan2);
			clientLoanRepository.save(clientLoan3);


			Card card = new Card(CardType.DEBIT, CardColorType.GOLD, 5366501119761273L, LocalDate.now(), LocalDate.now().plusYears(5),client1.getFirstName()+" "+client1.getLastName(), 945);
			cardRepository.save(card);
		};


	}
}


