package com.train.crypto.model;

import com.train.crypto.config.CryptoConverter;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = CryptoConverter.class)
    private String userDocument;

    @Convert(converter = CryptoConverter.class)
    private String creditCardToken;

    private Long value;

    public User () {}

    public User(Long id, String userDocument, String creditCardToken, Long value) {
        this.id = id;
        this.userDocument = userDocument;
        this.creditCardToken = creditCardToken;
        this.value = value;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserDocument() {
        return userDocument;
    }
    public void setUserDocument(String userDocument) {
        this.userDocument = userDocument;
    }
    public String getCreditCardToken() {
        return creditCardToken;
    }
    public void setCreditCardToken(String creditCardToken) {
        this.creditCardToken = creditCardToken;
    }
    public Long getValue() {
        return value;
    }
    public void setValue(Long value) {
        this.value = value;
    }

    
}
