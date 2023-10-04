package com.dynns.cloudtecnologia.consumer.listeners;

import lombok.extern.log4j.Log4j2;
import model.dto.MessageDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class MessageListener {
    @KafkaListener(
            topics = "topico-1",//escuta o tópico 1
            groupId = "message-one",//cria se ñ existir e escuta o consumer group message-one
            containerFactory = "containerFactory" //nome do container(método) criado na config
    )
    public void messageOneListener1(@Payload MessageDTO messageDTO) {
        log.info("::: (messageOneListener1) Mensagem recebida | Payload {}", messageDTO.toString());
    }

    @KafkaListener(
            topics = "topico-1",//escuta o tópico 1..
            groupId = "message-one",//cria se ñ existir e escuta o consumer group message-one
            containerFactory = "containerFactory" //nome do container(método) criado na config
    )
    public void messageOneListener2(@Payload MessageDTO messageDTO) {
        log.info("::: (messageOneListener2) Mensagem recebida | Payload {}", messageDTO.toString());
    }

    @KafkaListener(
            topics = "topico-2",//escuta o tópico 2
            groupId = "message-two",//cria o consumer group message-two
            containerFactory = "containerFactory" //nome do container(método) criado na config
    )
    public void messageTwoListener(@Payload MessageDTO messageDTO) {
        log.info("::: (message-two) Mensagem recebida | Payload {}", messageDTO.toString());
    }
}
