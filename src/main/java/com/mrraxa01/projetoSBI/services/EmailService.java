package com.mrraxa01.projetoSBI.services;

import org.springframework.mail.SimpleMailMessage;

import com.mrraxa01.projetoSBI.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
