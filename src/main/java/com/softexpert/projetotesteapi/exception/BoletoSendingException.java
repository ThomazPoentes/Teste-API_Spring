package com.softexpert.projetotesteapi.exception;

public class BoletoSendingException extends RuntimeException {

    public BoletoSendingException(String message, int statusCode) {
        super("Error while sending boleto: " + statusCode + ". " + message);
    }
}