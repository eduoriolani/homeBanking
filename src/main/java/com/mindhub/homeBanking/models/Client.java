package com.mindhub.homeBanking.models;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
@Entity


public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")


    private long id;
    private String firstName;
    private String lastName;
    private String email;

    public Client(){}
    public Client(String first, String last, String contact){
        this.firstName = first;
        this.lastName = last;
        this.email = contact;
    }

    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String toString(){
        return firstName + " " + lastName;
    }

    public interface ClientRepository extends JpaRepository<Client, Long> {
        List<Client> findByLastName(String lastName);
    }
}
