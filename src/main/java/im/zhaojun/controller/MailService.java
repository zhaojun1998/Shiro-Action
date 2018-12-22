package im.zhaojun.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MailService {

    @Value("${spring.mail.username}")
    private String form;

    @Resource
    private MailSender mailSender;

    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        mailMessage.setFrom(form);
        mailSender.send(mailMessage);
    }
}
