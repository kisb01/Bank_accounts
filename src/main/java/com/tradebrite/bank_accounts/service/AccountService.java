package com.tradebrite.bank_accounts.service;

import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private AccountService accountService;

    public AccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
