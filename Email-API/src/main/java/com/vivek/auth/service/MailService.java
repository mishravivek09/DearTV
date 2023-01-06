package com.vivek.auth.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
@Service
public class MailService {
	private JavaMailSender mail;
	@Autowired
	public MailService(JavaMailSender mail){
		super();
		this.mail = mail;
	}
	public void sendOtpMessage(String to,String sub,String  msg) throws MessagingException{
		MimeMessage mime=mail.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(mime);

		helper.setTo(to);
		helper.setSubject(sub);
		helper.setText(msg, true);
		mail.send(mime);
	}
}
