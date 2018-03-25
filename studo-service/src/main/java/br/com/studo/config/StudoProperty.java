package br.com.studo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "studo")
public class StudoProperty {

    private String originPermitida;

    private boolean enviarEmail;

    private final Seguranca seguranca = new Seguranca();

    public static class Seguranca {

        @Setter
        @Getter
        private boolean enableHttps;
    }
}
