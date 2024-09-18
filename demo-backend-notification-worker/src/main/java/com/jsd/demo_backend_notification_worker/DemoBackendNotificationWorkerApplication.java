package com.jsd.demo_backend_notification_worker;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class DemoBackendNotificationWorkerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoBackendNotificationWorkerApplication.class, args);
    }
}
