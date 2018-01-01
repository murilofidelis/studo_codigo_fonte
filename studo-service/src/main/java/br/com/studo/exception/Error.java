package br.com.studo.exception;

import lombok.Getter;

@Getter
public class Error {

    private String mensagemErro;

    public Error(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }


}
