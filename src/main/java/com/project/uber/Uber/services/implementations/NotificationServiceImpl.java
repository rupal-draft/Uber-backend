package com.project.uber.Uber.services.implementations;

import com.project.uber.Uber.exceptions.RuntimeConflictException;
import com.project.uber.Uber.services.NotificationService;
import org.apache.log4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {


    private static final Logger log = Logger.getLogger(NotificationServiceImpl.class);
    private final JavaMailSender javaMailSender;

    public NotificationServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(body);

            javaMailSender.send(simpleMailMessage);

            log.info("Email sent successfully!");
        } catch (Exception e){
            log.error(e.getLocalizedMessage());
            throw new RuntimeConflictException(e.getLocalizedMessage());
        }

    }

    @Override
    public void sendEmail(String[] to, String subject, String body) {
        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(body);

            javaMailSender.send(simpleMailMessage);

            log.info("Email sent successfully!");
        } catch (Exception e){
            log.error(e.getLocalizedMessage());
            throw new RuntimeConflictException(e.getLocalizedMessage());
        }
    }

}
