package com.tradebrite.bank_accounts.repository;

import com.tradebrite.bank_accounts.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
