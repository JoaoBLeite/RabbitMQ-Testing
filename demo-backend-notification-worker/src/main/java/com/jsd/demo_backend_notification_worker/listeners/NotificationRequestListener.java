package com.jsd.demo_backend_notification_worker.listeners;

import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsd.demo_backend_notification_worker.models.NotificationDTO;
import com.jsd.demo_backend_notification_worker.producers.NotificationResponseErrorProducer;
import com.jsd.demo_backend_notification_worker.producers.NotificationResponseSuccessProducer;
import com.jsd.demo_backend_notification_worker.services.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationRequestListener {

    private final NotificationResponseSuccessProducer successProducer;
    private final NotificationResponseErrorProducer errorProducer;
    private final MailService mailService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "${rabbit-mq.request.queue}")
    public void receiveMessage(@Payload Message<String> message) {
        String payload = message.getPayload();
        System.out.println(payload);

        // Simulate a Success or Error scenario
        if (new Random().nextBoolean()) {
            try {
                mailService.sendTextEmail(objectMapper.readValue(payload, NotificationDTO.class));
                successProducer.generateSuccessResponse("Email sent with success " + payload);
            } catch (Exception e) {
                errorProducer.generateErrorResponse("Email notification failed! " + payload);
            }
        } else {
            errorProducer.generateErrorResponse("Email notification failed! " + payload);
        }
    }

}
