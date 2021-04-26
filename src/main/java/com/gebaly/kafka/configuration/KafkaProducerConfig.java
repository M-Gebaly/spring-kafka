package com.gebaly.kafka.configuration;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gebaly.kafka.dto.LogInfo;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class KafkaProducerConfig {

    private final Logger LOGGING = LoggerFactory.getLogger(getClass());

    @Value("${kafka.topic.user-topic}")
    private String userTopic;

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;


    @Bean
    Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    ProducerFactory<String, LogInfo> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    KafkaTemplate<String, LogInfo> kafkaTemplate() {
        KafkaTemplate kafkaTemplate = new KafkaTemplate<String, LogInfo>(producerFactory());
        kafkaTemplate.setMessageConverter(new StringJsonMessageConverter());
        //kafkaTemplate.setDefaultTopic(userTopic);
        kafkaTemplate.setProducerListener(new ProducerListener<String, LogInfo>() {
            @Override
            public void onSuccess(ProducerRecord<String, LogInfo> producerRecord, RecordMetadata recordMetadata) {
                LOGGING.info("ACK from ProducerListener message: {} offset:  {}", producerRecord.value(),
                        recordMetadata.offset());
            }
        });
        return kafkaTemplate;
    }
}
