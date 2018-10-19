package com.webproject.bankapp.services;

import com.webproject.bankapp.domain.Account;
import com.webproject.bankapp.domain.Backlog;
import com.webproject.bankapp.domain.Client;
import com.webproject.bankapp.exceptions.ClientNotFoundException;
import com.webproject.bankapp.repositories.AccountRepository;
import com.webproject.bankapp.repositories.BacklogRepository;
import com.webproject.bankapp.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    public Account addAccount(String accountIdentifier, Account account){
        try {
            Backlog backlog = backlogRepository.findByCardNumber(accountIdentifier);
            account.setBacklog(backlog);
            Integer accountsCount = backlog.getAccountsCount();
            accountsCount++;
            backlog.setAccountsCount(accountsCount);
            account.setAccountSequence(backlog.getCardNumber() + "-" + accountsCount);
            account.setCardNumber(accountIdentifier);

            return accountRepository.save(account);
        }catch (Exception e) {
            throw new ClientNotFoundException("Client not found");
        }
    }

    public Iterable<Account>findBacklogById(String id){
        Client client = clientRepository.findByCardNumber(id);
        if(client == null) {
            throw new ClientNotFoundException("Client with card number: '" + id + "' does not exist");
        }

        return accountRepository.findByCardNumber(id);
    }

    public Account findAccountByAccountSequence(String backlog_id, String account_id){
        Backlog backlog = backlogRepository.findByCardNumber(backlog_id);
        if(backlog == null) {
            throw new ClientNotFoundException("Client with card number: '" + backlog_id + "' does not exist");
        }
        Account account = accountRepository.findByAccountSequence(account_id);
        if(account == null) {
            throw new ClientNotFoundException("Client account '" + account_id + "' not found");
        }
        if(!account.getCardNumber().equals(backlog_id)) {
            throw new ClientNotFoundException("Client account '" + account_id +
                    "' does not exist in client: '" + backlog_id);
        }
        return account;
    }

    public Account updateByAccountSequence(Account updatedAccount, String backlog_id, String account_id){
        Account account = findAccountByAccountSequence(backlog_id, account_id);
        account = updatedAccount;

        return accountRepository.save(account);
    }

    public void deleteAccountByClientSequence(String backlog_id, String account_id){
        Account account = findAccountByAccountSequence(backlog_id, account_id);

        accountRepository.delete(account);
    }
}
