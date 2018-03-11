package br.com.studo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class ExceprionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ StudoException.class })
	public ResponseEntity<Object> exceprionHandler(StudoException ex, WebRequest request) {
		String mensagem = ex.getMessage();
		List<String> erros = Arrays.asList(mensagem);
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
}
