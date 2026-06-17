package com.example.crudkafkapinot.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "ordenes-topic";

    @Autowired
    private KafkaTemplate<String, OrdenEvent> kafkaTemplate;

    public void enviarEvento(OrdenEvent evento) {
        kafkaTemplate.send(TOPIC, String.valueOf(evento.getId()), evento);
        System.out.println("Kafka -> " + evento);
    }
}