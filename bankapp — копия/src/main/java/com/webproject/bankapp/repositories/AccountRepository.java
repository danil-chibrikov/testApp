package com.webproject.bankapp.repositories;

import com.webproject.bankapp.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    List<Account> findByCardNumber(String id);

    Account findByAccountSequence(String sequence);
}
