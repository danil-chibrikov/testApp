package com.webproject.bankapp.web;

import com.webproject.bankapp.domain.Account;
import com.webproject.bankapp.services.AccountService;
import com.webproject.bankapp.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bank/backlog")
@CrossOrigin
public class BacklogController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addAccountsToBacklog(@Valid @RequestBody Account account,
                                                  BindingResult bindingResult, @PathVariable String backlog_id){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(bindingResult);
        if (errorMap != null) return errorMap;
        Account account1 = accountService.addAccount(backlog_id, account);
        return new ResponseEntity<Account>(account1, HttpStatus.CREATED);
    }

    @GetMapping("/{backlog_id}")
    public Iterable<Account> getClientBacklog(@PathVariable String backlog_id){
        return accountService.findBacklogById(backlog_id);
    }

    @GetMapping("/{backlog_id}/{account_id}")
    public ResponseEntity<?> getAccount(@PathVariable String backlog_id, @PathVariable String account_id){
        Account account = accountService.findAccountByAccountSequence(backlog_id, account_id);
        return new ResponseEntity<Account>( account, HttpStatus.OK);
    }

    @PatchMapping("/{backlog_id}/{account_id}")
    public ResponseEntity<?> updateAccount(@Valid @RequestBody Account account, BindingResult bindingResult,
                                               @PathVariable String backlog_id, @PathVariable String account_id ){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(bindingResult);
        if (errorMap != null) return errorMap;
        Account updatedAccount = accountService.updateByAccountSequence(account, backlog_id, account_id);
        return new ResponseEntity<Account>(updatedAccount, HttpStatus.OK);
    }

    @DeleteMapping("/{backlog_id}/{account_id}")
    public ResponseEntity<?> deleteAccount(@PathVariable String backlog_id, @PathVariable String account_id){
        accountService.deleteAccountByClientSequence(backlog_id, account_id);
        return new ResponseEntity<String>("Account " + account_id + " was deleted successfully", HttpStatus.OK);
    }
}
