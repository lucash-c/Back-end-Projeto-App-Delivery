package com.lucashcampos.projetodelivery.services;

import org.springframework.mail.SimpleMailMessage;

import com.lucashcampos.projetodelivery.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);

}
