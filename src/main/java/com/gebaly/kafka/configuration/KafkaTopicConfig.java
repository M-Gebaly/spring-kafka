package com.gebaly.kafka.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic.user-topic}")
    private String userTopic;

    @Bean
    public NewTopic adviceTopic(){
        //return new NewTopic("user",3,(short) 1);
        return TopicBuilder.name(userTopic).build();
    }
}
