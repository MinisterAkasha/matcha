package ru.matcha.backend.services.impl;

import org.springframework.stereotype.Service;
import ru.matcha.backend.services.MailSender;

@Service
public class MailSenderImpl implements MailSender {
    @Override
    public void send(String emailTo, String subject, String message) {

    }
}
