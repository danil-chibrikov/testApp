package com.webproject.bankapp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_account;
    @Size(min=16, max=16, message = "Please use 16 characters. Only numeral")
    @Column(unique = true, updatable = false)
    private String creditCardNumber;
    private Integer count = 0;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date created_At;

    /*//OnetoOne with client
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    private Client client;
    //One to Many accounts*/

    //ManyToOne with client
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "client_id", updatable = false, nullable = false)
    @JsonIgnore
    private Client client;


    public Account() {
    }

    public Long getId_account() {
        return id_account;
    }

    public void setId_account(Long id_account) {
        this.id_account = id_account;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @PrePersist
    protected void OnCreate(){
        this.created_At = new Date();
    }
}
