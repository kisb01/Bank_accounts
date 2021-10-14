package com.tradebrite.bank_accounts.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
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
    private String number;
    private Long balance;

    @ManyToMany(mappedBy = "accounts")
    private List<Customer> owners = new ArrayList<>();

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
