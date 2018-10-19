package com.webproject.bankapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_backlog;
    private Integer accountsCount = 0;
    private String cardNumber;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id",nullable = false)
    @JsonIgnore
    private Client client;

    //OneToMany accounts
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "backlog", orphanRemoval = true)
    private List<Account> accountList = new ArrayList<>();

    public Backlog() {
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getId_backlog() {
        return id_backlog;
    }

    public void setId_backlog(Long id_backlog) {
        this.id_backlog = id_backlog;
    }

    public Integer getAccountsCount() {
        return accountsCount;
    }

    public void setAccountsCount(Integer accountsCount) {
        this.accountsCount = accountsCount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}