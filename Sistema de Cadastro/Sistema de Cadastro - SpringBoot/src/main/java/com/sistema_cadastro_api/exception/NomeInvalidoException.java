package com.sistema_cadastro_api.exception;

public class NomeInvalidoException extends RuntimeException {
    public NomeInvalidoException(String mensagem) {
        super(mensagem);
    }
}
