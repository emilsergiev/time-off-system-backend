package com.concretepage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	private JavaMailSender sender;
	
	@Autowired
	public EmailService(JavaMailSender sender) {
		this.sender = sender;
	}
	
	public void sendEmail(String recieverAddress, String subject, String body) throws MailException {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(recieverAddress);
		message.setFrom("Time-off-System");
		message.setSubject(subject);
		message.setText(body);
		
		sender.send(message);
	}

}