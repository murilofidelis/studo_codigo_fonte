package br.com.studo.task;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class AtividadeTask {

    private static final String TIME_ZONE = "America/Sao_Paulo";

//    @Scheduled(cron = "*/5 * * * * *", zone = TIME_ZONE)
    private void encerraAtividade() {
        System.out.println("EXECUTANDO....");
    }
}
