package com.gebaly.kafka.component;

import com.gebaly.kafka.dto.LogInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerComponent.class);


    @KafkaListener(topics = "user")
    public void consume(LogInfo logInfo){
        LOGGER.info(String.format("************** Consumer receive the message %s", logInfo));
    }
}
