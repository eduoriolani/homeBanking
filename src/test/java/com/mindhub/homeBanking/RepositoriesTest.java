package com.mindhub.homeBanking;

import com.mindhub.homeBanking.enums.TransactionType;
import com.mindhub.homeBanking.models.*;
import com.mindhub.homeBanking.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = NONE)
public class RepositoriesTest {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    LoanRepository loanRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @Test
    public void existLoans() {
        List<Loan> loans = loanRepository.findAll();
        assertThat(loans, is(not(empty())));
    }
    @Test
    public void existMortgageLoan(){
        List<Loan> loans = loanRepository.findAll();
        assertThat(loans, hasItem(hasProperty("name", is("Mortgage"))));
    }

    @Test
    public void existClients(){
        List<Client> clients = clientRepository.findAll();
        assertThat(clients, is(not(empty())));
    }
    @Test
    public void existAdmin(){
        List<Client> clients = clientRepository.findAll();
        assertThat(clients, hasItem(hasProperty("email", containsString("admin"))));
    }

    @Test
    public void existAccounts(){
        List<Account> accounts = accountRepository.findAll();
        assertThat(accounts, is(not(empty())));
    }
    @Test
    public void hasDate(){
        List<Account> accounts = accountRepository.findAll();
        assertThat(accounts, hasItem(hasProperty("date")));
    }

    @Test
    public void existCards(){
        List<Card> cards = cardRepository.findAll();
        assertThat(cards, is(not(empty())));
    }
    @Test
    public void existsCvv(){
        List<Card> cards = cardRepository.findAll();
        assertThat(cards, hasItem(hasProperty("cvv")));
    }

    @Test
    public void existTransactions(){
        List<Transaction> transactions = transactionRepository.findAll();
        assertThat(transactions, is(not(empty())));
    }
    @Test
    public void existDebitTransaction() {
        List<Transaction> transactions = transactionRepository.findAll();
        boolean debitTransactionExists = transactions.stream()
                .anyMatch(transaction -> transaction.getType() == TransactionType.DEBIT);
        assertTrue(debitTransactionExists);
    }

}

