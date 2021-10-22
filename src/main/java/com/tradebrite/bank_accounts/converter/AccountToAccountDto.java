package com.tradebrite.bank_accounts.converter;

import com.tradebrite.bank_accounts.dto.AccountDto;
import com.tradebrite.bank_accounts.model.Account;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AccountToAccountDto implements Converter<Account, AccountDto> {

    private final CustomerToCustomerDto customerToCustomerDto;

    public AccountToAccountDto(CustomerToCustomerDto customerToCustomerDto) {
        this.customerToCustomerDto = customerToCustomerDto;
    }

    @Synchronized
    @Nullable
    @Override
    public AccountDto convert(Account source) {
        if (source == null) return null;
        final AccountDto accountDto = new AccountDto();
        accountDto.setNumber(source.getNumber());
        accountDto.setBalance(source.getBalance());
        if (source.getOwners() != null && source.getOwners().size() > 0) {
            source.getOwners().forEach(customer -> accountDto.getCustomers().add(customer.getId()));
        }
        return accountDto;
    }
}
