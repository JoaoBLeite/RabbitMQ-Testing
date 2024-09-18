package com.jsd.demo_backend_api.listeners;

import com.jsd.demo_backend_api.facades.NotificationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationSuccessResponseListener {

    private final NotificationFacade notificationFacade;

    @RabbitListener(queues = "${rabbit-mq.response.success.queue}")
    public void receiveSuccessMessage(@Payload Message<String> message) {
        String payload = message.getPayload();
        notificationFacade.successResponse(payload);
    }
}
