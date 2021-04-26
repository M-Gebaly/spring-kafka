package com.gebaly.kafka.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LogInfo implements Serializable {
    
    private String username;
    private String message;
}
