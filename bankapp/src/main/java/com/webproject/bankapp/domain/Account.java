package com.webproject.bankapp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_account;
    @NotBlank(message ="Account Identifier is required")
    @Size(min=4, max=5, message = "Please use 4 to 5 characters")
    @Column(updatable = false, unique = true)
    private String accountIdentifier;
    private int count;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date created_At;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updated_At;
    @Column(name = "client_id", nullable = false, updatable = false)
    private Long client_id;

    public Account() {
    }

    public Long getId_account() {
        return id_account;
    }

    public void setId_account(Long id_account) {
        this.id_account = id_account;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public String getAccountIdentifier() {
        return accountIdentifier;
    }

    public void setAccountIdentifier(String accountIdentifier) {
        this.accountIdentifier = accountIdentifier;
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

    @PrePersist
    protected void OnCreate(){
        this.created_At = new Date();
    }

    @PreUpdate
    protected void OnUpdate(){
        this.updated_At = new Date();
    }
}
