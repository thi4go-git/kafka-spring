package com.dynns.cloudtecnologia.producer.service;

import model.dto.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private static final Logger LOG = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Override
    public void createEvent(MessageDTO messageDTO) {
        MessageDTO mensagemKafka = new MessageDTO(messageDTO.getStatus(), messageDTO.getDescricao(), messageDTO.getTopico());
        kafkaTemplate.send(mensagemKafka.getTopico(), mensagemKafka);
        LOG.info("Mensagem enviada para o KAFKA! Payload: " + mensagemKafka.toString());
    }
}
