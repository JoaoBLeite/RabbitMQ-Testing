package com.jsd.demo_backend_notification_worker.listeners;

import java.util.Random;

import com.jsd.demo_backend_notification_worker.producers.NotificationResponseErrorProducer;
import com.jsd.demo_backend_notification_worker.producers.NotificationResponseSuccessProducer;
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

    @RabbitListener(queues = "${rabbit-mq.request.queue}")
    public void receiveMessage(@Payload Message message) {
        System.out.println(message.getPayload());
        if (new Random().nextBoolean()) {
            successProducer.generateSuccessResponse("Email sent with success" + message);
        } else {
            errorProducer.generateErrorResponse("Email notification failed!" + message);
        }
    }

}
