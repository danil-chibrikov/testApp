package com.webproject.bankapp.repositories;

import com.webproject.bankapp.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByAccountIdentifier(String accountId);

    @Override
    Iterable<Account> findAll();
}
