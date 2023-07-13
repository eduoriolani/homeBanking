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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootApplication
public class HomeBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeBankingApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner initData(ClientService clientService, AccountService accountService, TransactionService transactionService, LoanService loanService, ClientLoanRepository clientLoanRepository, CardService cardService){
//		return (args -> {
//
//			Client client1 = new Client("Melba", "Morel", "melba@mindhub.com", passwordEncoder.encode("1234"));
//			Client client2 = new Client( "Edu", "Oriolani", "edu.oriolani@gmail.com", passwordEncoder.encode("1234"));
//			Client admin = new Client ("admin", "admin", "admin@admin.com", passwordEncoder.encode("1234"));
//			clientService.save(client1);
//			clientService.save(client2);
//			clientService.save(admin);
//
//			Account account1 = new Account("VIN001", LocalDate.now(), 5000.0);
//			Account account2 = new Account( "VIN002", LocalDate.now().plusDays(1), 7500.0);
//			Account account3 = new Account("VIN003", LocalDate.now(), 24000.0);
//			client1.addAccounts(account1);
//			client1.addAccounts(account2);
//			client2.addAccounts(account3);
//			accountService.save(account1);
//			accountService.save(account2);
//			accountService.save(account3);
//
//			Transaction transaction1 = new Transaction(TransactionType.DEBIT, -400.00, "Shopping", LocalDateTime.now());
//			Transaction transaction2 = new Transaction(TransactionType.CREDIT, 5400.00, "Loading", LocalDateTime.now());
//			Transaction transaction3 = new Transaction(TransactionType.DEBIT, -3400.00, "Tax Fee", LocalDateTime.now());
//			Transaction transaction4 = new Transaction(TransactionType.CREDIT, 3250.00, "Loading", LocalDateTime.now());
//			account1.addTransactions(transaction1);
//			account1.addTransactions(transaction2);
//			account2.addTransactions(transaction4);
//			account3.addTransactions(transaction3);
//
//			transactionService.save(transaction1);
//			transactionService.save(transaction2);
//			transactionService.save(transaction3);
//			transactionService.save(transaction4);
//
//			List<Integer> mortgage = List.of(12,24,36,48,60);
//			List<Integer> personal = List.of(6,12,24);
//			List<Integer> automotive = List.of(12,24,36);
//			Loan loan1 = new Loan("Mortgage", mortgage, 500000.0);
//			Loan loan2 = new Loan("Personal", personal , 100000.0);
//			Loan loan3 = new Loan( "Automotive", automotive, 300000.0);
//			loanService.save(loan1);
//			loanService.save(loan2);
//			loanService.save(loan3);
//
//			ClientLoan clientLoan1 = new ClientLoan( 48 , 400000.0);
//			ClientLoan clientLoan2 = new ClientLoan( 12 , 50000.0);
//			ClientLoan clientLoan3 = new ClientLoan( 24, 100000.0);
//			ClientLoan clientLoan4 = new ClientLoan( 36, 200000.0);
//
//			client1.addClientLoan(clientLoan1);
//			loan1.addClientLoan(clientLoan1);
//			clientLoanRepository.save(clientLoan1);
//			client1.addClientLoan(clientLoan2);
//			loan2.addClientLoan(clientLoan2);
//			clientLoanRepository.save(clientLoan2);
//
//			client2.addClientLoan(clientLoan3);
//			loan2.addClientLoan(clientLoan3);
//			clientLoanRepository.save(clientLoan3);
//			client2.addClientLoan(clientLoan4);
//			loan3.addClientLoan(clientLoan4);
//			clientLoanRepository.save(clientLoan4);
//
//			Card card1 = new Card(client1.getFirstName()+" "+client1.getLastName(), CardType.DEBIT, CardColor.GOLD, "4517-5697-4524-5421", 954, LocalDateTime.now(), LocalDateTime.now().plusYears(5));
//			Card card2 = new Card(client1.getFirstName()+" "+client1.getLastName(), CardType.CREDIT, CardColor.TITANIUM, "7517-2697-4534-2462", 414, LocalDateTime.now(), LocalDateTime.now().plusYears(5));
//			Card card3 = new Card(client1.getFirstName()+" "+client1.getLastName(), CardType.DEBIT, CardColor.SILVER, "1354-2642-8543-2752", 264, LocalDateTime.now(), LocalDateTime.now().plusYears(4));
//			client1.addCard(card1);
//			client1.addCard(card2);
//			client1.addCard(card3);
//			cardService.save(card1);
//			cardService.save(card2);
//			cardService.save(card3);
//

}


