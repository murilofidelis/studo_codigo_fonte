package br.com.studo.config;

import lombok.extern.slf4j.Slf4j;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.repository.sql.ConnectionProvider;
import org.javers.repository.sql.DialectName;
import org.javers.repository.sql.JaversSqlRepository;
import org.javers.repository.sql.SqlRepositoryBuilder;
import org.javers.spring.auditable.AuthorProvider;
import org.javers.spring.auditable.SpringSecurityAuthorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
@Configuration
public class JaversAuditConfig {

    @Autowired
    private StudoProperty property;

    @Bean
    public AuthorProvider authorProvider() {
        return new SpringSecurityAuthorProvider();
    }

    @Bean
    public ConnectionProvider connectionProvider() {
        return new ConnectionProvider() {
            @Override
            public Connection getConnection() throws SQLException {
                try {
                    Class.forName("org.postgresql.Driver");
                    Properties properties = new Properties();
                    properties.setProperty(property.getAuditoria().getUser(), property.getAuditoria().getUserPostgres());
                    properties.setProperty(property.getAuditoria().getPass(), property.getAuditoria().getPassPostgres());
                    return DriverManager.getConnection(property.getAuditoria().getUrlDataBase(), properties);
                } catch (ClassNotFoundException e) {
                    log.error("Erro de conexão: ", e);
                    return null;
                }
            }
        };
    }

    @Bean
    public Javers javers(ConnectionProvider connectionProvider) {
        JaversSqlRepository sqlRepository = SqlRepositoryBuilder
                .sqlRepository()
                .withSchema(property.getAuditoria().getSchema())
                .withConnectionProvider(connectionProvider)
                .withDialect(DialectName.POSTGRES).build();
        return JaversBuilder.javers().registerJaversRepository(sqlRepository).build();
    }
}