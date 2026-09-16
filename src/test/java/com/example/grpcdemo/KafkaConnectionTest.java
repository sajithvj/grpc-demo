package com.example.grpcdemo;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaConnectionTest {
  public static void main(String[] args) {
    Properties props = new Properties();
    props.setProperty("bootstrap.servers", "kafka-3d3dad9a-sajithjayaraman74-192c.l.aivencloud.com:13703");
    props.setProperty("security.protocol", "SASL_SSL");
    // Note: When running in an IDE, you may need to specify
    // the full path to certificate files
    // Example: props.setProperty("ssl.truststore.location", "/full/path/to/ca.pem");
    props.setProperty("ssl.truststore.location", "D:\\grpc-demo\\src\\main\\resources\\client-certs\\ca.pem");
    props.setProperty("ssl.truststore.type", "PEM");
    props.setProperty("sasl.mechanism", "SCRAM-SHA-256");
    props.setProperty("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"avnadmin\" password=\"AVNS_v4EywHz-wycHum3wwo8\";");
    props.setProperty("key.serializer", StringSerializer.class.getName());
    props.setProperty("value.serializer", StringSerializer.class.getName());

    KafkaProducer<String, String> producer = new KafkaProducer<>(props);

    try {

      while (true) {

        String key = "hello";
        String value = "world";

        ProducerRecord<String, String> record = new ProducerRecord<String, String>("topic_1", key, value);
        producer.send(record);

        System.out.printf("Produced: key=%s, value=%s into topic topic_1%n", key, value);

        Thread.sleep(1000);
      }

    } catch (Exception ex) {
      System.out.println(ex);

    } finally {
      producer.flush();
      producer.close();
    }
  }

}


