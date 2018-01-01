package br.com.studo.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudoException extends RuntimeException {

    public StudoException(String var) {
        super(var);
    }

}
