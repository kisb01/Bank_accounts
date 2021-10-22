package com.tradebrite.bank_accounts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Slf4j
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "([0-9]{4}[-]*[0-9]{4})")
    private String number;
    private Long balance;

    @ManyToMany(mappedBy = "accounts")
    private List<Customer> owners = new ArrayList<>();

    public Account() {
    }

    public Account(Long id, String number, Long balance) {
        this.id = id;
        this.number = number;
        this.balance = balance;
    }

    public void deposit(Long amount) {
        if (amount <= 0) {
            throw new RuntimeException("Enter valid amount to deposit!");
        }
        this.balance += amount;
        log.info("The new balance of account " + this.number + " is " + this.balance);
    }

    public void withdraw(Long amount) {
        if (this.balance - amount < 0 || amount <= 0) {
            throw new RuntimeException("Enter valid amount to withdraw!");
        }
        this.balance -= amount;
        log.info("The new balance of account " + this.number + " is " + this.balance);
    }

    public void transferToAccount(Account account, Long amount) {
        withdraw(amount);
        account.deposit(amount);
    }

}
