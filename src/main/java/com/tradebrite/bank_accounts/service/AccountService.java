package com.tradebrite.bank_accounts.service;

import com.tradebrite.bank_accounts.converter.AccountDtoToAccount;
import com.tradebrite.bank_accounts.converter.AccountToAccountDto;
import com.tradebrite.bank_accounts.dto.AccountDto;
import com.tradebrite.bank_accounts.model.Account;
import com.tradebrite.bank_accounts.repository.AccountRepository;
import com.tradebrite.bank_accounts.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private AccountToAccountDto accountToAccountDto;
    private AccountDtoToAccount accountDtoToAccount;
    private CustomerRepository customerRepository;

    public AccountService(AccountRepository accountRepository, AccountToAccountDto accountToAccountDto, AccountDtoToAccount accountDtoToAccount, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.accountToAccountDto = accountToAccountDto;
        this.accountDtoToAccount = accountDtoToAccount;
        this.customerRepository = customerRepository;
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

    public AccountDto save(AccountDto accountDto) {
        Account account = accountDtoToAccount.convert(accountDto);
        Account savedAccount = accountRepository.save(account);
        return accountToAccountDto.convert(savedAccount);
    }

    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

    @Transactional
    public Long deposit(Long id, Long amount) {
        Account account = findById(id);
        account.deposit(amount);
        AccountDto dto = accountToAccountDto.convert(account);
        return save(dto).getBalance();
    }

    @Transactional
    public Long withdraw(Long id, Long amount) {
        Account account = findById(id);
        account.withdraw(amount);
        AccountDto dto = accountToAccountDto.convert(account);
        return save(dto).getBalance();
    }

    @Transactional
    public void transfer(Long senderId, Long receiverId, Long amount) {
        Account sender = findById(senderId);
        Account receiver = findById(receiverId);
        sender.transferToAccount(receiver, amount);
        accountRepository.saveAll(Arrays.asList(sender, receiver));
    }
}
