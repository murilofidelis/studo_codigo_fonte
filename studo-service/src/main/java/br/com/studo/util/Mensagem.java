package br.com.studo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Component
public class Mensagem {

    @Autowired
    private MessageSource messageSource;

    private MessageSourceAccessor accessor;

    @PostConstruct
    private void init() {
        Locale locale = new Locale("pt_br");
        accessor = new MessageSourceAccessor(messageSource, locale);
    }

    public String get(String msg) {
        return accessor.getMessage(msg);
    }
}
