package com.webproject.bankapp.exceptions;

public class ClientIdExceptionResponse {

    private String creditCardNumber;

    public ClientIdExceptionResponse(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
}
