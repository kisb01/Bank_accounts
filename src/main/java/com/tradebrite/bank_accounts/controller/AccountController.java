package com.tradebrite.bank_accounts.controller;

import com.tradebrite.bank_accounts.converter.StringToLong;
import com.tradebrite.bank_accounts.model.Account;
import com.tradebrite.bank_accounts.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Account findById(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new RuntimeException("Must enter a valid number");
        } else {
            return accountService.findById(StringToLong.convert(id));
        }
    }

    @PostMapping
    public Account save(@RequestBody Account account) {
        return accountService.save(account);
    }

    @PutMapping("/{id}")
    public Account update(@RequestBody Account account, @PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new RuntimeException("Must enter a valid number");
        } else {
            account.setId(StringToLong.convert(id));
        }
        return accountService.save(account);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new RuntimeException("Must enter a valid number");
        } else {
            accountService.deleteById(StringToLong.convert(id));
        }
    }
}
