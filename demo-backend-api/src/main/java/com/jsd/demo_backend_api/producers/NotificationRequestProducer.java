package com.jsd.demo_backend_api.producers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsd.demo_backend_api.model.NotificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationRequestProducer {

    private final AmqpTemplate amqpTemplate;

    private final ObjectMapper objectMapper;

    @Value("${rabbit-mq.request.exchange}")
    private String exchange;

    @Value("${rabbit-mq.request.rout-key}")
    private String routKey;

    public void integration(NotificationDTO notificationDTO) throws JsonProcessingException {
        amqpTemplate.convertAndSend(exchange, routKey, objectMapper.writeValueAsString(notificationDTO));
    }
}
