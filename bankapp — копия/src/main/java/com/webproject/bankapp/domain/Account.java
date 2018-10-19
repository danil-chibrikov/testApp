package com.webproject.bankapp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false, unique = true)
    private String accountSequence;
    @NotBlank(message = "Please deposit money")
    private String count;
    private String type;

    //ManyToOne with Backlog
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="backlog_id", updatable = false, nullable = false)
    @JsonIgnore
    private Backlog backlog;

    @Column(updatable = false)
    private String cardNumber;
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private Date created_At;
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private Date updated_At;

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountSequence() {
        return accountSequence;
    }

    public void setAccountSequence(String accountSequence) {
        this.accountSequence = accountSequence;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    public Backlog getBacklog() {
        return backlog;
    }

    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
    }

    public Date getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(Date updated_At) {
        this.updated_At = updated_At;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @PrePersist
    protected void OnCreate(){
        this.created_At = new Date();
    }

    @PreUpdate
    protected void OnUpdate(){
        this.updated_At = new Date();
    }
    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountSequence='" + accountSequence + '\'' +
                ", count='" + count + '\'' +
                ", backlog=" + backlog +
                ", cardNumber='" + cardNumber + '\'' +
                ", create_At=" + created_At +
                '}';
    }
}
