package ru.matcha.services.impl;

import org.springframework.stereotype.Service;
import ru.matcha.services.MailSender;

@Service
public class MailSenderImpl implements MailSender {
    @Override
    public void send(String emailTo, String subject, String message) {

    }
}
