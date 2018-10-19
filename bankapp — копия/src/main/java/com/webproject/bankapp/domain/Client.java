package com.webproject.bankapp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_client;
    @NotBlank(message = "Client fullname is required")
    private String fullname;
    @NotBlank(message = "Client address is required. Please use this form(City, street, number of building)")
    private String address;
    @NotBlank(message = "Client credit card number is required")
    @Size(min=16, max=16, message = "Please use 16 characters. Only numeral")
    @Column(unique = true)
    private String creditCardNumber;
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    @Column(updatable = false)
    private Date created_At;
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private Date updated_At;

    //@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "client")

    //private Account account;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "client")
    private List<Account> accounts = new ArrayList<>();

    public Client() {
    }

    public Long getId_client() {
        return id_client;
    }

    public void setId_client(Long id_client) {
        this.id_client = id_client;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    public Date getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(Date updated_At) {
        this.updated_At = updated_At;
    }

//    public Account getAccount() {
//        return account;
//    }
//
//    public void setAccount(Account account) {
//        this.account = account;
//    }

    @PrePersist
    protected void OnCreate(){
        this.created_At = new Date();
    }

    @PreUpdate
    protected void OnUpdate(){
        this.updated_At = new Date();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
