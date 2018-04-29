package br.com.studo.exception;

import br.com.studo.service.LogErroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

@Slf4j
@ControllerAdvice
public class ExceprionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private LogErroService logErroService;

    @ExceptionHandler({StudoException.class})
    public ResponseEntity<Object> exceprionHandler(StudoException exception, WebRequest request) {
        String mensagem = exception.getMessage();
        List<String> erros = Arrays.asList(mensagem);
        logErroService.salvarLog(getStackTrace(exception));
        return handleExceptionInternal(exception, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({Exception.class})
    public void errorInterceptor(Exception exception) {
        logErroService.salvarLog(getStackTrace(exception));
        log.error("ERRO: ", exception);
    }

    private String getStackTrace(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
