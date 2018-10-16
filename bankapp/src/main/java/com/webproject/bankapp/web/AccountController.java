package com.webproject.bankapp.web;

import com.webproject.bankapp.domain.Account;
import com.webproject.bankapp.services.AccountService;
import com.webproject.bankapp.services.MapValidationErrorService;
import org.hibernate.validator.internal.engine.messageinterpolation.InterpolationTermType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bank/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewAccount(@Valid @RequestBody Account account, BindingResult bindingResult) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(bindingResult);
        if(errorMap != null) return errorMap;

        Account ac = accountService.saveOrUpdateAccount(account);
        return new ResponseEntity<Account>(ac, HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable String accountId) {

        Account account = accountService.findAccountByIdentifier(accountId);

        return new ResponseEntity<Account>(account, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Account> getAllAccounts() {
        return accountService.findAllAccounts();
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable String accountId) {
        accountService.deleteAccountByIdentifier(accountId);

        return new ResponseEntity<String>("Account with ID: '" + accountId + "' was deleted", HttpStatus.OK);
    }
}
