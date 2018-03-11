package br.com.studo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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
	}
}
