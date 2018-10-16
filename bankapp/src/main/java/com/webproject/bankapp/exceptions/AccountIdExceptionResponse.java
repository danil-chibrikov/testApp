package com.webproject.bankapp.exceptions;

public class AccountIdExceptionResponse {

    private String accountIdentifier;

    public AccountIdExceptionResponse(String accountIdentifier) {
        this.accountIdentifier = accountIdentifier;
    }

    public String getAccountIdentifier() {
        return accountIdentifier;
    }

    public void setAccountIdentifier(String accountIdentifier) {
        this.accountIdentifier = accountIdentifier;
    }
}
