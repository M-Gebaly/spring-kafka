package com.gebaly.kafka.controller;

import com.gebaly.kafka.component.ProducerComponent;
import com.gebaly.kafka.dto.LogInfo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kafka")
public class KafkaController {

    private final ProducerComponent producer;

    public KafkaController(ProducerComponent producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/publish")
    public String sendMessage(@RequestBody LogInfo logInfo){
        producer.sendMessage(logInfo);
        return "Published successfully";
    }
}
