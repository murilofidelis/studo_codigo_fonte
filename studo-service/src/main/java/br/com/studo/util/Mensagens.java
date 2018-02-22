package br.com.studo.util;

import br.com.studo.exception.StudoException;

import java.io.IOException;
import java.util.Properties;

public enum Mensagens {

    MSG007,
    MSG041;

    Properties properties = new Properties();

    Mensagens() {
        try {
            properties.load(Mensagens.class.getResourceAsStream("/messages.properties"));
        } catch (IOException e) {
            throw new StudoException("Ocorreu um erro ao ler aquivo de mensagens");
        }
    }

    public String getValue() {
        return properties.getProperty(this.name());
    }
}
