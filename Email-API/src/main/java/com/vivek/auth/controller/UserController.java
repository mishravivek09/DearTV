package com.vivek.auth.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vivek.auth.service.MailService;
import com.vivek.auth.service.OtpService;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
public class UserController {
	private MailService mail;
	private OtpService otp;
	@Autowired
	public UserController(MailService mail, OtpService otp) {
		super();
		this.mail = mail;
		this.otp = otp;
	}
	@GetMapping("/generate/otp/{uname}")
	public String generateOTP(@PathVariable String uname) throws MessagingException{
		
		int OTP=otp.generateOtp(uname);
		mail.sendOtpMessage(uname, "Email Verification","<h2>Verification code</h2>"+"\n"+"<h1>"+String.valueOf(OTP)+"</h1>"+"\n"+"<h3>(This code is valid for 5 minutes)</h3>") ;
		
		return "OTP sended successfully to "+uname;
	}
	@GetMapping("/validate/otp/{uname}/{OTP}")
	public String validateOTP(@PathVariable String uname, @PathVariable int OTP) {
		int x=otp.getOtp(uname);
		
		if(x==OTP) {
			otp.clearOtp(uname);
			return "OTP Verified Successfully..";
		}else {
			return "Wrong OTP! Please try again..";
		}
	}
}
