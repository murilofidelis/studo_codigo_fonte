package br.com.studo.service;

public interface EmailService {

    void enviaEmail(String destinatario, String assunto, String mensagem);
}
