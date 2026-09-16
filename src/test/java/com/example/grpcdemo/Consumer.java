package com.example.grpcdemo;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;

public final class Consumer {
  public static void main(String[] args) {
    Properties props = new Properties();
    props.setProperty("bootstrap.servers", "");
    props.setProperty("security.protocol", "SASL_SSL");
    // Note: When running in an IDE, you may need to specify
    // the full path to certificate files
    // Example: props.setProperty("ssl.truststore.location", "/full/path/to/ca.pem");
    props.setProperty("ssl.truststore.location", "");
    props.setProperty("ssl.truststore.type", "PEM");
    props.setProperty("sasl.mechanism", "SCRAM-SHA-256");
    props.setProperty("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=password=;");
    props.setProperty("group.id", "topic_1-consumer");
    props.setProperty("auto.offset.reset", "latest");
    props.setProperty("key.deserializer", StringDeserializer.class.getName());
    props.setProperty("value.deserializer", StringDeserializer.class.getName());

    KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

    consumer.subscribe(Arrays.asList("topic_1"));

    try {
      while (true) {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
        for (ConsumerRecord<String, String> record : records) {
          System.out.printf("Consumed: key=%s, value=%s from topic topic_1%n", record.key(), record.value());
        }
      }

    } finally {
      consumer.close();
    }
  }
}