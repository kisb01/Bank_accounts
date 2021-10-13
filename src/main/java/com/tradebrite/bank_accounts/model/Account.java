package com.tradebrite.bank_accounts.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private Double balance;

    @ManyToMany (mappedBy = "accounts")
    private List<Customer> owners = new ArrayList<>();

}
