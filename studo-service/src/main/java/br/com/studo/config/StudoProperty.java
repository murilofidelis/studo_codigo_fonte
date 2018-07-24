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

    private final Auditoria auditoria = new Auditoria();

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

    @Setter
    @Getter
    public static class Auditoria{

        private String user;

        private  String pass;

        private String userPostgres;

        private String passPostgres;

        private String urlDataBase;

        private String schema;

    }
}
