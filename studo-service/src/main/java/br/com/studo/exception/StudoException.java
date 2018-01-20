package br.com.studo.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudoException extends RuntimeException {

	private static final long serialVersionUID = 3661546451490206892L;

	public StudoException(String var) {
        super(var);
    }

}
