package com.webproject.bankapp.services;

import com.webproject.bankapp.domain.Account;
import com.webproject.bankapp.domain.Client;
import com.webproject.bankapp.repositories.AccountRepository;
import com.webproject.bankapp.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Account addAccount(String accountIdentifier, Account account) {
        Client client = clientRepository.findByCreditCardNumber(accountIdentifier);
        account.setClient(client);
        account.setCreditCardNumber(accountIdentifier);

        return accountRepository.save(account);
    }
}
