package com.mindhub.homeBanking;

import com.mindhub.homeBanking.enums.CardColor;
import com.mindhub.homeBanking.enums.CardType;
import com.mindhub.homeBanking.enums.TransactionType;
import com.mindhub.homeBanking.models.*;
import com.mindhub.homeBanking.repositories.*;
import com.mindhub.homeBanking.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootApplication
@EnableWebSecurity
public class HomeBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeBankingApplication.class, args);
	}


	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository, @Autowired PasswordEncoder passwordEncoder) {
		return (args -> {

			Client client1 = new Client("Melba", "Morel", "melba@mindhub.com", passwordEncoder.encode("1234"));
			Client client2 = new Client("Edu", "Oriolani", "edu.oriolani@gmail.com", passwordEncoder.encode("1234"));
			Client admin = new Client("admin", "admin", "admin@admin.com", passwordEncoder.encode("1234"));
			clientRepository.save(client1);
			clientRepository.save(client2);
			clientRepository.save(admin);

			Account account1 = new Account("VIN001", LocalDate.now(), 5000.0,true, "Saving");
			Account account2 = new Account("VIN002", LocalDate.now().plusDays(1), 7500.0,true, "Checking");
			Account account3 = new Account("VIN003", LocalDate.now(), 24000.0,true, "Saving");
			client1.addAccounts(account1);
			client1.addAccounts(account2);
			client2.addAccounts(account3);
			accountRepository.save(account1);
			accountRepository.save(account2);
			accountRepository.save(account3);

			Transaction transaction1 = new Transaction(TransactionType.DEBIT, -400.00, account1.getBalance() ,"Shopping", LocalDateTime.now());
			Transaction transaction2 = new Transaction(TransactionType.CREDIT, 5400.00, account1.getBalance(),"Loading", LocalDateTime.now());
			Transaction transaction3 = new Transaction(TransactionType.DEBIT, -3400.00, account3.getBalance(), "Tax Fee", LocalDateTime.now());
			Transaction transaction4 = new Transaction(TransactionType.CREDIT, 3250.00, account2.getBalance(), "Loading", LocalDateTime.now());
			account1.addTransactions(transaction1);
			account1.addTransactions(transaction2);
			account2.addTransactions(transaction4);
			account3.addTransactions(transaction3);

			transactionRepository.save(transaction1);
			transactionRepository.save(transaction2);
			transactionRepository.save(transaction3);
			transactionRepository.save(transaction4);

			List<Integer> mortgage = List.of(12, 24, 36, 48, 60);
			List<Integer> personal = List.of(6, 12, 24);
			List<Integer> automotive = List.of(12, 24, 36);
			Loan loan1 = new Loan("Mortgage", mortgage, 500000.0, 16);
			Loan loan2 = new Loan("Personal", personal, 100000.0, 20);
			Loan loan3 = new Loan("Automotive", automotive, 300000.0, 18);
			loanRepository.save(loan1);
			loanRepository.save(loan2);
			loanRepository.save(loan3);


			Card card1 = new Card(client1.getFirstName() + " " + client1.getLastName(), CardType.DEBIT, CardColor.GOLD, "4517-5697-4524-5421", "954", true, LocalDateTime.now().minusMonths(1), LocalDateTime.now().minusDays(2));
			Card card2 = new Card(client1.getFirstName() + " " + client1.getLastName(), CardType.CREDIT, CardColor.TITANIUM, "7517-2697-4534-2462", "414",true, LocalDateTime.now(), LocalDateTime.now().plusDays(2));
			Card card3 = new Card(client1.getFirstName() + " " + client1.getLastName(), CardType.DEBIT, CardColor.SILVER, "1354-2642-8543-2752", "264", true, LocalDateTime.now(), LocalDateTime.now().plusYears(4));
			client1.addCard(card1);
			client1.addCard(card2);
			client1.addCard(card3);
			cardRepository.save(card1);
			cardRepository.save(card2);
			cardRepository.save(card3);

		});
	}
}
