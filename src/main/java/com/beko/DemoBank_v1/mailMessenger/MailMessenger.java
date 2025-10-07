package com.beko.DemoBank_v1.mailMessenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailMessenger {

    private final JavaMailSender mailSender;

    @Autowired
    public MailMessenger(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendHtmlEmail(String from, String toMail, String subject, String body) throws MessagingException {
        // Create message
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(from);
        helper.setTo(toMail);
        helper.setSubject(subject);
        helper.setText(body, true); // true = HTML

        // Send mail
        mailSender.send(message);
    }
}
