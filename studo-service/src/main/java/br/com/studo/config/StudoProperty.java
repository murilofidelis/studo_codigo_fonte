package br.com.studo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "studo")
public class StudoProperty {

    private boolean enviarEmail;

    private final Seguranca seguranca = new Seguranca();

    @Setter
    @Getter
    public static class Seguranca {

        private String originPermitida;

        private boolean enableHttps;

        private String studoCliente;

        private String secret;

        private String[] scopes;

        private int accessTokenValiditySeconds;

        private int refreshTokenValiditySeconds;

        private String[] authorizedGrantTypes;

    }
}
