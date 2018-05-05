package br.com.studo.integration;

import br.com.studo.domain.dto.DisciplinaDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UsuarioSource {

    // @Autowired
    //  private AmqpTemplate amqpTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${channel.usuario.exchange}")
    private String exchange;

    @Value("${channel.usuario.routingkey}")
    private String routingKey;

    public void sendMensagem(DisciplinaDTO dto) {
        rabbitTemplate.convertAndSend(exchange, routingKey, dto);
        System.out.println("Mensagem enviada: " + dto);

    }
}
