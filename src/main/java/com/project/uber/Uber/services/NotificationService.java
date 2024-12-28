package com.project.uber.Uber.services;

public interface NotificationService {

    void sendEmail(String to, String subject, String body);

    void sendEmail(String[] to, String subject, String body);

}
