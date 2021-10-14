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

    @GetMapping("/{id}/deposit")
    public void deposit(@PathVariable("id") String id, @RequestParam("amount") String amount) {
        if (StringToLong.convert(id) == null || StringToLong.convert(amount) == null) {
            throw new RuntimeException("Must enter a valid number");
        } else {
            accountService.deposit(StringToLong.convert(id), StringToLong.convert(amount));
        }
    }

    @GetMapping("/{id}/withdraw")
    public void withdraw(@PathVariable("id") String id, @RequestParam("amount") String amount) {
        if (StringToLong.convert(id) == null || StringToLong.convert(amount) == null) {
            throw new RuntimeException("Must enter a valid number");
        } else {
            accountService.withdraw(StringToLong.convert(id), StringToLong.convert(amount));
        }
    }

    @GetMapping("/{senderId}/transfer/{receiverId}")
    public void transfer(@PathVariable("senderId") String senderId, @PathVariable("receiverId") String receiverId, @RequestParam("amount") String amount) {
        if (StringToLong.convert(senderId) == null || StringToLong.convert(amount) == null || StringToLong.convert(receiverId) == null) {
            throw new RuntimeException("Must enter a valid number");
        } else {
            accountService.transfer(StringToLong.convert(senderId), StringToLong.convert(receiverId), StringToLong.convert(amount));
        }
    }
}
