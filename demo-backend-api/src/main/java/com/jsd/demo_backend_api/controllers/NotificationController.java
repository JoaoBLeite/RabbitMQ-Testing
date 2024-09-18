package com.jsd.demo_backend_api.controllers;

import com.jsd.demo_backend_api.facades.NotificationFacade;
import com.jsd.demo_backend_api.model.NotificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationFacade notificationFacade;

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody NotificationDTO notificationDTO) {
        String response = notificationFacade.sendMessage(notificationDTO);
        return ResponseEntity.ok(response);
    }
}
