package com.webproject.bankapp.services;

import com.webproject.bankapp.domain.Account;
import com.webproject.bankapp.exceptions.AccountIdException;
import com.webproject.bankapp.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account saveOrUpdateAccount(Account account) {
        try {
            account.setAccountIdentifier(account.getAccountIdentifier().toUpperCase());
            return accountRepository.save(account);
        }catch (Exception e) {
            throw new AccountIdException("Account ID '" + account.getAccountIdentifier().toUpperCase() + "' already exists");
        }
    }

    public Account findAccountByIdentifier(String accountId) {
        Account account = accountRepository.findByAccountIdentifier(accountId.toUpperCase());

        if(account == null) {
            throw new AccountIdException("Account ID '" + accountId + "' does not exists");
        }
        return account;
    }

    public Iterable<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    public void deleteAccountByIdentifier(String accountId) {
        Account account = accountRepository.findByAccountIdentifier(accountId.toUpperCase());

        if(account == null) {
            throw new AccountIdException("Cannot Account with ID '" + accountId + "'. This account does not exist");
        }
        accountRepository.delete(account);
    }
}
