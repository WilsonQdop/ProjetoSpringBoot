package com.Wilson.ListaTarefa.exception;

public class UserExistsException extends RuntimeException {
    public UserExistsException(String mensagem) {
        super(mensagem);
    }
}
