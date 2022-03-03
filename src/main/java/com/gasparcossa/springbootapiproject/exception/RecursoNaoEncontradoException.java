package com.gasparcossa.springbootapiproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import lombok.Data;

/**
 * Classe que customiza mensagens de excessoes
 */
@Data
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecursoNaoEncontradoException extends RuntimeException {

    private static final long serialVersionUID = 1l;
    private String nomeDoRecurso;
    private String nomeDoCampo;
    private Object valorDoCampo;

    public RecursoNaoEncontradoException(String nomeDoRecurso, String nomeDoCampo, Object valorDoCampo) {
        super(String.format("%s com %s: %s nao foi encontrado", nomeDoRecurso, nomeDoCampo, valorDoCampo));
        this.nomeDoRecurso = nomeDoRecurso;
        this.nomeDoCampo = nomeDoCampo;
        this.valorDoCampo = valorDoCampo;
    }
    public RecursoNaoEncontradoException(){}
}