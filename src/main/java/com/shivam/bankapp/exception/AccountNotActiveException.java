package com.shivam.bankapp.exception;

public class AccountNotActiveException extends RuntimeException{
    public AccountNotActiveException(String message) {
        super(message);
    }
}
