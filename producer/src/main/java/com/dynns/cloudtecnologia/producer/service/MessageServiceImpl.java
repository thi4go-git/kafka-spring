package com.dynns.cloudtecnologia.producer.service;

import lombok.extern.log4j.Log4j2;
import model.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Service
@Log4j2
public class MessageServiceImpl implements MessageService {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void createEvent(MessageDTO messageDTO) {
        MessageDTO mensagemKafka = new MessageDTO(messageDTO.getStatus(), messageDTO.getDescricao(), messageDTO.getTopico());
        log.info("Enviando para o KAFKA... Payload: " + mensagemKafka.toString());
        kafkaTemplate.send(mensagemKafka.getTopico(), messageDTO)
                .whenComplete(getResultSendMessage());
    }

    private static BiConsumer<SendResult<String, Object>, Throwable> getResultSendMessage() {
        return (success, ex) -> {
            if (ex != null) {
                log.error("::: Erro ao enviar Mensagem KAFKA :::");
            } else {
                log.info("::: Sucesso ao enviar Mensagem KAFKA :::");
            }
        };
    }
}
