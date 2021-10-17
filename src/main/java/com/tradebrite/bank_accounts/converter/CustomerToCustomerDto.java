package com.tradebrite.bank_accounts.converter;

import com.tradebrite.bank_accounts.dto.CustomerDto;
import com.tradebrite.bank_accounts.model.Customer;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CustomerToCustomerDto implements Converter<Customer, CustomerDto> {

    @Synchronized
    @Nullable
    @Override
    public CustomerDto convert(Customer source) {
        if (source == null) return null;
        final CustomerDto customerDto = new CustomerDto();
        customerDto.setName(source.getName());
        customerDto.setEmailAddress(source.getEmailAddress());
        customerDto.setPhoneNumber(source.getPhoneNumber());
        return customerDto;
    }
}
