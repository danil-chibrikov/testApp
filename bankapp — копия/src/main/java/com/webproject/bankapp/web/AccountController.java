package com.webproject.bankapp.web;

import com.webproject.bankapp.domain.Account;
import com.webproject.bankapp.services.AccountService;
import com.webproject.bankapp.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bank/account")
@CrossOrigin
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/{account_id}")
    public ResponseEntity<?> addAccountToClient(@Valid @RequestBody Account account,
                                               BindingResult bindingResult, @PathVariable String account_id) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(bindingResult);
        if (errorMap != null) return errorMap;

        Account account1 = accountService.addAccount(account_id, account);

        return new ResponseEntity<Account>(account1, HttpStatus.CREATED);
    }
}
