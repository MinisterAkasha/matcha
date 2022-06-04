package ru.matcha.backend.services;

public interface MailSender {

    void send(String emailTo, String subject, String message);
}
