package com.webproject.bankapp.exceptions;

public class ClientIdExceptionResponse {

    private String clientPhoneNumber;

    public ClientIdExceptionResponse(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }
}
