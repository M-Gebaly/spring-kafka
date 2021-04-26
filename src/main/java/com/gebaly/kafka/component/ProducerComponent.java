package com.gebaly.kafka.component;

import com.gebaly.kafka.dto.LogInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProducerComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerComponent.class);

    @Value("${kafka.topic.user-topic}")
    private String userTopic;


    private KafkaTemplate<String, LogInfo> kafkaTemplate;

    @Autowired
    public ProducerComponent(KafkaTemplate<String, LogInfo> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(LogInfo logInfo){
        LOGGER.info(String.format("************** Producer sending the message %s", logInfo));
        kafkaTemplate.send(userTopic, logInfo);
    }
}
