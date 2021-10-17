package com.tradebrite.bank_accounts.converter;

import com.tradebrite.bank_accounts.dto.AccountDto;
import com.tradebrite.bank_accounts.model.Account;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoToAccount implements Converter<AccountDto, Account> {

    private final CustomerDtoToCustomer customerDtoToCustomer;

    public AccountDtoToAccount(CustomerDtoToCustomer customerDtoToCustomer) {
        this.customerDtoToCustomer = customerDtoToCustomer;
    }

    @Synchronized
    @Nullable
    @Override
    public Account convert(AccountDto source) {
        if (source == null) return null;
        final Account account = new Account();
        account.setNumber(source.getNumber());
        account.setBalance(source.getBalance());
        if (source.getCustomers() != null && source.getCustomers().size() > 0) {
            source.getCustomers().forEach(owner -> account.getOwners().add(customerDtoToCustomer.convert(owner)));
        }
        return account;
    }
}
