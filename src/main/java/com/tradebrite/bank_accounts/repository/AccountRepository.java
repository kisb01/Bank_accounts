package com.tradebrite.bank_accounts.repository;

import com.tradebrite.bank_accounts.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
