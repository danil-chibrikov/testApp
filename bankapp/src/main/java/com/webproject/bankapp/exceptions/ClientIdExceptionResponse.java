package com.webproject.bankapp.exceptions;

public class ClientIdExceptionResponse {

    private String cardNumber;

    public ClientIdExceptionResponse(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
