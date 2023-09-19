package com.dynns.cloudtecnologia.producer.service;

import model.dto.MessageDTO;

public interface MessageService {
    void createEvent(MessageDTO messageDTO);
}
