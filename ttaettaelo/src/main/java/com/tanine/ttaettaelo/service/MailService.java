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

/**
 * 이메일 인증 관련 서비스를 처리하는 클래스
 * 사용자가 이메일 인증을 요청하면 인증메일을 보내고
 * 사용자가 입력한 인증번호가 일치하는지 확인하는 기능을 제공한다.
 */
@Service
@RequiredArgsConstructor
@PropertySource("classpath:application-secret.properties")
public class MailService {

    private final JavaMailSender javaMailSender;
    
    @Value("${spring.mail.username}")
    private String senderEmail;
    
    private final Map<String, Integer> certificationMap = new ConcurrentHashMap<>(); // 키: 이메일 주소, 값: 인증번호

    /**
     * 랜덤으로 인증번호 생성
     * @return 6자리 인증번호
     */
    public int createNumber() {
    	return (int)(Math.random() * 900000) + 100000; //(int) Math.random() * (최댓값-최소값+1) + 최소값
    }

    /**
     * 인증메일 생성
     * @param email 인증메일을 보낼 이메일 주소
     * @return 생성된 인증메일
     */
    public MimeMessage CreateMail(String email) {
        int number = createNumber(); // 인증번호 생성
        certificationMap.put(email, number);
        
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail); // 발신자 이메일
            message.setRecipients(MimeMessage.RecipientType.TO, email); // 수신자 이메일
            message.setSubject("이메일 인증"); // 이메일 제목
            
            // 이메일 본문
            String body = "";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h1>" + number + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            
            message.setText(body,"UTF-8", "html"); // 본문 내용 설정
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return message;
    }

    /**
     * 인증메일 전송
     * @param email 인증메일을 보낼 이메일 주소
     */
    public void sendMail(String email) {
        MimeMessage message = CreateMail(email); // 이메일 생성
        javaMailSender.send(message); // 이메일 보내기
    }
    
    /**
     * 사용자가 입력한 인증번호가 일치하는지 확인
     * @param email
     * @param userInput 사용자가 입력한 인증번호
     * @return 인증번호 일치 여부
     */
    public boolean checkNumber(String email, String userInput) {
        Integer saved = certificationMap.get(email); // 인증번호 가져오기
        return saved != null && saved.toString().equals(userInput); // 확인 후 결과 반환
    }
}
