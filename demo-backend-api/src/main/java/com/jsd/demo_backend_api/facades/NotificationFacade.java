package com.jsd.demo_backend_api.facades;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jsd.demo_backend_api.model.NotificationDTO;
import com.jsd.demo_backend_api.producers.NotificationRequestProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationFacade {

    private final NotificationRequestProducer producer;

    public String sendEmail(NotificationDTO notificationDTO) {

        try {
            producer.integration(notificationDTO);
        } catch (JsonProcessingException e) {
            return "An error occurred while requesting notification..." + e.getMessage();
        }

        return "Sending email requested...";
    }

    public void successResponse(String payload) {
        System.out.println("# Success Message: " + payload);
    }

    public void errorResponse(String payload) {
        System.err.println("& Error Message: " + payload);
    }
}
