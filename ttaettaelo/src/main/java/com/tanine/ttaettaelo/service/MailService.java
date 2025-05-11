package com.tanine.ttaettaelo.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:application-secret.properties")
public class MailService {

    private final JavaMailSender javaMailSender;
    
    @Value("${spring.mail.username}")
    private String senderEmail;
    
    private final Map<String, Integer> certificationMap = new ConcurrentHashMap<>();

	// 랜덤으로 숫자 생성
    public int createNumber() {
    	return (int)(Math.random() * 900000) + 100000; //(int) Math.random() * (최댓값-최소값+1) + 최소값
    }

    public MimeMessage CreateMail(String mail) {
        int number = createNumber();
        certificationMap.put(mail, number);
        
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("이메일 인증");
            String body = "";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h1>" + number + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return message;
    }

    public void sendMail(String mail) {
        MimeMessage message = CreateMail(mail);
        javaMailSender.send(message);
    }
    
    // 인증번호 확인
    public boolean checkNumber(String mail, String userInput) {
        Integer saved = certificationMap.get(mail);
        return saved != null && saved.toString().equals(userInput);
    }
}
