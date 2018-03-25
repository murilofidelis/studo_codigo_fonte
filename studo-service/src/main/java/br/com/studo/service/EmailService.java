package br.com.studo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void enviaEmail(String destinatario, String assunto, String mensagem) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(destinatario);
        mail.setSubject(assunto);
        mail.setText(mensagem);

        javaMailSender.send(mail);
        log.info("E-MAIL ENVIADO PARA: {}", destinatario);
    }
}
