package com.tradebrite.bank_accounts.converter;

import com.tradebrite.bank_accounts.dto.CustomerDto;
import com.tradebrite.bank_accounts.model.Customer;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoToCustomer implements Converter<CustomerDto, Customer> {

    @Synchronized
    @Nullable
    @Override
    public Customer convert(CustomerDto source) {
        if (source == null) return null;
        final Customer customer = new Customer();
        customer.setName(source.getName());
        customer.setEmailAddress(source.getEmailAddress());
        customer.setPhoneNumber(source.getPhoneNumber());
        return customer;
    }
}
