package com.dynns.cloudtecnologia.producer.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;


@Configuration
public class KafkaConfig {


    @Autowired
    private KafkaProperties kafkaProperties;


    /**
     * Esse Bean serve para rodar esse método assim que o projeto starta
     * se não existir esses tópicos no KAFKA ele cria.
     *
     * @return
     */
    @Bean
    public KafkaAdmin.NewTopics newTopics() {
        //Criando tópicos e partições
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name("topico-1").partitions(2).build(),
                TopicBuilder.name("topico-2").partitions(2).build(),
                TopicBuilder.name("topico-3").partitions(2).build()
        );
    }


    /**
     * Esse bean configura a Fábrica de produção de mensagens
     *
     * @return
     */
    @Bean
    public ProducerFactory producerFactory() {
        var configs = new HashMap<String, Object>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        //Essa configuração do Bootstrapservers vem do application.yml conforme essa configuração

        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //import org.apache.kafka.common.serialization.StringSerializer;

        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        //import org.springframework.kafka.support.serializer.JsonSerializer;


        return new DefaultKafkaProducerFactory(configs);
    }


    /**
     * Configura o template para envio de mensagens
     *
     * @return
     */
    @Bean
    public KafkaTemplate<String, Object> jsonKafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
