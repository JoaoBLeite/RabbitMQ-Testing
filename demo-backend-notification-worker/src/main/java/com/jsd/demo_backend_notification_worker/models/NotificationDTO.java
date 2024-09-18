package com.jsd.demo_backend_notification_worker.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NotificationDTO {

    private String email;
    private String title;
    private String content;

}