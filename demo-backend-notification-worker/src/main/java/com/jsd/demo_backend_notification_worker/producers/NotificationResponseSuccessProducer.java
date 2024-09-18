package com.jsd.demo_backend_notification_worker.producers;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationResponseSuccessProducer {

    private final AmqpTemplate amqpTemplate;

    @Value("${rabbit-mq.response.success.exchange}")
    private String exchange;

    @Value("${rabbit-mq.response.success.rout-key}")
    private String routKey;

    public void generateSuccessResponse(String message) {
        amqpTemplate.convertAndSend(exchange, routKey, message);
    }

}