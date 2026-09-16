package com.example.grpcdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
  private static final String TOPIC = "topic_1";

  private final KafkaTemplate<String,Object> kafkaTemplate;

  @Autowired
  public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void publishOrderCreated(String key, String event) {
    kafkaTemplate.send(TOPIC, key, event)
        .whenComplete((result, ex) -> {
          if (ex != null) {
            System.out.println("Failed to publish order event");
          } else {
            System.out.println("Order event published successfully");
          }
        });
  }
}
