package com.tradebrite.bank_accounts.service;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerService customerService;

    public CustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

}
