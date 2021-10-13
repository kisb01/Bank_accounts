package com.tradebrite.bank_accounts.controller;

import com.tradebrite.bank_accounts.service.AccountService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


}
