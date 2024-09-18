package com.jsd.demo_backend_notification_worker.services;

import com.jsd.demo_backend_notification_worker.models.NotificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String emailAddress;

    public void sendTextEmail(NotificationDTO notificationDTO) throws MailException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailAddress);
        simpleMailMessage.setTo(notificationDTO.getEmail());
        simpleMailMessage.setSubject(notificationDTO.getTitle());
        simpleMailMessage.setText(notificationDTO.getContent());

        mailSender.send(simpleMailMessage);
    }

}
