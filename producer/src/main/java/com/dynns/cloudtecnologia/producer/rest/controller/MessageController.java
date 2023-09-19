package com.dynns.cloudtecnologia.producer.rest.controller;


import com.dynns.cloudtecnologia.producer.service.MessageServiceImpl;
import model.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/messages")
public class MessageController {

    @Autowired
    private MessageServiceImpl messageService;

    @PostMapping
    ResponseEntity<Void> createEvent(@RequestBody MessageDTO messageDTO) {
        messageService.createEvent(messageDTO);
        return ResponseEntity.ok().build();
    }
}
