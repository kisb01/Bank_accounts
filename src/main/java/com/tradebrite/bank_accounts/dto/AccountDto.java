package com.tradebrite.bank_accounts.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
public class AccountDto {

    @Pattern(regexp = "([0-9]{4}[-]*[0-9]{4})")
    private String number;
    private Long balance;
    private List<CustomerDto> customers;
}
