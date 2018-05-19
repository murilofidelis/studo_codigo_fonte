package br.com.studo;

import br.com.studo.config.StudoProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@EnableCaching
@SpringBootApplication
@EnableConfigurationProperties(StudoProperty.class)
public class StudoServiceApplication {

    public static void main(String[] args) throws UnknownHostException {

        SpringApplication app = new SpringApplication(StudoServiceApplication.class);

        Environment env = app.run(args).getEnvironment();

        log.info("\n----------------------------------------------------------\n\t" +
                        "Serviço: '{}' iniciado! Acesso URLs:\n\t" +
                        "Local: \t\thttp://localhost:{}\n\t" +
                        "Externo: \thttp://{}:{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));
    }

}
