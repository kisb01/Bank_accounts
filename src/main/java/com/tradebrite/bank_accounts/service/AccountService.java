package com.tradebrite.bank_accounts.service;

import com.tradebrite.bank_accounts.model.Account;
import com.tradebrite.bank_accounts.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        accountRepository.findAll().iterator().forEachRemaining(accounts::add);
        return accounts;
    }

    public Account findById(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isEmpty()) {
            throw new RuntimeException("Account not found");
        } else {
            return optionalAccount.get();
        }
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }


}
