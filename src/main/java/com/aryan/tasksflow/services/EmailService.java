package com.aryan.tasksflow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.service.registry.ImportHttpServices;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;
    public void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(body);
            javaMailSender.send(mail);
        } catch (Exception e) {
            System.out.println("Exception while sendEmail ");
        }
    }
}
