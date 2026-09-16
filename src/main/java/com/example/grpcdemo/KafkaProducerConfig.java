package com.example.grpcdemo;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;


@Configuration
public class KafkaProducerConfig {
  @Value("${spring.kafka.properties.bootstrap.servers}")
  private String bootStrapServers;
  @Value("${spring.kafka.properties.sasl.jaas.config}")
  private String saslconfig;
  @Value("${spring.kafka.properties.security.protocol}")
  private String securityprotocol;
  @Value("${spring.kafka.properties.sasl.mechanism}")
  private String saslmechanism;
  @Value("${spring.kafka.properties.truststore.location}")
  private String truststoreLocation;
  @Value("${spring.kafka.properties.truststore.password}")
  private String truststorePassword;
  @Value("${spring.kafka.properties.ssl.truststore.type}")
  private String truststoreType;

  @Value("${spring.kafka.properties.session.timeout.ms}")
  private String sessionTimeout;

  @Value("${spring.kafka.properties.acks}")
  private String acks;

  public Map producerConfigs(){
    Map <String,Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServers);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    props.put("sasl.jaas.config","org.apache.kafka.common.security.scram.ScramLoginModule required username=\"avnadmin\" password=\"AVNS_v4EywHz-wycHum3wwo8\";");
    props.put("sasl.mechanism",saslmechanism);
    props.put("security.protocol",securityprotocol);
//    props.put("session.timeout.ms",sessionTimeout);
    props.put("ssl.truststore.location", "D:\\grpc-demo\\src\\main\\resources\\client-certs\\ca.pem");
    props.put("ssl.truststore.type", "PEM");
//    props.put("ssl.truststore.password", truststorePassword);
    props.put("cleanup.policy","compact");
    props.put("acks",acks);
    props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG,"snappy");

    return props;
  }
  @Bean(name = "producerFactory")
  public ProducerFactory<String, Object> producerFactory() { return new DefaultKafkaProducerFactory<>(producerConfigs());
  }
  @Bean(name = "kafkaTemplate")
  public KafkaTemplate<String, Object> kafkaTemplate() {return new KafkaTemplate<>(producerFactory());}
//  @Bean
//  public NewTopic ordersTopic() {
//    return TopicBuilder.name("orders-topic")
//        .partitions(3)
//        .replicas(1)
//        .build();
//  }

}
